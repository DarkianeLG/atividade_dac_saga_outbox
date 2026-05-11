package com.saga.outbox.atividade.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "cliente")
public class ClienteMongo {

    @Id
    private Long id;
    private String nome;
    private String email;
}
