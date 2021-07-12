/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Claudio Xavier
 */

@Entity
@Table(name = "tbl_Devolucao")
public class Devolucao_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numDevolucao;
    private Date dataDevolucao;
    @Column(name = "valorDevolucao", columnDefinition = "DECIMAL(10,2)")
    private double valorDevolucao;

    private Cliente_Model cliente;
    private Funcionario_Model funcionario;

    public Devolucao_Model() {
    }

    public Integer getNumDevolucao() {
        return numDevolucao;
    }

    public void setNumDevolucao(Integer numDevolucao) {
        this.numDevolucao = numDevolucao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValorDevolucao() {
        return valorDevolucao;
    }

    public void setValorDevolucao(double valorDevolucao) {
        this.valorDevolucao = valorDevolucao;
    }

    public Cliente_Model getCliente() {
        return cliente;
    }

    public void setCliente(Cliente_Model cliente) {
        this.cliente = cliente;
    }

    public Funcionario_Model getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario_Model funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.numDevolucao);
        hash = 89 * hash + Objects.hashCode(this.dataDevolucao);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.valorDevolucao) ^ (Double.doubleToLongBits(this.valorDevolucao) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.cliente);
        hash = 89 * hash + Objects.hashCode(this.funcionario);
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
        final Devolucao_Model other = (Devolucao_Model) obj;
        if (Double.doubleToLongBits(this.valorDevolucao) != Double.doubleToLongBits(other.valorDevolucao)) {
            return false;
        }
        if (!Objects.equals(this.numDevolucao, other.numDevolucao)) {
            return false;
        }
        if (!Objects.equals(this.dataDevolucao, other.dataDevolucao)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Devolucao_Model{" + "numDevolucao=" + numDevolucao + ", dataDevolucao=" + dataDevolucao + ", valorDevolucao=" + valorDevolucao + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

}
