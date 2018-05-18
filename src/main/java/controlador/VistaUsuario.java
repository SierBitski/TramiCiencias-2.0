/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.*;
import lombok.*;

/**
 *
 * @author dixego
 */
@SessionScoped
@ManagedBean
public class VistaUsuario {
    //@ManagedProperty(value = "#{params.id")
    @Getter @Setter private String id;
    @Getter @Setter private Usuario user;
    @Getter @Setter private boolean loggedAdmin;
    @Getter @Setter private List<Pregunta> preguntas;
    @ManagedProperty(value="#{sesionGlobal}") @Getter @Setter
    SesionGlobal sesionGlobal;
    
    //@PostConstruct
    //private void construct() {  
    //}
    
    public void cargarUsuario() {
        UsuarioDAO udao = new UsuarioDAO();
        PreguntaDAO pdao = new PreguntaDAO();
        if(this.id == null) {
            try{ 
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Usuario logged = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if(logged != null) {
            this.loggedAdmin = logged.isEsAdmin();
        } else {
            this.loggedAdmin = false;
        }
        try {
            this.user = udao.buscar(id);
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        
        if (this.user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("404.html");
            } catch (IOException ex) {

            }
        } else {
            try {
                this.preguntas = pdao.buscarPorUsuario(user.getCorreo());
            } catch(Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            }
            
            Collections.reverse(this.preguntas);
        }
    }
    
    public void borrarUsuario() {
        UsuarioDAO udao = new UsuarioDAO();
        RespuestaDAO rdao = new RespuestaDAO();
        PreguntaDAO pdao = new PreguntaDAO();
        try {
        for(Pregunta p: this.preguntas) {
            rdao.borrarRespuestasPorPregunta(p.getIdPregunta());
            pdao.borrar(p.getIdPregunta());
        }
        rdao.borrarRespuestasPorUsuario(this.id);
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ocurrió un error con la base de datos; vuelva a intentar más tarde."));
        }
        if(sesionGlobal.getUsuario().getCorreo() == this.id) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            System.out.println("XXXXXXXXXXXXX:" + sesionGlobal.getUsuario().getCorreo());
        }
        try {
            udao.borrar(this.id);
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
