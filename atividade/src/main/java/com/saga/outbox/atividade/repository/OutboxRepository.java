package com.saga.outbox.atividade.repository;

import com.saga.outbox.atividade.model.OutboxEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvento, Long> {

    List<OutboxEvento> findByProcessadaFalse();
}
