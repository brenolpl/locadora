package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Ator;
import com.locadoar.backend.persistence.repository.IAtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ator")
@CrossOrigin( origins = "http://localhost:5173")
public class AtorController {

    @Autowired
    private IAtorRepository atorRepository;

    @GetMapping("{id}")
    public Optional<Ator> getById(@PathVariable Integer id) {
        return atorRepository.findById(id);
    }

    @GetMapping("list")
    public List<Ator> list() {
        return atorRepository.findAll();
    }

    @PostMapping("save")
    public Ator persist(@RequestBody Ator ator) {
        return atorRepository.save(ator);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam Integer id) {
        atorRepository.deleteById(id);
    }
}
