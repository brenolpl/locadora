package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Ator;
import com.locadoar.backend.persistence.repository.IAtorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ator")
public class AtorController extends BaseController<Ator, IAtorRepository> {

    public AtorController(IAtorRepository repo) {
        super(Ator.class, repo);
    }
}
