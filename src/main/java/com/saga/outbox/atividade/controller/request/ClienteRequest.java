package com.saga.outbox.atividade.controller.request;

import lombok.*;

@Getter
@Setter
public class ClienteRequest{

    private Long id;
    private String nome;
    private String email;
}
