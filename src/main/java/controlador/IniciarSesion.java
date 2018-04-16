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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

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
        Usuario usuario = udao.buscar(correoUsuario,contrasena);
        FacesContext context = FacesContext.getCurrentInstance();

        if (usuario == null) {
            context.addMessage(null, new FacesMessage("No se encontró el usuario"));
            correoUsuario = null;
            contrasena = null;
            return "";
        } else{
            context.getExternalContext().getSessionMap().put("user", usuario);
            sesionGlobal.login(usuario);
            correoUsuario = null;
            contrasena = null;
            return "index.xhtml?faces-redirect=true";
        }
    }

    public String  logout() {
        sesionGlobal.logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
}
