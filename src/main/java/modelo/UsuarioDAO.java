/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author dixego
 */
public class UsuarioDAO {
    SessionFactory sf;
    
    public UsuarioDAO() {
        this.sf = HibernateUtil.getSessionFactory();
    }
    
    public void guardar(Usuario u){
        Session s = sf.openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.persist(u);
            tx.commit();
        } catch(Exception ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }
    
    public Usuario buscar(String id) {
        Session s = sf.openSession();
        Transaction tx = null;
        Usuario result = null;
        
        try {
            tx = s.beginTransaction();
            result = (Usuario) s.get(Usuario.class, id);
            tx.commit();
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
        return result;
    }
    
    public Usuario buscar(String id, String psswd) {
        Session s = sf.openSession();
        Transaction tx = null;
        Usuario result = null;
        
        try {
            tx = s.beginTransaction();
            result = (Usuario) s.get(Usuario.class, id);
            
            if(!result.getContrasena().equals(psswd)) {
                result = null;
            }
            
            tx.commit();
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
        return result;
    }
    
    public void borrar(String id) {
        Session s = sf.openSession();
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();
            s.delete(s.get(Usuario.class, id));
            tx.commit();
        } catch(Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }
    
}
