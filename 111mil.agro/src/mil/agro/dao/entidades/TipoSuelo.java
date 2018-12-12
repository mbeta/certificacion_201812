/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Betania
 */
@Entity
@Table(name = "tipo_suelo")
public class TipoSuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_suelo")
    private Integer idTipoSuelo;
    @Column(name = "descripcion")
    private String descripcion;


    public TipoSuelo() {
    }

    public TipoSuelo(Integer idTipoSuelo) {
        this.idTipoSuelo = idTipoSuelo;
    }

    public Integer getIdTipoSuelo() {
        return idTipoSuelo;
    }

    public void setIdTipoSuelo(Integer idTipoSuelo) {
        this.idTipoSuelo = idTipoSuelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSuelo != null ? idTipoSuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSuelo)) {
            return false;
        }
        TipoSuelo other = (TipoSuelo) object;
        if ((this.idTipoSuelo == null && other.idTipoSuelo != null) || (this.idTipoSuelo != null && !this.idTipoSuelo.equals(other.idTipoSuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
