/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idClass;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Claudio Xavier
 */
public class PagamentoVenda_idClass implements Serializable{

    private Integer numParcela;
    private Integer venda;

    public PagamentoVenda_idClass() {
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public Integer getVenda() {
        return venda;
    }

    public void setVenda(Integer venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.numParcela);
        hash = 17 * hash + Objects.hashCode(this.venda);
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
        final PagamentoVenda_idClass other = (PagamentoVenda_idClass) obj;
        if (!Objects.equals(this.numParcela, other.numParcela)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PagamentoVenda_idClass{" + "numParcela=" + numParcela + ", venda=" + venda + '}';
    }

}
