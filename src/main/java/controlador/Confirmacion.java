/**
 * Confirmacion
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.*;
import lombok.*;

@ManagedBean
@SessionScoped
public class Confirmacion {
    @Getter @Setter String id;
    @Getter @Setter int hash;

    public String activar() {
        UsuarioDAO udao = new UsuarioDAO();
        udao.confirmarUsuario(id, hash);
        return "index.xhtml?faces-redirect=true";
    }
    
}