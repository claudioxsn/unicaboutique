/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import idClass.NivelAcesso_idClass;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author claudio
 */
@Entity
@Table(name = "tbl_nivelAcesso")
@IdClass(NivelAcesso_idClass.class)
public class NivelAcesso_Model implements Serializable {

    @Id
    @Column(name = "permissao", nullable = false, columnDefinition = "VARCHAR(15)")
    private String permissao;

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario_Model usuario;

    public NivelAcesso_Model() {
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Usuario_Model getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario_Model usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.permissao);
        hash = 97 * hash + Objects.hashCode(this.usuario);
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
        final NivelAcesso_Model other = (NivelAcesso_Model) obj;
        if (!Objects.equals(this.permissao, other.permissao)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NivelAcesso_Model{" + "permissao=" + permissao + ", usuario=" + usuario + '}';
    }

}
