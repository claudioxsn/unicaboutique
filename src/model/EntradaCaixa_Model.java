/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.EntradaCaixa_idClass;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "tbl_entrada_Caixa")
@IdClass(EntradaCaixa_idClass.class)
public class EntradaCaixa_Model implements Serializable {

    @Id
    @Column(name = "numEntrada", columnDefinition = "INT")
    private Integer numEntrada;

    @Id
    @ManyToOne
    @JoinColumn(name = "caixa")
    private Caixa_Model caixa;

    @Column(name = "motivoEntrada", nullable = false, columnDefinition = "VARCHAR(250)")
    private String motivoEntrada;
    @Column(name = "valor", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valor;

    public EntradaCaixa_Model() {
    }

    public Integer getNumEntrada() {
        return numEntrada;
    }

    public void setNumEntrada(Integer numEntrada) {
        this.numEntrada = numEntrada;
    }

    public Caixa_Model getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa_Model caixa) {
        this.caixa = caixa;
    }

    public String getMotivoEntrada() {
        return motivoEntrada;
    }

    public void setMotivoEntrada(String motivoEntrada) {
        this.motivoEntrada = motivoEntrada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.numEntrada);
        hash = 41 * hash + Objects.hashCode(this.caixa);
        hash = 41 * hash + Objects.hashCode(this.motivoEntrada);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntradaCaixa_Model other = (EntradaCaixa_Model) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.motivoEntrada, other.motivoEntrada)) {
            return false;
        }
        if (!Objects.equals(this.numEntrada, other.numEntrada)) {
            return false;
        }
        if (!Objects.equals(this.caixa, other.caixa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntradaCaixa_Model{" + "numEntrada=" + numEntrada + ", caixa=" + caixa + ", motivoEntrada=" + motivoEntrada + ", valor=" + valor + '}';
    }


}
