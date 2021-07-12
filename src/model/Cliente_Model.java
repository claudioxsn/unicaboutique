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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "tbl_Cliente")
public class Cliente_Model extends Pessoa implements Serializable {

    @Column(name = "dataCadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "ultimaAtualizacao", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultimaAtualizacao;

    public Cliente_Model() {
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @Override
    public String toString() {
        return "Cliente_Model{" + "dataCadastro=" + dataCadastro + ", ultimaAtualizacao=" + ultimaAtualizacao + '}';
    }

}
