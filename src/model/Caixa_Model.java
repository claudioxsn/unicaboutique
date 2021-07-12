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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "tbl_Caixa")
public class Caixa_Model implements Serializable {

    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAbertura;
    @Column(name = "saldoAbertura", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double saldoAbertura;
    @Column(name = "saldoEntrada", nullable = true, columnDefinition = "DECIMAL(10,2)")
    private double saldoEntrada;
    @Column(name = "saldoSaida", nullable = true, columnDefinition = "DECIMAL(10,2)")
    private double saldoSaida;
    @Column(name = "status", nullable = false)
    private boolean status;

    public Caixa_Model() {
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getSaldoAbertura() {
        return saldoAbertura;
    }

    public void setSaldoAbertura(double saldoAbertura) {
        this.saldoAbertura = saldoAbertura;
    }

    public double getSaldoEntrada() {
        return saldoEntrada;
    }

    public void setSaldoEntrada(double saldoEntrada) {
        this.saldoEntrada = saldoEntrada;
    }

    public double getSaldoSaida() {
        return saldoSaida;
    }

    public void setSaldoSaida(double saldoSaida) {
        this.saldoSaida = saldoSaida;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Caixa_Model{" + "dataAbertura=" + dataAbertura + ", saldoAbertura=" + saldoAbertura + ", saldoEntrada=" + saldoEntrada + ", saldoSaida=" + saldoSaida + ", status=" + status + '}';
    }

}
