package com.saga.outbox.atividade.repository;

import com.saga.outbox.atividade.model.ClienteMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteMongoRepository
        extends MongoRepository<ClienteMongo, Long> {
}
