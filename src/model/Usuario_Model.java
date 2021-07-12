/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author claudio
 */
@Entity
@Table(name = "tbl_usuario")
public class Usuario_Model implements Serializable {

    @Id
    @Column(name = "usuario", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String usuario;

    @Column(name = "senha", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String senha;

    private boolean ativo;

    @OneToOne
    @JoinColumn(name = "funcionario")
    private Funcionario_Model funcionario;

    @OneToMany
    @JoinColumn(name = "usuario")
    private List<NivelAcesso_Model> nivel;

    public Usuario_Model() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Funcionario_Model getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario_Model funcionario) {
        this.funcionario = funcionario;
    }

    public List<NivelAcesso_Model> getNivel() {
        return nivel;
    }

    public void setNivel(List<NivelAcesso_Model> nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.usuario);
        hash = 29 * hash + Objects.hashCode(this.senha);
        hash = 29 * hash + (this.ativo ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.funcionario);
        hash = 29 * hash + Objects.hashCode(this.nivel);
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
        final Usuario_Model other = (Usuario_Model) obj;
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario_Model{" + "usuario=" + usuario + ", senha=" + senha + ", ativo=" + ativo + ", funcionario=" + funcionario + ", nivel=" + nivel + '}';
    }

}
