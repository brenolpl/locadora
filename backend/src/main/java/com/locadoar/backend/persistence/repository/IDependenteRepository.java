package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDependenteRepository extends JpaRepository<Dependente, Integer> {
}
