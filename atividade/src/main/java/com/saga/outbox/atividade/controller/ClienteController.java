package com.saga.outbox.atividade.controller;

import com.saga.outbox.atividade.controller.request.ClienteRequest;
import com.saga.outbox.atividade.controller.response.ClienteResponse;
import com.saga.outbox.atividade.service.ClienteSagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteSagaService clienteService;

    @PostMapping
    public ClienteResponse criar (@RequestBody ClienteRequest request) {
        return  clienteService.criarCliente(request);
    }
}
