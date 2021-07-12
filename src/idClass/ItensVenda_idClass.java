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
public class ItensVenda_idClass implements Serializable {

    private Integer numItem;
    private Integer venda;

    public ItensVenda_idClass() {
    }

    public Integer getNumItem() {
        return numItem;
    }

    public void setNumItem(Integer numItem) {
        this.numItem = numItem;
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
        hash = 37 * hash + Objects.hashCode(this.numItem);
        hash = 37 * hash + Objects.hashCode(this.venda);
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
        final ItensVenda_idClass other = (ItensVenda_idClass) obj;
        if (!Objects.equals(this.numItem, other.numItem)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItensVenda_idClass{" + "numItem=" + numItem + ", venda=" + venda + '}';
    }

}
