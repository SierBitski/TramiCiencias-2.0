/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import lombok.*;
import modelo.*;

/**
 *
 * @author dixego
 */
@ManagedBean
@SessionScoped
public class SesionGlobal {
    
    @Getter @Setter private Usuario usuario;
    @Getter @Setter private boolean logged;
    
    
    
    public void login(Usuario usuario) {
        setUsuario(usuario);
        if(!usuario.equals(null))
            logged = true;
    }
    
    public void logout() {
        this.usuario = null;
        this.logged = false;
    }
    
}
