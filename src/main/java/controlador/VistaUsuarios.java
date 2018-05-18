/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class VistaUsuarios {
    @Getter @Setter private List<Usuario> usuarios;

    public void cargarUsuarios() {
        UsuarioDAO udao = new UsuarioDAO();
        try {
            List<Usuario> usuarios = udao.usuarios();
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        this.usuarios = usuarios;
    }
}
