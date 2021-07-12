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
public class RelVendasPorPeriodo {

    private Integer numVenda;
    private Date dataVenda;
    private String status;
    private String tipoPagamento;
    private double total;
    private double desconto;
    private double totalComDesconto;
    private String empresa;

    public RelVendasPorPeriodo() {
    }

    public Integer getNumVenda() {
        return numVenda;
    }

    public void setNumVenda(Integer numVenda) {
        this.numVenda = numVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTotalComDesconto() {
        return totalComDesconto;
    }

    public void setTotalComDesconto(double totalComDesconto) {
        this.totalComDesconto = totalComDesconto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "RelVendasPorPeriodo{" + "numVenda=" + numVenda + ", dataVenda=" + dataVenda + ", status=" + status + ", tipoPagamento=" + tipoPagamento + ", total=" + total + ", desconto=" + desconto + ", totalComDesconto=" + totalComDesconto + ", empresa=" + empresa + '}';
    }

}
