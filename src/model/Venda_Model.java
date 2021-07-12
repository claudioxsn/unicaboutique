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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "tbl_venda")
public class Venda_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numVenda;
    @Column(name = "valorVenda", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorVenda;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVenda;

    @Column(name = "porcentagemDesconto", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double percentDesconto;

    @Column(name = "valorDesconto", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorDesconto;

    @Column(name = "valorComDesconto", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorComDesconto;

    @Column(name = "statusVenda", nullable = false, columnDefinition = "VARCHAR(30)")
    private String statusVenda;

    @Column(name = "tipoPagamento", columnDefinition = "VARCHAR(40)")
    private String tipoPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente_Model cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor")
    private Funcionario_Model funcionario;

    public Venda_Model() {
    }

    public Integer getNumVenda() {
        return numVenda;
    }

    public void setNumVenda(Integer numVenda) {
        this.numVenda = numVenda;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getPercentDesconto() {
        return percentDesconto;
    }

    public void setPercentDesconto(double percentDesconto) {
        this.percentDesconto = percentDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getValorComDesconto() {
        return valorComDesconto;
    }

    public void setValorComDesconto(double valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
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
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.numVenda);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.valorVenda) ^ (Double.doubleToLongBits(this.valorVenda) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.dataVenda);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.percentDesconto) ^ (Double.doubleToLongBits(this.percentDesconto) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.valorDesconto) ^ (Double.doubleToLongBits(this.valorDesconto) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.valorComDesconto) ^ (Double.doubleToLongBits(this.valorComDesconto) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.statusVenda);
        hash = 37 * hash + Objects.hashCode(this.tipoPagamento);
        hash = 37 * hash + Objects.hashCode(this.cliente);
        hash = 37 * hash + Objects.hashCode(this.funcionario);
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
        final Venda_Model other = (Venda_Model) obj;
        if (Double.doubleToLongBits(this.valorVenda) != Double.doubleToLongBits(other.valorVenda)) {
            return false;
        }
        if (Double.doubleToLongBits(this.percentDesconto) != Double.doubleToLongBits(other.percentDesconto)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorDesconto) != Double.doubleToLongBits(other.valorDesconto)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorComDesconto) != Double.doubleToLongBits(other.valorComDesconto)) {
            return false;
        }
        if (!Objects.equals(this.statusVenda, other.statusVenda)) {
            return false;
        }
        if (!Objects.equals(this.tipoPagamento, other.tipoPagamento)) {
            return false;
        }
        if (!Objects.equals(this.numVenda, other.numVenda)) {
            return false;
        }
        if (!Objects.equals(this.dataVenda, other.dataVenda)) {
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
        return "Venda_Model{" + "numVenda=" + numVenda + ", valorVenda=" + valorVenda + ", dataVenda=" + dataVenda + ", percentDesconto=" + percentDesconto + ", valorDesconto=" + valorDesconto + ", valorComDesconto=" + valorComDesconto + ", statusVenda=" + statusVenda + ", tipoPagamento=" + tipoPagamento + ", cliente=" + cliente + ", funcionario=" + funcionario + '}';
    }

}
