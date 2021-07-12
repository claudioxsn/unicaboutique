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
public class ItensCondicional_idClass implements Serializable {

    private Integer numItem;
    private Integer Condicional;

    public ItensCondicional_idClass() {
    }

    public Integer getNumItem() {
        return numItem;
    }

    public void setNumItem(Integer numItem) {
        this.numItem = numItem;
    }

    public Integer getCondicional() {
        return Condicional;
    }

    public void setCondicional(Integer Condicional) {
        this.Condicional = Condicional;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.numItem);
        hash = 37 * hash + Objects.hashCode(this.Condicional);
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
        final ItensCondicional_idClass other = (ItensCondicional_idClass) obj;
        if (!Objects.equals(this.numItem, other.numItem)) {
            return false;
        }
        if (!Objects.equals(this.Condicional, other.Condicional)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItensCondicional_idClass{" + "numItem=" + numItem + ", Condicional=" + Condicional + '}';
    }

}
