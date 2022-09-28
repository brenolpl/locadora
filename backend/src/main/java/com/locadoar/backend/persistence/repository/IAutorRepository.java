package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorRepository extends JpaRepository<Autor, Integer> {
}
