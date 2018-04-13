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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
        this.user = udao.buscar(id);
        
        if (this.user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("404.html");
            } catch (IOException ex) {

            }
        } else {
            this.preguntas = pdao.buscarPorUsuario(user.getCorreo());
            Collections.reverse(this.preguntas);
        }
    }
    
    public void borrarUsuario() {
        UsuarioDAO udao = new UsuarioDAO();
        RespuestaDAO rdao = new RespuestaDAO();
        PreguntaDAO pdao = new PreguntaDAO();
        
        for(Pregunta p: this.preguntas) {
            rdao.borrarRespuestasPorPregunta(p.getIdPregunta());
            pdao.borrar(p.getIdPregunta());
        }
        rdao.borrarRespuestasPorUsuario(this.id);

        if(sesionGlobal.getUsuario().getCorreo() == this.id) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            System.out.println("XXXXXXXXXXXXX:" + sesionGlobal.getUsuario().getCorreo());
        }
        
        udao.borrar(this.id);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
