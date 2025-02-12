package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_oticas", schema = "opticcloud")
@IdClass(UsuarioOticaId.class)
public class UsuarioOtica {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_otica")
    private Otica otica;
}


