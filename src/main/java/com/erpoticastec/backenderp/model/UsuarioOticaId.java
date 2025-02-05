package com.erpoticastec.backenderp.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioOticaId implements Serializable {

    private Long usuario;
    private Long otica;

    public UsuarioOticaId() {}

    public UsuarioOticaId(Long usuario, Long otica) {
        this.usuario = usuario;
        this.otica = otica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioOticaId that = (UsuarioOticaId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(otica, that.otica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, otica);
    }
}