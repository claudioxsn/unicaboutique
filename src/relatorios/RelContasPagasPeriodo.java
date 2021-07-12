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
public class RelContasPagasPeriodo {

    private String numboleto;
    private Date vencimento;
    private Date pagamento;
    private double valor;
    private String empresa;

    public RelContasPagasPeriodo() {
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    public String getNumboleto() {
        return numboleto;
    }

    public void setNumboleto(String numboleto) {
        this.numboleto = numboleto;
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
        return "RelContasPagasPeriodo{" + "numboleto=" + numboleto + ", vencimento=" + vencimento + ", pagamento=" + pagamento + ", valor=" + valor + ", empresa=" + empresa + '}';
    }

}
