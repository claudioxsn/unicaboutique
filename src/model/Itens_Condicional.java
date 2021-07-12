/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.ItensCondicional_idClass;
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
@Table(name = "tbl_itensCondicional")
@IdClass(ItensCondicional_idClass.class)
public class Itens_Condicional implements Serializable {

    @Id
    @Column(name = "numItem", nullable = false, columnDefinition = "INT")
    private int numItem;
    @Column(name = "valorItem", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double valorItem;
    @Column(name = "quantidade", nullable = false, columnDefinition = "INT")
    private int quantidade;

    @Id
    @ManyToOne
    @JoinColumn(name = "Condicional")
    private Condicional_Model Condicional;

    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto_Model produto;

    public Itens_Condicional() {
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Condicional_Model getCondicional() {
        return Condicional;
    }

    public void setCondicional(Condicional_Model Condicional) {
        this.Condicional = Condicional;
    }

    public Produto_Model getProduto() {
        return produto;
    }

    public void setProduto(Produto_Model produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.numItem;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.valorItem) ^ (Double.doubleToLongBits(this.valorItem) >>> 32));
        hash = 19 * hash + this.quantidade;
        hash = 19 * hash + Objects.hashCode(this.Condicional);
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
        final Itens_Condicional other = (Itens_Condicional) obj;
        if (this.numItem != other.numItem) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorItem) != Double.doubleToLongBits(other.valorItem)) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.Condicional, other.Condicional)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Itens_Condicional{" + "numItem=" + numItem + ", valorItem=" + valorItem + ", quantidade=" + quantidade + ", Condicional=" + Condicional + ", produto=" + produto + '}';
    }

}
