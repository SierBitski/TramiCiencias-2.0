/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.*;

/**
 *
 * @author Juanitobanana
 */
@ManagedBean
@SessionScoped
public class IniciarSesion {
    
    
    @Getter @Setter private String correoUsuario;
    @Getter @Setter private String contrasena;
    
    @ManagedProperty(value="#{sesionGlobal}") @Getter @Setter
    private SesionGlobal sesionGlobal;
    
        
    public String login() {
        UsuarioDAO udao = new UsuarioDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Usuario usuario = udao.buscar(correoUsuario,contrasena);   
            sesionGlobal.login(usuario);
            correoUsuario = null;
            contrasena = null;
            return "index.xhtml?faces-redirect=true";
        } catch(Exception e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
            correoUsuario = null;
            contrasena = null;
            return "";
        }
    }

    public String  logout() {
        sesionGlobal.logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
}
