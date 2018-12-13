/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.services;


import java.util.List;
import mil.agro.dao.entidades.Campo;
import mil.agro.dao.entidades.Lote;
import mil.agro.dao.excepciones.CampoException;
import mil.agro.dao.excepciones.LoteException;
import mil.agro.dao.resource.CamposSessionManager;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author BETANIA
 */
public class CampoService {
    
    public CampoService() {
    }

     public static void nuevo(Campo object, List<Lote> lotes) throws CampoException {
        Session session = null;
        
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction();           
            int id = (int) session.save(object);
            object.setIdCampo(id);
            //registramos los lotes asociados
            for (Lote lote : lotes) {
                lote.setCampo(object);
                session.save(lote);
            }  
        } catch(HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new CampoException("Imposible guardar nuevo Campo",e );
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                session.close();
            }
        }
    }
     
      public static void nuevo(Campo object) throws CampoException {
        Session session = null;
        
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction();           
            int id = (int) session.save(object);
            object.setIdCampo(id);
            
        } catch(HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new CampoException("Imposible guardar nuevo Campo",e );
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                session.close();
            }
        }
    }
     
      public static Boolean existeNombre(String nombre) throws CampoException {
        Session session = null;

        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            List<Campo> lista = session.createQuery("FROM Campo o WHERE o.nombre = '"+nombre+"'").list();
            return !lista.isEmpty();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new CampoException("Imposible obtener Consulta", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
