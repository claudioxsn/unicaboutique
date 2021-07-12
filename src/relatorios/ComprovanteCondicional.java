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
public class ComprovanteCondicional {

    private String loja;
    private String enderecoLoja;
    private String telefone;
    private String celular;

    private Integer numCondicional;
    private String nome;
    private Date data;
    private String telefoneCliente;
    private String enderecoCliente;
    private String cidadeCliente;
    private String item;
    private int quantidade;
    private double valor;
    private double valorCondicional;

    public ComprovanteCondicional() {
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getEnderecoLoja() {
        return enderecoLoja;
    }

    public void setEnderecoLoja(String enderecoLoja) {
        this.enderecoLoja = enderecoLoja;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getNumCondicional() {
        return numCondicional;
    }

    public void setNumCondicional(Integer numCondicional) {
        this.numCondicional = numCondicional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorCondicional() {
        return valorCondicional;
    }

    public void setValorCondicional(double valorCondicional) {
        this.valorCondicional = valorCondicional;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.loja);
        hash = 79 * hash + Objects.hashCode(this.enderecoLoja);
        hash = 79 * hash + Objects.hashCode(this.telefone);
        hash = 79 * hash + Objects.hashCode(this.celular);
        hash = 79 * hash + Objects.hashCode(this.numCondicional);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.data);
        hash = 79 * hash + Objects.hashCode(this.telefoneCliente);
        hash = 79 * hash + Objects.hashCode(this.enderecoCliente);
        hash = 79 * hash + Objects.hashCode(this.cidadeCliente);
        hash = 79 * hash + Objects.hashCode(this.item);
        hash = 79 * hash + this.quantidade;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.valorCondicional) ^ (Double.doubleToLongBits(this.valorCondicional) >>> 32));
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
        final ComprovanteCondicional other = (ComprovanteCondicional) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorCondicional) != Double.doubleToLongBits(other.valorCondicional)) {
            return false;
        }
        if (!Objects.equals(this.loja, other.loja)) {
            return false;
        }
        if (!Objects.equals(this.enderecoLoja, other.enderecoLoja)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefoneCliente, other.telefoneCliente)) {
            return false;
        }
        if (!Objects.equals(this.enderecoCliente, other.enderecoCliente)) {
            return false;
        }
        if (!Objects.equals(this.cidadeCliente, other.cidadeCliente)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        if (!Objects.equals(this.numCondicional, other.numCondicional)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComprovanteCondicional{" + "loja=" + loja + ", enderecoLoja=" + enderecoLoja + ", telefone=" + telefone + ", celular=" + celular + ", numCondicional=" + numCondicional + ", nome=" + nome + ", data=" + data + ", telefoneCliente=" + telefoneCliente + ", enderecoCliente=" + enderecoCliente + ", cidadeCliente=" + cidadeCliente + ", item=" + item + ", quantidade=" + quantidade + ", valor=" + valor + ", valorCondicional=" + valorCondicional + '}';
    }

}
