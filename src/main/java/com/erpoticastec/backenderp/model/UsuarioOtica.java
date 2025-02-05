package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "usuarios_oticas", schema = "opticcloud")
@IdClass(UsuarioOticaId.class)
public class UsuarioOtica {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_otica")
    private Otica otica;
}


