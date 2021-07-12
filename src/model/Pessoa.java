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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nomeCliente", length = 60, nullable = false, columnDefinition = "VARCHAR(60)")
    private String nome;
    @Column(name = "cpf", length = 14, nullable = true, columnDefinition = "VARCHAR(14)")
    private String cpf;
    @Column(name = "rg", length = 14, nullable = true, columnDefinition = "VARCHAR(14)")
    private String rg;
    @Column(name = "dataNascimento", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "sexo", nullable = false, columnDefinition = "VARCHAR(9)")
    private String sexo;
    @Column(name = "rua", nullable = false, columnDefinition = "VARCHAR(60)")
    private String rua;
    @Column(name = "numero", nullable = false, columnDefinition = "VARCHAR(6)")
    private String numero;
    @Column(name = "bairro", nullable = true, columnDefinition = "VARCHAR(50)")
    private String bairro;
    @Column(name = "complemento", nullable = true, columnDefinition = "VARCHAR(30)")
    private String complemento;
    @Column(name = "cidade", nullable = true, columnDefinition = "VARCHAR(60)")
    private String cidade;
    @Column(name = "estado", nullable = true, columnDefinition = "VARCHAR(2)")
    private String estado;
    @Column(name = "cep", nullable = true, columnDefinition = "VARCHAR(10)")
    private String cep;
    @Column(name = "telefoneResidencial", nullable = true, columnDefinition = "VARCHAR(13)")
    private String telefoneResidencial;
    @Column(name = "telefoneComercial", nullable = true, columnDefinition = "VARCHAR(13)")
    private String telefoneComercial;
    @Column(name = "celularWhatsApp", nullable = false, columnDefinition = "VARCHAR(14)")
    private String whatsApp;
    @Column(name = "celular", nullable = true, columnDefinition = "VARCHAR(14)")
    private String celular;
    @Column(name = "observacoes", nullable = false, columnDefinition = "VARCHAR(255)")
    private String observacoes;

    public Pessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", complemento=" + complemento + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", telefoneResidencial=" + telefoneResidencial + ", telefoneComercial=" + telefoneComercial + ", whatsApp=" + whatsApp + ", celular=" + celular + ", observacoes=" + observacoes + '}';
    }

}
