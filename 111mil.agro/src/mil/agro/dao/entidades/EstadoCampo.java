/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Betania
 */
@Entity
@Table(name = "estado_campo")
public class EstadoCampo implements Serializable {
    
    public static int ID_ESTADO_CREADO = 1;
    public static int ID_ESTADO_PARC_TRABAJADO = 2;
    public static int ID_ESTADO_COMP_TRABAJADO = 3;
    public static int ID_ESTADO_DESUSO = 4;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_campo")
    private Integer idEstadoCampo;
    @Column(name = "descripcion")
    private String descripcion;

    public EstadoCampo() {
    }

    public EstadoCampo(Integer idEstadoCampo) {
        this.idEstadoCampo = idEstadoCampo;
    }

    public Integer getIdEstadoCampo() {
        return idEstadoCampo;
    }

    public void setIdEstadoCampo(Integer idEstadoCampo) {
        this.idEstadoCampo = idEstadoCampo;
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
        hash += (idEstadoCampo != null ? idEstadoCampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCampo)) {
            return false;
        }
        EstadoCampo other = (EstadoCampo) object;
        if ((this.idEstadoCampo == null && other.idEstadoCampo != null) || (this.idEstadoCampo != null && !this.idEstadoCampo.equals(other.idEstadoCampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
