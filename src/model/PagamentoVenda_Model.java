/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.PagamentoVenda_idClass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "tbl_pagamentoVenda")
@IdClass(PagamentoVenda_idClass.class)
public class PagamentoVenda_Model implements Serializable {

    @Id
    @Column(name = "numParcela", nullable = false, columnDefinition = "INT")
    private Integer numParcela;
    @Column(name = "valorParcela", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorParcela;

    @Column(name = "tipoPagamento", columnDefinition = "VARCHAR(20)")
    private String tipoPagamento;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;

    @Id
    @ManyToOne
    @JoinColumn(name = "venda")
    private Venda_Model venda;

    @ManyToOne
    @JoinColumn(name = "dataPagamento")
    private Caixa_Model dataPagamento;

    public PagamentoVenda_Model() {
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Venda_Model getVenda() {
        return venda;
    }

    public void setVenda(Venda_Model venda) {
        this.venda = venda;
    }

    public Caixa_Model getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Caixa_Model dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.numParcela);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valorParcela) ^ (Double.doubleToLongBits(this.valorParcela) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.tipoPagamento);
        hash = 67 * hash + Objects.hashCode(this.dataVencimento);
        hash = 67 * hash + Objects.hashCode(this.venda);
        hash = 67 * hash + Objects.hashCode(this.dataPagamento);
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
        final PagamentoVenda_Model other = (PagamentoVenda_Model) obj;
        if (Double.doubleToLongBits(this.valorParcela) != Double.doubleToLongBits(other.valorParcela)) {
            return false;
        }
        if (!Objects.equals(this.tipoPagamento, other.tipoPagamento)) {
            return false;
        }
        if (!Objects.equals(this.numParcela, other.numParcela)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        if (!Objects.equals(this.dataPagamento, other.dataPagamento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PagamentoVenda_Model{" + "numParcela=" + numParcela + ", valorParcela=" + valorParcela + ", tipoPagamento=" + tipoPagamento + ", dataVencimento=" + dataVencimento + ", venda=" + venda + ", dataPagamento=" + dataPagamento + '}';
    }

}
