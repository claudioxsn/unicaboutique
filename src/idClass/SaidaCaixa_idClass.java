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
public class SaidaCaixa_idClass implements Serializable{

    private Integer numSaida;
    private Date caixa;

    public SaidaCaixa_idClass() {
    }

    public Integer getNumSaida() {
        return numSaida;
    }

    public void setNumSaida(Integer numSaida) {
        this.numSaida = numSaida;
    }

    public Date getCaixa() {
        return caixa;
    }

    public void setCaixa(Date caixa) {
        this.caixa = caixa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.numSaida);
        hash = 89 * hash + Objects.hashCode(this.caixa);
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
        final SaidaCaixa_idClass other = (SaidaCaixa_idClass) obj;
        if (!Objects.equals(this.numSaida, other.numSaida)) {
            return false;
        }
        if (!Objects.equals(this.caixa, other.caixa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SaidaCaixa_idClass{" + "numSaida=" + numSaida + ", caixa=" + caixa + '}';
    }

}
