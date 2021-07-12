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
public class RelComprovanteOrdemServico {

    private int numOs;
    private String cliente;
    private Date entrada;
    private Date saida;
    private String marca;
    private String modelo;
    private String placa;
    private String ano;
    private String cor;
    private String defeito;
    private String observacoes;
    private String servProd;
    private int qtd;
    private double valorUnit;
    private double totalOs;

    public RelComprovanteOrdemServico() {
    }

    public double getTotalOs() {
        return totalOs;
    }

    public void setTotalOs(double totalOs) {
        this.totalOs = totalOs;
    }

    public int getNumOs() {
        return numOs;
    }

    public void setNumOs(int numOs) {
        this.numOs = numOs;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getServProd() {
        return servProd;
    }

    public void setServProd(String servProd) {
        this.servProd = servProd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    @Override
    public String toString() {
        return "RelComprovanteOrdemServico{" + "numOs=" + numOs + ", cliente=" + cliente + ", entrada=" + entrada + ", saida=" + saida + ", marca=" + marca + ", modelo=" + modelo + ", placa=" + placa + ", ano=" + ano + ", cor=" + cor + ", defeito=" + defeito + ", observacoes=" + observacoes + ", servProd=" + servProd + ", qtd=" + qtd + ", valorUnit=" + valorUnit + ", totalOs=" + totalOs + '}';
    }

}
