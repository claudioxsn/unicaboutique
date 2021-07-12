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
@Table(name = "tbl_Condicional")
public class Condicional_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numCondicional;
    @Column(name = "valorCondicional", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorCondicional;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCondicional;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDevolucao;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date prazoDevolucao;

    @Column(name = "statusCondicional", nullable = false, columnDefinition = "VARCHAR(30)")
    private String statusCondicional;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente_Model cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor")
    private Funcionario_Model vendedor;

    public Condicional_Model() {
    }

    public Integer getNumCondicional() {
        return numCondicional;
    }

    public void setNumCondicional(Integer numCondicional) {
        this.numCondicional = numCondicional;
    }

    public double getValorCondicional() {
        return valorCondicional;
    }

    public void setValorCondicional(double valorCondicional) {
        this.valorCondicional = valorCondicional;
    }

    public Date getDataCondicional() {
        return dataCondicional;
    }

    public void setDataCondicional(Date dataCondicional) {
        this.dataCondicional = dataCondicional;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public void setPrazoDevolucao(Date prazoDevolucao) {
        this.prazoDevolucao = prazoDevolucao;
    }

    public String getStatusCondicional() {
        return statusCondicional;
    }

    public void setStatusCondicional(String statusCondicional) {
        this.statusCondicional = statusCondicional;
    }

    public Cliente_Model getCliente() {
        return cliente;
    }

    public void setCliente(Cliente_Model cliente) {
        this.cliente = cliente;
    }

    public Funcionario_Model getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario_Model vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Condicional_Model{" + "numCondicional=" + numCondicional + ", valorCondicional=" + valorCondicional + ", dataCondicional=" + dataCondicional + ", dataDevolucao=" + dataDevolucao + ", prazoDevolucao=" + prazoDevolucao + ", statusCondicional=" + statusCondicional + ", cliente=" + cliente + ", vendedor=" + vendedor + '}';
    }

}
