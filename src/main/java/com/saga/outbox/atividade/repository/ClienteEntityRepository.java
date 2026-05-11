package com.saga.outbox.atividade.repository;

import com.saga.outbox.atividade.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Long> {
}
