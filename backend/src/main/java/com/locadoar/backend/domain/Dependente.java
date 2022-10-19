package com.locadoar.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DEPENDENTE")
public class Dependente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEPENDENTE")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_CLIENTE_RESPONSAVEL")
    private Cliente clienteResponsavel;

    @OneToOne
    @JoinColumn(name = "ID_CLIENTE_DEPENDENTE")
    private Cliente clienteDependente;

    public Cliente getClienteResponsavel() {
        return clienteResponsavel;
    }

    public void setClienteResponsavel(Cliente clienteResponsavel) {
        this.clienteResponsavel = clienteResponsavel;
    }

    public Cliente getClienteDependente() {
        return clienteDependente;
    }

    public void setClienteDependente(Cliente clienteDependente) {
        this.clienteDependente = clienteDependente;
    }
}
