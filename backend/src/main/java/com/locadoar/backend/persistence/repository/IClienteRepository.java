package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
