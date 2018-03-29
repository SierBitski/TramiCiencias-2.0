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
    @ManagedProperty(value = "#{param.id}")
    @Getter @Setter private String id;
    @Getter @Setter private Usuario user;
    
    @PostConstruct
    private void construct() {
        UsuarioDAO udao = new UsuarioDAO();
        this.user = udao.buscar(id);
        if(this.user == null)
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../404.html");
        } catch (IOException ex) {
            Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarUsuario(String id) {
        System.out.println(id);
        UsuarioDAO udao = new UsuarioDAO();
        udao.borrar(id);
    }
}
