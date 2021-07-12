/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.ItensCompra_idClass;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Claudio Xavier
 */
@Entity
@Table(name = "Itens_Compra")
@IdClass(ItensCompra_idClass.class)
public class Itens_Compra implements Serializable {

    @Id
    @Column(name = "numItem", columnDefinition = "INT")
    private Integer numItem;
    @Column(name = "valorItem", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorItem;
    @Column(name = "quantidadeSolicitada", nullable = false, columnDefinition = "INT")
    private int quantidadeSolicitada;

    @Column(name = "quantidadeAtendida", nullable = false, columnDefinition = "INT")
    private int quantidadeAtendida;

    @Id
    @ManyToOne
    @JoinColumn(name = "compra")
    private Compra_Model compra;

    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto_Model produto;

    public Itens_Compra() {
    }

    public Integer getNumItem() {
        return numItem;
    }

    public void setNumItem(Integer numItem) {
        this.numItem = numItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public int getQuantidadeAtendida() {
        return quantidadeAtendida;
    }

    public void setQuantidadeAtendida(int quantidadeAtendida) {
        this.quantidadeAtendida = quantidadeAtendida;
    }

    public Compra_Model getCompra() {
        return compra;
    }

    public void setCompra(Compra_Model compra) {
        this.compra = compra;
    }

    public Produto_Model getProduto() {
        return produto;
    }

    public void setProduto(Produto_Model produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.numItem);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.valorItem) ^ (Double.doubleToLongBits(this.valorItem) >>> 32));
        hash = 19 * hash + this.quantidadeSolicitada;
        hash = 19 * hash + this.quantidadeAtendida;
        hash = 19 * hash + Objects.hashCode(this.compra);
        hash = 19 * hash + Objects.hashCode(this.produto);
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
        final Itens_Compra other = (Itens_Compra) obj;
        if (Double.doubleToLongBits(this.valorItem) != Double.doubleToLongBits(other.valorItem)) {
            return false;
        }
        if (this.quantidadeSolicitada != other.quantidadeSolicitada) {
            return false;
        }
        if (this.quantidadeAtendida != other.quantidadeAtendida) {
            return false;
        }
        if (!Objects.equals(this.numItem, other.numItem)) {
            return false;
        }
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Itens_Compra{" + "numItem=" + numItem + ", valorItem=" + valorItem + ", quantidadeSolicitada=" + quantidadeSolicitada + ", quantidadeAtendida=" + quantidadeAtendida + ", compra=" + compra + ", produto=" + produto + '}';
    }

}
