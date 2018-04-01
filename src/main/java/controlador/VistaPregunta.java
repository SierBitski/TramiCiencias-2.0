/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.*;
import lombok.*;

/**
 *
 * @author dixego
 */
@ViewScoped
@ManagedBean
public class VistaPregunta {
    //@ManagedProperty(value = "#{partams.id")
    @Getter @Setter private int id;
    @Getter @Setter private Pregunta pregunta;
    @Getter @Setter private List<Respuesta> respuestas;
    @Getter @Setter private int respuestaId;
    
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
        if(this.pregunta == null)
            try {
               FacesContext.getCurrentInstance().getExternalContext().redirect("404.html");
        } catch (IOException ex) {
            
        }
    }
    
    public void borrarPregunta() {
        /*RespuestaDAO rdao = new RespuestaDAO();
        PreguntaDAO pdao = new PreguntaDAO();
        
        rdao.borrarRespuestasPorPregunta(this.id);
        pdao.borrar(this.id);*/
        System.out.println("LAAAAAAAAAVIIIIIIIIKIIIIIIINAAAAAAAAAAA");
    }
    
    public void borrarRespuesta() {
        System.out.println("HAHAHAH YOU SORRY FOOL");
    }
}
