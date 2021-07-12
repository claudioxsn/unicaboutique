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
public class PagamentoCompra_idClass implements Serializable {

    private String numBoleto;
    private Integer compra;

    public PagamentoCompra_idClass() {
    }

    public String getNumBoleto() {
        return numBoleto;
    }

    public void setNumBoleto(String numBoleto) {
        this.numBoleto = numBoleto;
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
        hash = 59 * hash + Objects.hashCode(this.numBoleto);
        hash = 59 * hash + Objects.hashCode(this.compra);
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
        final PagamentoCompra_idClass other = (PagamentoCompra_idClass) obj;
        if (!Objects.equals(this.numBoleto, other.numBoleto)) {
            return false;
        }
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PagamentoCompra_idClass{" + "numBoleto=" + numBoleto + ", compra=" + compra + '}';
    }

}
