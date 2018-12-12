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
import mil.agro.dao.resource.CamposSessionManager;
import org.hibernate.FlushMode;
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
                
                
            }
            
            
        } catch(Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new CampoException("Imposible guardar nueva Agente",e );
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                session.close();
            }
        }
    }
     
   

}
