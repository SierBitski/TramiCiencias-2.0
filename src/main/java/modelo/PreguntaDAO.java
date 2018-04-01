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
public class PreguntaDAO {
    SessionFactory sf;
    
    public PreguntaDAO() {
        this.sf = HibernateUtil.getSessionFactory();
    }
    
    public void guardar(Pregunta u){
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
    
    public Pregunta buscarPregunta(int id) {
        Session s = sf.openSession();
        Transaction tx = null;
        Pregunta result = null;
        
        try {
            tx = s.beginTransaction();
            result = (Pregunta) s.get(Pregunta.class, id);
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
    
    public List<Respuesta> buscarRespuestas(int id) {
        Session s = sf.openSession();
        Transaction tx = null;
        List<Respuesta> result = null;
        
        try {
            tx = s.beginTransaction();
            String hql = "from Respuesta where id_pregunta = :id";
            result = (List<Respuesta>) s.createQuery(hql).setParameter("id", id).list();
        } catch (Exception ex) {
            
        } finally {
            s.close();
        }
        return result;
    }
    
    public void borrar(int id) {
        Session s = sf.openSession();
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();
            s.delete(s.get(Pregunta.class, new Integer(id)));
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
