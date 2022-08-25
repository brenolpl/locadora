package com.locadoar.backend.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BaseController<DOMAINCLASS, REPOSITORY extends JpaRepository<DOMAINCLASS, Integer>> {
    Class<DOMAINCLASS> domainClass;
    REPOSITORY repo;

    public BaseController(Class<DOMAINCLASS> domainClass, REPOSITORY repo) {
        this.repo = repo;
        this.domainClass = domainClass;
    }

    @GetMapping
    public Optional<DOMAINCLASS> get(@RequestParam Integer id) {
        return repo.findById(id);
    }
 }
