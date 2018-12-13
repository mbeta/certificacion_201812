/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.services;

import java.util.List;
import mil.agro.dao.entidades.Lote;
import mil.agro.dao.excepciones.LoteException;
import mil.agro.dao.resource.CamposSessionManager;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author BETANIA
 */
public class LoteService {

    public LoteService() {
    }

    public static void nuevo(Lote object) throws LoteException {
        Session session = null;
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction();
            int id = (int) session.save(object);
            object.setIdLote(id);

        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new LoteException("Imposible guardar nuevo Lote", e);
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                session.close();
            }
        }
    }

    public static List<Lote> getLotes(int idCampo) throws LoteException {
        Session session = null;
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            List<Lote> lista = session.createQuery("FROM Lote o WHERE o.campo.idCampo  = " + idCampo  +" ORDER BY o.nroLote").list();
            return lista;
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new LoteException("Imposible obtener Lotes", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Lote getLote(int idLote) throws LoteException {
        Session session = null;
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            return (Lote) session.createQuery("FROM Lote o WHERE o.idLote  = " + idLote).uniqueResult();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new LoteException("Imposible obtener Lote", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Boolean existeNro(int idCampo, int nro) throws LoteException {
        Session session = null;
        try {
            session = CamposSessionManager.getSession();
            session.beginTransaction().commit();
            session.setFlushMode(FlushMode.COMMIT);
            session.flush();
            List<Lote> lista = session.createQuery("FROM Lote o WHERE o.idCampo = " + idCampo + " AND o.nroLote = " + nro).list();
            return !lista.isEmpty();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new LoteException("Imposible obtener Consulta", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
