package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Locacao;
import com.locadoar.backend.persistence.repository.ILocacaoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/locacao")
public class LocacaoController extends BaseController<Locacao, ILocacaoRepository> {
    public LocacaoController(ILocacaoRepository repository) {
        super(Locacao.class, repository);
    }
}
