package com.saga.outbox.atividade.mapper;

import com.saga.outbox.atividade.controller.request.ClienteRequest;
import com.saga.outbox.atividade.controller.response.ClienteResponse;
import com.saga.outbox.atividade.model.ClienteEntity;

public class ClienteEntityMapper {

    public static ClienteEntity toEntity(ClienteRequest request) {

        return ClienteEntity.builder()
                .id(request.getId())
                .nome(request.getNome())
                .email(request.getEmail())
                .build();
    }

    public static ClienteResponse toResponse(ClienteEntity toEntity) {

        return ClienteResponse.builder()
                .id(toEntity.getId())
                .nome(toEntity.getNome())
                .email(toEntity.getEmail())
                .status(toEntity.getStatus())
                .build();
    }
}
