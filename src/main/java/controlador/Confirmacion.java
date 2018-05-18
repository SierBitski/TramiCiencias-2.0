/**
 * Confirmacion
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.*;
import lombok.*;

@ManagedBean
@SessionScoped
public class Confirmacion {
    @Getter @Setter String id;
    @Getter @Setter int hash;

    public String activar() {
        UsuarioDAO udao = new UsuarioDAO();
        try {
            udao.confirmarUsuario(id, hash);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "index.xhtml?faces-redirect=true";
    }
    
}