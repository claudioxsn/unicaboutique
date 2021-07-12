/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "tbl_empresa")
public class Empresa_Model implements Serializable {

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
    @Column(name = "cep", length = 9, nullable = true, columnDefinition = "VARCHAR(10)")
    private String cep;
    @Column(name = "estado", length = 2, nullable = false, columnDefinition = "VARCHAR(2)")
    private String estado;
    @Column(name = "endereco", length = 80, nullable = false, columnDefinition = "VARCHAR(80)")
    private String endereco;
    @Column(name = "numero", nullable = false, columnDefinition = "VARCHAR(10)")
    private String numero;
    @Column(name = "bairro", nullable = false, columnDefinition = "VARCHAR(60)")
    private String bairro;
    @Column(name = "telefone", nullable = false, columnDefinition = "VARCHAR(13)")
    private String telefone;
    @Column(name = "celular", nullable = false, columnDefinition = "VARCHAR(14)")
    private String celular;
    @Column(name = "email", nullable = true, columnDefinition = "VARCHAR(50)")
    private String email;
    private String imagem;

    public Empresa_Model() {
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.razaoSocial);
        hash = 59 * hash + Objects.hashCode(this.nomeFantasia);
        hash = 59 * hash + Objects.hashCode(this.cnpj);
        hash = 59 * hash + Objects.hashCode(this.cidade);
        hash = 59 * hash + Objects.hashCode(this.cep);
        hash = 59 * hash + Objects.hashCode(this.estado);
        hash = 59 * hash + Objects.hashCode(this.endereco);
        hash = 59 * hash + Objects.hashCode(this.numero);
        hash = 59 * hash + Objects.hashCode(this.bairro);
        hash = 59 * hash + Objects.hashCode(this.telefone);
        hash = 59 * hash + Objects.hashCode(this.celular);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.imagem);
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
        final Empresa_Model other = (Empresa_Model) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Empresa_Model{" + "id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + ", cidade=" + cidade + ", cep=" + cep + ", estado=" + estado + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", telefone=" + telefone + ", celular=" + celular + ", email=" + email + ", imagem=" + imagem + '}';
    }

}
