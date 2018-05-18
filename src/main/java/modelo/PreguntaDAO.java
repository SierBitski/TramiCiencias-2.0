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
    
    public void guardar(Pregunta u) throws Exception{
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
    
    public Pregunta buscarPregunta(int id) throws Exception {
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
            throw new Exception("Error de base de datos");            
        } finally {
            s.close();
        }
        return result;
    }
    
    public List<Respuesta> buscarRespuestas(int id) throws Exception {
        Session s = sf.openSession();
        Transaction tx = null;
        List<Respuesta> result = null;
        
        try {
            tx = s.beginTransaction();
            String hql = "from Respuesta where id_pregunta = :id";
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
            s.delete(s.get(Pregunta.class, new Integer(id)));
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

    public List<Pregunta> buscarTitulo(String abuscar) throws Exception{
      List<Pregunta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql;
            hql = "from Pregunta where titulo like :abuscar";
            Query query = session.createQuery(hql).setParameter("abuscar", "%" + abuscar + "%");
            result = (List<Pregunta>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
           throw new Exception("Error de base de datos");
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    
    public List<Pregunta> buscarPorUsuario(String correoUsuario) throws Exception{
      List<Pregunta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql;
            hql = "from Pregunta where correoUsuario = :correo";
            Query query = session.createQuery(hql).setParameter("correo", correoUsuario);
            result = (List<Pregunta>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
           throw new Exception("Error de base de datos");
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    
    public List<Pregunta> preguntas() throws Exception {
        List<Pregunta> result = null;
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql;
            hql = "from Pregunta";
            Query query = session.createQuery(hql);
            result = (List<Pregunta>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
           throw new Exception("Error de base de datos");
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    
}
