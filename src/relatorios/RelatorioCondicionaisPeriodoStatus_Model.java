/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Claudio Xavier
 */
public class RelatorioCondicionaisPeriodoStatus_Model {

    private Integer numero;
    private String nomeCliente;
    private String status;
    private Date abertura;
    private Date prazo;
    private Date devolucao;
    private double valorCondicional;
    private String empresa;

    public RelatorioCondicionaisPeriodoStatus_Model() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public Date getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Date devolucao) {
        this.devolucao = devolucao;
    }

    public double getValorCondicional() {
        return valorCondicional;
    }

    public void setValorCondicional(double valorCondicional) {
        this.valorCondicional = valorCondicional;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.numero);
        hash = 23 * hash + Objects.hashCode(this.nomeCliente);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.abertura);
        hash = 23 * hash + Objects.hashCode(this.prazo);
        hash = 23 * hash + Objects.hashCode(this.devolucao);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.valorCondicional) ^ (Double.doubleToLongBits(this.valorCondicional) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.empresa);
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
        final RelatorioCondicionaisPeriodoStatus_Model other = (RelatorioCondicionaisPeriodoStatus_Model) obj;
        if (Double.doubleToLongBits(this.valorCondicional) != Double.doubleToLongBits(other.valorCondicional)) {
            return false;
        }
        if (!Objects.equals(this.nomeCliente, other.nomeCliente)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.abertura, other.abertura)) {
            return false;
        }
        if (!Objects.equals(this.prazo, other.prazo)) {
            return false;
        }
        if (!Objects.equals(this.devolucao, other.devolucao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RelatorioCondicionaisPeriodoStatus_Model{" + "numero=" + numero + ", nomeCliente=" + nomeCliente + ", status=" + status + ", abertura=" + abertura + ", prazo=" + prazo + ", devolucao=" + devolucao + ", valorCondicional=" + valorCondicional + ", empresa=" + empresa + '}';
    }

}
