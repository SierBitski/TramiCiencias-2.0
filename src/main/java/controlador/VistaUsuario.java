/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.IOException;
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
public class VistaUsuario {
    //@ManagedProperty(value = "#{params.id")
    @Getter @Setter private String id;
    @Getter @Setter private Usuario user;
    
    //@PostConstruct
    //private void construct() {  
    //}
    
    public void cargarUsuario() {
        UsuarioDAO udao = new UsuarioDAO();
        if(this.id == null) {
            try{ 
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.user = udao.buscar(id);
        if(this.user == null)
            try {
               FacesContext.getCurrentInstance().getExternalContext().redirect("404.html");
        } catch (IOException ex) {
            
        }
    }
    
    public void borrarUsuario() {
        UsuarioDAO udao = new UsuarioDAO();
        udao.borrar(this.id);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
