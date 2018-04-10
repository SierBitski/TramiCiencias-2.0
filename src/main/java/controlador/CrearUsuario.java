/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.regex.*;
import javax.faces.application.FacesMessage;
import modelo.*;
import lombok.*;

/**
 *
 * @author dixego
 */
@ViewScoped
@ManagedBean
public class CrearUsuario {
    @Getter @Setter private String correo;
    @Getter @Setter private String nombreUsuario;
    @Getter @Setter private Date fechaNacimiento;
    @Getter @Setter private boolean esAdmin;
    @Getter @Setter private String contrasena;
    
    
      
    public String crearUsuario() {
        Pattern p = Pattern.compile("[\\w\\-\\_\\+ñ]*@ciencias\\.unam\\.mx");
        Matcher m = p.matcher(correo);
        if(m.matches()){
            Usuario u = Usuario.builder().correo(correo)
                    .nombreUsuario(nombreUsuario)
                    .fechaNacimiento(fechaNacimiento)
                    .esAdmin(esAdmin)
                    .contrasena(contrasena).build();
            UsuarioDAO udao = new UsuarioDAO();
            udao.guardar(u);
            return "index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo inválido"));
            return "";
        }        
    }
    
}
