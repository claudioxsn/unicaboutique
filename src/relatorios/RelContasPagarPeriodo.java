/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.util.Date;

/**
 *
 * @author Claudio Xavier
 */
public class RelContasPagarPeriodo {

    private String numboleto;
    private Date emissao;
    private Date vencimento;
    private double valor;
    private String empresa;

    public RelContasPagarPeriodo() {
    }

    public String getNumboleto() {
        return numboleto;
    }

    public void setNumboleto(String numboleto) {
        this.numboleto = numboleto;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "RelContasPagarPeriodo{" + "numboleto=" + numboleto + ", emissao=" + emissao + ", vencimento=" + vencimento + ", valor=" + valor + ", empresa=" + empresa + '}';
    }

}
