/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idClass;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Claudio Xavier
 */
public class EntradaCaixa_idClass implements Serializable {
    
    private Integer numEntrada; 
    private Date caixa; 

    public EntradaCaixa_idClass() {
    }

    public Integer getNumEntrada() {
        return numEntrada;
    }

    public void setNumEntrada(Integer numEntrada) {
        this.numEntrada = numEntrada;
    }

    public Date getCaixa() {
        return caixa;
    }

    public void setCaixa(Date caixa) {
        this.caixa = caixa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.numEntrada);
        hash = 53 * hash + Objects.hashCode(this.caixa);
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
        final EntradaCaixa_idClass other = (EntradaCaixa_idClass) obj;
        if (!Objects.equals(this.numEntrada, other.numEntrada)) {
            return false;
        }
        if (!Objects.equals(this.caixa, other.caixa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntradaCaixa_idClass{" + "numEntrada=" + numEntrada + ", caixa=" + caixa + '}';
    }
    
    
}
