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
public class ItensCompra_idClass implements Serializable {

    private Integer numItem;
    private Integer compra;

    public ItensCompra_idClass() {
    }

    public Integer getNumItem() {
        return numItem;
    }

    public void setNumItem(Integer numItem) {
        this.numItem = numItem;
    }

    public Integer getCompra() {
        return compra;
    }

    public void setCompra(Integer compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.numItem);
        hash = 31 * hash + Objects.hashCode(this.compra);
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
        final ItensCompra_idClass other = (ItensCompra_idClass) obj;
        if (!Objects.equals(this.numItem, other.numItem)) {
            return false;
        }
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItensCompra_idClass{" + "numItem=" + numItem + ", compra=" + compra + '}';
    }

}
