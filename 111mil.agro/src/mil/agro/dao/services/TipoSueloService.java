/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.services;


import java.util.List;
import mil.agro.dao.entidades.TipoSuelo;
import mil.agro.dao.excepciones.TipoSueloException;
import mil.agro.dao.resource.CamposSessionManager;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author BETANIA
 */
public class TipoSueloService {
    
    public TipoSueloService() {
    }

    
     
      public static List<TipoSuelo> getTipos() throws TipoSueloException {
        Session session = null;
        
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            List<TipoSuelo> lista = session.createQuery("FROM TipoSuelo o").list();
            return lista;
        } catch(HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new TipoSueloException("Imposible obtener Tipos",e );
        } finally {
            if (session != null) session.close();
        }
    }
     
         public static TipoSuelo getTipo(int idTipo) throws TipoSueloException {
        Session session = null;
        
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            return (TipoSuelo) session.createQuery("FROM TipoSuelo o WHERE o.idTipoSuelo  = "+idTipo).uniqueResult();
        } catch(HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new TipoSueloException("Imposible obtener Tipo",e );
        } finally {
            if (session != null) session.close();
        }
    }

}
