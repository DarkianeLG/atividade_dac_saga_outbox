package com.saga.outbox.atividade.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "outbox_evento")
public class OutboxEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agregadoId;
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String carga;

    private boolean processada;
}
