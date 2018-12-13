/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.services;

import java.util.List;
import mil.agro.dao.entidades.EstadoCampo;
import mil.agro.dao.excepciones.EstadoCampoException;
import mil.agro.dao.resource.CamposSessionManager;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author BETANIA
 */
public class EstadoCampoService {

    public EstadoCampoService() {
    }

    public static List<EstadoCampo> getEstados() throws EstadoCampoException {
        Session session = null;

        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            List<EstadoCampo> lista = session.createQuery("FROM EstdoCampo o").list();
            return lista;
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new EstadoCampoException("Imposible obtener Estados", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static EstadoCampo getEstado(int idEstado) throws EstadoCampoException {
        Session session = null;

        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            return (EstadoCampo) session.createQuery("FROM EstadoCampo o WHERE o.idEstadoCampo  = " + idEstado).uniqueResult();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new EstadoCampoException("Imposible obtener Estado", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
