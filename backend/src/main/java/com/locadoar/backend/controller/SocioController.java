package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Socio;
import com.locadoar.backend.persistence.repository.ISocioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/socio")
public class SocioController extends BaseController<Socio, ISocioRepository> {
    public SocioController(ISocioRepository repository) {
        super(Socio.class, repository);
    }
}
