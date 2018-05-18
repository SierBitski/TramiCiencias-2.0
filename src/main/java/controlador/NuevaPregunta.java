/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.*;
import modelo.*;


@SessionScoped
@ManagedBean
public class NuevaPregunta {
    
    @Getter @Setter private String titulo;
    @Getter @Setter private String texto;
    @ManagedProperty(value="#{sesionGlobal}") @Getter @Setter
    SesionGlobal sesionGlobal;
    
    public String guardarPregunta() {
        PreguntaDAO pdao = new PreguntaDAO();
        Pregunta r = Pregunta.builder()
                .correoUsuario(sesionGlobal.getUsuario().getCorreo())
                .titulo(titulo)
                .texto(texto)
                .build();
        try {
            pdao.guardar(r);            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        
        return "index?faces-redirect=true";
    }
    
}
