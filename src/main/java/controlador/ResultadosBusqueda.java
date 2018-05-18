/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author vicaris
 */
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.*;
import lombok.*;

@SessionScoped
@ManagedBean
public class ResultadosBusqueda {
    
    @Getter @Setter private String busqueda;
    @Getter @Setter private List<Pregunta> preguntas;
    @Getter @Setter private Pregunta preguntaEncontrada;
       
    public String visitar(int id){
        PreguntaDAO pdao = new PreguntaDAO();
        try {
            preguntaEncontrada = pdao.buscarPregunta(id);
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        return "pregunta?id="+preguntaEncontrada;
    }
    
    public String buscarPregunta() {
        PreguntaDAO pdao = new PreguntaDAO();
        try {
            preguntas = pdao.buscarTitulo(busqueda);
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        return "resultados?faces-redirect=true";
    }
    
}