/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "tbl_Compra")
public class Compra_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numCompra;
    @Column(name = "valorCompra", columnDefinition = "DECIMAL(10,2)")
    private double valorCompra;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCompra;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEntrega;
    @Column(name = "statusCompra", columnDefinition = "VARCHAR(30)")
    private String statusCompra;
    @Column(name = "numNota", columnDefinition = "VARCHAR(30)")
    private String numNota;

    @Column(name = "observacoes", columnDefinition = "VARCHAR(255)")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Fornecedor_Model fornecedor;

    public Compra_Model() {
    }

    public Integer getNumCompra() {
        return numCompra;
    }

    public void setNumCompra(Integer numCompra) {
        this.numCompra = numCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(String statusCompra) {
        this.statusCompra = statusCompra;
    }

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Fornecedor_Model getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor_Model fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Compra_Model{" + "numCompra=" + numCompra + ", valorCompra=" + valorCompra + ", dataCompra=" + dataCompra + ", dataEntrega=" + dataEntrega + ", statusCompra=" + statusCompra + ", numNota=" + numNota + ", observacoes=" + observacoes + ", fornecedor=" + fornecedor + '}';
    }

}
