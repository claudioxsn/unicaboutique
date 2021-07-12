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
@Table(name = "tbl_produto")
public class Produto_Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    @Column(name = "tamanho", nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    private String tamanho;
    @Column(name = "cor", columnDefinition = "VARCHAR(30)")
    private String cor;
    @Column(name = "marcaProduto", nullable = true, length = 60, columnDefinition = "VARCHAR(60)")
    private String marca;
    @Column(name = "modeloProduto", nullable = true, length = 60, columnDefinition = "VARCHAR(60)")
    private String modelo;
    @Column(name = "descricaoProduto", nullable = true, length = 60, columnDefinition = "VARCHAR(60)")
    private String descricaoProduto;
    @Column(name = "valorVenda", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorVenda;
    @Column(name = "qtdEstoque", nullable = false, columnDefinition = "INT")
    private Integer qtdEstoque;
    @Column(name = "qtdMinima", nullable = false, columnDefinition = "INT")
    private Integer qtdMinima;
    @Column(name = "codbar", nullable = true, columnDefinition = "VARCHAR(60)")
    private String codBar;

    public Produto_Model() {
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idProduto);
        hash = 59 * hash + Objects.hashCode(this.tamanho);
        hash = 59 * hash + Objects.hashCode(this.marca);
        hash = 59 * hash + Objects.hashCode(this.modelo);
        hash = 59 * hash + Objects.hashCode(this.descricaoProduto);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.valorVenda) ^ (Double.doubleToLongBits(this.valorVenda) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.qtdEstoque);
        hash = 59 * hash + Objects.hashCode(this.qtdMinima);
        hash = 59 * hash + Objects.hashCode(this.codBar);
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
        final Produto_Model other = (Produto_Model) obj;
        if (Double.doubleToLongBits(this.valorVenda) != Double.doubleToLongBits(other.valorVenda)) {
            return false;
        }
        if (!Objects.equals(this.tamanho, other.tamanho)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.descricaoProduto, other.descricaoProduto)) {
            return false;
        }
        if (!Objects.equals(this.codBar, other.codBar)) {
            return false;
        }
        if (!Objects.equals(this.idProduto, other.idProduto)) {
            return false;
        }
        if (!Objects.equals(this.qtdEstoque, other.qtdEstoque)) {
            return false;
        }
        if (!Objects.equals(this.qtdMinima, other.qtdMinima)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto_Model{" + "idProduto=" + idProduto + ", tamanho=" + tamanho + ", marca=" + marca + ", modelo=" + modelo + ", descricaoProduto=" + descricaoProduto + ", valorVenda=" + valorVenda + ", qtdEstoque=" + qtdEstoque + ", qtdMinima=" + qtdMinima + ", codBar=" + codBar + '}';
    }

}
