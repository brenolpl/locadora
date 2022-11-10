package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Cliente;
import com.locadoar.backend.persistence.repository.IClienteRepository;
import com.locadoar.backend.usecase.DesativarCliente;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cliente")
public class ClienteController extends BaseController<Cliente, IClienteRepository> {

    public ClienteController(IClienteRepository repository) {
        super(Cliente.class, repository);
    }

    @Override
    protected void delete(Integer id) {
        new DesativarCliente(id).execute();
    }
}
