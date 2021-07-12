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
@Table(name = "tbl_Contas_Diversas")
public class Contas_Diversas_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fornecedor", nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    private String fornecedor;
    @Column(name = "numeroDocumento", nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private String numDocumento;
    @Column(name = "descricao", nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    private String descricao;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;

    @Column(name = "valorDocumento", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double valorDocumento;
    @Column(name = "observacoes", nullable = true, columnDefinition = "VARCHAR(250)")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "dataPagamento")
    private Caixa_Model dataPagamento;

    public Contas_Diversas_Model() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Double getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(Double valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Caixa_Model getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Caixa_Model dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Contas_Diversas_Model{" + "id=" + id + ", fornecedor=" + fornecedor + ", numDocumento=" + numDocumento + ", descricao=" + descricao + ", dataEmissao=" + dataEmissao + ", dataVencimento=" + dataVencimento + ", valorDocumento=" + valorDocumento + ", observacoes=" + observacoes + ", dataPagamento=" + dataPagamento + '}';
    }

}
