/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Betania
 */
@Entity
@Table(name = "lote")
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lote")
    private Integer idLote;
    @Basic(optional = false)
    @Column(name = "nro_lote")
    private int nroLote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superficie")
    private BigDecimal superficie;
    @JoinColumn(name = "id_campo", referencedColumnName = "id_campo")
    @ManyToOne
    private Campo campo;
    @JoinColumn(name = "id_tipo_suelo", referencedColumnName = "id_tipo_suelo")
    @ManyToOne
    private TipoSuelo tipoSuelo;

    public Lote() {
    }

    public Lote(Integer idLote) {
        this.idLote = idLote;
    }

    public Lote(Integer idLote, int nroLote) {
        this.idLote = idLote;
        this.nroLote = nroLote;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public int getNroLote() {
        return nroLote;
    }

    public void setNroLote(int nroLote) {
        this.nroLote = nroLote;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public TipoSuelo getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(TipoSuelo tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(nroLote);
    }
    
}
