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
public class RelComprovantePagamento {

    private String cliente;

    private int osVenda;
    private int numParcela;
    private double valorParcela;
    private Date vencimento;
    private Date pagamento;

    public RelComprovantePagamento() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getOsVenda() {
        return osVenda;
    }

    public void setOsVenda(int osVenda) {
        this.osVenda = osVenda;
    }

    public int getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "RelComprovantePagamento{" + "cliente=" + cliente + ", osVenda=" + osVenda + ", numParcela=" + numParcela + ", valorParcela=" + valorParcela + ", vencimento=" + vencimento + ", pagamento=" + pagamento + '}';
    }

}
