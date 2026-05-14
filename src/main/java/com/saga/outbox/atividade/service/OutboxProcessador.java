package com.saga.outbox.atividade.service;

import com.saga.outbox.atividade.model.ClienteMongo;
import com.saga.outbox.atividade.model.OutboxEvento;
import com.saga.outbox.atividade.repository.ClienteEntityRepository;
import com.saga.outbox.atividade.repository.ClienteMongoRepository;
import com.saga.outbox.atividade.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxProcessador {

    private final OutboxRepository outboxRepository;
    private final ClienteMongoRepository mongoRepository;
    private final ClienteEntityRepository clienteEntityRepository;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processarEventos() {
        List<OutboxEvento> eventos = outboxRepository.findByProcessadaFalse();

        for (OutboxEvento evento : eventos) {
            try {
                ClienteMongo mongo = new ClienteMongo();
                mongo.setId(Long.valueOf(extract(evento.getCarga(), "id")));
                mongo.setNome(extract(evento.getCarga(), "nome"));
                mongo.setEmail(extract(evento.getCarga(), "email"));

                mongoRepository.save(mongo);

                clienteEntityRepository.findById(evento.getAgregadoId()).ifPresent(cliente -> {
                    cliente.setStatus("CONFIRMADO");
                    clienteEntityRepository.save(cliente);
                });

                evento.setProcessada(true);
                outboxRepository.save(evento);

            } catch (Exception e) {
                System.err.println("Erro ao processar evento: " + evento.getId());
            }
        }
    }

    private String extract(String json, String key) {
        return json.split("\"" + key + "\":\"")[1].split("\"")[0];
    }
}