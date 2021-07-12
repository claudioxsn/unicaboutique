/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
@Table(name = "tbl_fornecedor")
public class Fornecedor_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "razaosocial", length = 60, nullable = false, columnDefinition = "VARCHAR(60)")
    private String razaoSocial;

    @Column(name = "nomefantasia", length = 60, nullable = true, columnDefinition = "VARCHAR(60)")
    private String nomeFantasia;
    @Column(name = "cnpj", length = 18, nullable = true, columnDefinition = "VARCHAR(18)")
    private String cnpj;
    @Column(name = "cidade", length = 50, nullable = false, columnDefinition = "VARCHAR(50)")
    private String cidade;
    @Column(name = "estado", length = 2, nullable = false, columnDefinition = "VARCHAR(2)")
    private String estado;
    @Column(name = "cep", length = 10, nullable = true, columnDefinition = "VARCHAR(10)")
    private String cep;
    @Column(name = "rua", length = 50, nullable = false, columnDefinition = "VARCHAR(50)")
    private String rua;
    @Column(name = "numero", length = 6, nullable = false, columnDefinition = "VARCHAR(6)")
    private String numero;
    @Column(name = "bairro", nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private String bairro;
    @Column(name = "complemento", nullable = true, columnDefinition = "VARCHAR(30)")
    private String complemento;
    @Column(name = "contato", nullable = false, columnDefinition = "VARCHAR(30)")
    private String contato;
    @Column(name = "telefone", nullable = false, columnDefinition = "VARCHAR(13)")
    private String telefone;
    @Column(name = "email", nullable = true, columnDefinition = "VARCHAR(50)")
    private String email;

    public Fornecedor_Model() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fornecedor_Model{" + "id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", complemento=" + complemento + ", contato=" + contato + ", telefone=" + telefone + ", email=" + email + '}';
    }

}
