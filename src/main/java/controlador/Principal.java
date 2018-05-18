/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import modelo.Pregunta;
import modelo.PreguntaDAO;

/**
 *
 * @author dixego
 */
@ViewScoped
@ManagedBean
public class Principal {
    @Getter private List<Pregunta> preguntas;
    
    @PostConstruct
    public void conseguirPreguntas() {
        PreguntaDAO pdao = new PreguntaDAO();
        List<Pregunta> preguntas = null;
        try {
            preguntas = pdao.preguntas();
            Collections.reverse(preguntas);
        this.preguntas = preguntas;
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }
    
}
