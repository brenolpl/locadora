package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Dependente;
import com.locadoar.backend.persistence.repository.IDependenteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dependente")
public class DependenteController extends BaseController<Dependente, IDependenteRepository> {
    public DependenteController(IDependenteRepository repository) {
        super(Dependente.class, repository);
    }
}
