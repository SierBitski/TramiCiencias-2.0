/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.*;
import lombok.*;

/**
 *
 * @author dixego
 */
@SessionScoped
@ManagedBean
public class VistaPregunta {
    //@ManagedProperty(value = "#{partams.id")
    @Getter @Setter private int id;
    @Getter @Setter private Pregunta pregunta;
    @Getter @Setter private List<Respuesta> respuestas;
    @Getter @Setter private int respuestaId;
    @Getter @Setter private boolean loggedAdmin;
    
    //@PostConstruct
    //private void construct() {  
    //}
    
    public void cargarPregunta() {
        PreguntaDAO pdao = new PreguntaDAO();
        RespuestaDAO rdao = new RespuestaDAO();
        /*if(this.id == null) {
            try{ 
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        this.pregunta = pdao.buscarPregunta(id);
        this.respuestas = rdao.buscarRespuestasPorPregunta(id);
        Usuario logged = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if(logged != null) {
            this.loggedAdmin = logged.isEsAdmin();
        } else {
            this.loggedAdmin = false;
        }
        if(this.pregunta == null)
            try {
               FacesContext.getCurrentInstance().getExternalContext().redirect("404.html");
        } catch (IOException ex) {
            
        }
    }
    
    public void borrarPregunta() {
        RespuestaDAO rdao = new RespuestaDAO();
        PreguntaDAO pdao = new PreguntaDAO();
        
        rdao.borrarRespuestasPorPregunta(this.id);
        pdao.borrar(this.id);
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());    
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void borrarRespuesta(int id) {
        RespuestaDAO rdao = new RespuestaDAO();
        rdao.borrar(id);
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());    
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public String print(int id) {
        System.out.println(id);
        return "success";
    }
}
