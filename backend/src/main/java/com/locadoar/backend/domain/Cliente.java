package com.locadoar.backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CLIENTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer id;

    @NotNull
    @Column(name = "NUM_INSCRICAO", unique = true, nullable = false)
    private String numInscricao;

    @NotNull
    @Column(name = "NOME", nullable = false, unique = true)
    private String nome;

    @NotNull
    @Column(name = "DT_NASCIMENTO", nullable = false)
    private Date date;

    @Column(name = "SEXO")
    private String sexo;

    @NotNull
    @Column(name = "IS_ATIVO", nullable = false)
    private boolean isAtivo;
}
