/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.PagamentoCompra_idClass;
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
@Table(name = "tbl_pagamentoCompra")
@IdClass(PagamentoCompra_idClass.class)
public class PagamentoCompra_Model implements Serializable {

    @Id
    @Column(name = "numBoleto", nullable = false, columnDefinition = "VARCHAR(50)")
    private String numBoleto;

    @Column(name = "valorBoleto", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorBoleto;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;

    @Id
    @ManyToOne
    @JoinColumn(name = "compra")
    private Compra_Model compra;

    @ManyToOne
    @JoinColumn(name = "dataPagamento", nullable = true)
    private Caixa_Model dataPagamento;

    public PagamentoCompra_Model() {
    }

    public String getNumBoleto() {
        return numBoleto;
    }

    public void setNumBoleto(String numBoleto) {
        this.numBoleto = numBoleto;
    }

    public double getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(double valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Compra_Model getCompra() {
        return compra;
    }

    public void setCompra(Compra_Model compra) {
        this.compra = compra;
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
        hash = 53 * hash + Objects.hashCode(this.numBoleto);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.valorBoleto) ^ (Double.doubleToLongBits(this.valorBoleto) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.dataEmissao);
        hash = 53 * hash + Objects.hashCode(this.dataVencimento);
        hash = 53 * hash + Objects.hashCode(this.compra);
        hash = 53 * hash + Objects.hashCode(this.dataPagamento);
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
        final PagamentoCompra_Model other = (PagamentoCompra_Model) obj;
        if (Double.doubleToLongBits(this.valorBoleto) != Double.doubleToLongBits(other.valorBoleto)) {
            return false;
        }
        if (!Objects.equals(this.numBoleto, other.numBoleto)) {
            return false;
        }
        if (!Objects.equals(this.dataEmissao, other.dataEmissao)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        if (!Objects.equals(this.dataPagamento, other.dataPagamento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PagamentoCompra_Model{" + "numBoleto=" + numBoleto + ", valorBoleto=" + valorBoleto + ", dataEmissao=" + dataEmissao + ", dataVencimento=" + dataVencimento + ", compra=" + compra + ", dataPagamento=" + dataPagamento + '}';
    }

}
