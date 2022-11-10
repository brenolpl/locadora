package com.locadoar.backend.usecase;

import com.locadoar.backend.domain.Cliente;
import com.locadoar.backend.persistence.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DesativarCliente {

    private Integer id;

    public DesativarCliente(Integer id) {
        this.id = id;
    }

    @Autowired
    private IClienteRepository clienteRepository;

    public void execute() {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        cliente.get().setAtivo(false);
        clienteRepository.save(cliente.get());
    }
}
