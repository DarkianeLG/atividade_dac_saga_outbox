package com.saga.outbox.atividade.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    private Long id;
    private String nome;
    private String email;

    // PENDENTE | CONFIRMADO | FALHA
    private String status;
}
