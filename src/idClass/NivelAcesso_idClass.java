/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idClass;

import java.util.Objects;

/**
 *
 * @author claudio
 */
public class NivelAcesso_idClass {

    private String permissao;
    private String usuario;

    public NivelAcesso_idClass() {
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.permissao);
        hash = 59 * hash + Objects.hashCode(this.usuario);
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
        final NivelAcesso_idClass other = (NivelAcesso_idClass) obj;
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
        return "NivelAcesso_idClass{" + "permissao=" + permissao + ", usuario=" + usuario + '}';
    }

}
