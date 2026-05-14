package com.saga.outbox.atividade.service;

import com.saga.outbox.atividade.controller.request.ClienteRequest;
import com.saga.outbox.atividade.controller.response.ClienteResponse;
import com.saga.outbox.atividade.mapper.ClienteEntityMapper;
import com.saga.outbox.atividade.model.ClienteEntity;
import com.saga.outbox.atividade.model.OutboxEvento;
import com.saga.outbox.atividade.repository.ClienteEntityRepository;
import com.saga.outbox.atividade.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteSagaService {

    private final ClienteEntityRepository clienteEntityRepository;
    private final OutboxRepository outboxRepository;

    @Transactional
    public ClienteResponse criarCliente(ClienteRequest request) {
        ClienteEntity entity = ClienteEntityMapper.toEntity(request);

        try {
            entity.setStatus("PENDENTE");
            clienteEntityRepository.save(entity);

            OutboxEvento evento = new OutboxEvento();
            evento.setAgregadoId(entity.getId());
            evento.setTipo("CLIENT_CREATED");
            evento.setCarga("""
                    {
                      "id":"%s",
                      "nome":"%s",
                      "email":"%s"
                    }
                    """.formatted(
                    entity.getId(),
                    entity.getNome(),
                    entity.getEmail()
            ));
            evento.setProcessada(false);
            outboxRepository.save(evento);

            return ClienteEntityMapper.toResponse(entity);

        } catch (Exception e) {
            compensar(entity.getId());
            throw new RuntimeException("Erro ao iniciar transação distribuída");
        }
    }

    private void compensar(Long id) {
        clienteEntityRepository.findById(id)
                .ifPresent(cliente -> {
                    cliente.setStatus("FALHA");
                    clienteEntityRepository.save(cliente);
                });
    }
}