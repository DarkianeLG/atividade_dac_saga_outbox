package com.saga.outbox.atividade.mapper;

import com.saga.outbox.atividade.controller.request.ClienteRequest;
import com.saga.outbox.atividade.model.ClienteMongo;

public class ClienteMongoMapper {

    public static ClienteMongo toEntity(ClienteRequest request) {

        return ClienteMongo.builder()
                .id(request.getId())
                .nome(request.getNome())
                .email(request.getEmail())
                .build();
    }

}
