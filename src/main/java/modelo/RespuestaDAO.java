/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author dixego
 */
public class RespuestaDAO {
    SessionFactory sf;
    
    public RespuestaDAO() {
        this.sf = HibernateUtil.getSessionFactory();
    }
    
        public void guardar(Respuesta u) throws Exception{
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
            throw new Exception("Error de base de datos");
        } finally {
            s.close();
        }
    }
    
    public Respuesta buscarPregunta(int id) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        Respuesta result = null;
        
        try {
            tx = s.beginTransaction();
            result = (Respuesta) s.get(Respuesta.class, id);
            tx.commit();
        } catch (Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
            throw new Exception("Error de base de datos");
        } finally {
            s.close();
        }
        return result;
    }
    
    public List<Respuesta> buscarRespuestasPorPregunta(int id) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        List<Respuesta> result = null;
        
        try {
            tx = s.beginTransaction();
            String hql = "from Respuesta where idPregunta = :id";
            result = (List<Respuesta>) s.createQuery(hql).setParameter("id", id).list();
        } catch (Exception ex) {
            throw new Exception("Error de base de datos");
            
        } finally {
            s.close();
        }
        return result;
    }
    
    public void borrar(int id) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();
            s.delete(s.get(Respuesta.class, id));
            tx.commit();
        } catch(Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
            throw new Exception("Error de base de datos");
        } finally {
            s.close();
        }
    }
    
    public void borrar(Respuesta r) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();
            s.delete(r);
            tx.commit();
        } catch(Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
            throw new Exception("Error de base de datos");
        } finally {
            s.close();
        }
    }
    
    public void borrarRespuestasPorPregunta(int id) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();
            String hql = "delete from Respuesta where idPregunta = :id";
            s.createQuery(hql).setParameter("id", id).executeUpdate();
            tx.commit();
        } catch(Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
            throw new Exception("Error de base de datos");
        } finally {
            s.close();
        }
    }
    
    public void borrarRespuestasPorUsuario(String correo) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();
            String hql = "delete from Respuesta where correoUsuario = :correo";
            s.createQuery(hql).setParameter("correo", correo).executeUpdate();
            tx.commit();
        } catch(Exception ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
            throw new Exception("Error de base de datos");
        } finally {
            s.close();
        }
    }
    
}
