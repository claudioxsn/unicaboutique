/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.SaidaCaixa_idClass;
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
@Table(name = "tbl_SaidaCaixa")
@IdClass(SaidaCaixa_idClass.class)
public class SaidaCaixa_Model implements Serializable {

    @Id
    @Column(name = "numSaida", nullable = false, columnDefinition = "INT")
    private Integer numSaida;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "caixa")
    private Caixa_Model caixa;
    
    @ManyToOne
    @JoinColumn(name = "funcionarioResponsavel")
    private Funcionario_Model funcionarioResponsavel;
    
    @Column(name = "motivoSaida", nullable = false, columnDefinition = "VARCHAR(250)")
    private String motivoSaida;
    
    @Column(name = "valor", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valor;

    public SaidaCaixa_Model() {
    }

    public Integer getNumSaida() {
        return numSaida;
    }

    public void setNumSaida(Integer numSaida) {
        this.numSaida = numSaida;
    }

    public Caixa_Model getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa_Model caixa) {
        this.caixa = caixa;
    }

    public Funcionario_Model getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(Funcionario_Model funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    public String getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(String motivoSaida) {
        this.motivoSaida = motivoSaida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.numSaida);
        hash = 59 * hash + Objects.hashCode(this.caixa);
        hash = 59 * hash + Objects.hashCode(this.funcionarioResponsavel);
        hash = 59 * hash + Objects.hashCode(this.motivoSaida);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
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
        final SaidaCaixa_Model other = (SaidaCaixa_Model) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.motivoSaida, other.motivoSaida)) {
            return false;
        }
        if (!Objects.equals(this.numSaida, other.numSaida)) {
            return false;
        }
        if (!Objects.equals(this.caixa, other.caixa)) {
            return false;
        }
        if (!Objects.equals(this.funcionarioResponsavel, other.funcionarioResponsavel)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "SaidaCaixa_Model{" + "numSaida=" + numSaida + ", caixa=" + caixa + ", funcionarioResponsavel=" + funcionarioResponsavel + ", motivoSaida=" + motivoSaida + ", valor=" + valor + '}';
    }

}
