package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Autor;
import com.locadoar.backend.persistence.repository.IAutorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/autor")
public class AutorController extends BaseController<Autor, IAutorRepository> {

    public AutorController(IAutorRepository repository) {
        super(Autor.class, repository);
    }
}
