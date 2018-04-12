/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lombok.*;
import modelo.*;


@SessionScoped
@ManagedBean
public class NuevaRespuesta {
    
    @Getter @Setter private String respuesta;
    @ManagedProperty(value="#{vistaPregunta}") @Getter @Setter 
    VistaPregunta vistaPregunta;
    @ManagedProperty(value="#{sesionGlobal}") @Getter @Setter
    SesionGlobal sesionGlobal;
    
    public void guardarRespuesta() {
        RespuestaDAO rdao = new RespuestaDAO();
        Respuesta r = Respuesta.builder()
                .correoUsuario(sesionGlobal.getUsuario().getCorreo())
                .idPregunta(vistaPregunta.getPregunta().getIdPregunta())
                .texto(respuesta)
                .build();
        rdao.guardar(r);        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());    
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
