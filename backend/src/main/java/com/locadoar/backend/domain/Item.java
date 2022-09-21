package com.locadoar.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "DT_AQUISICAO")
    private Timestamp dataAquisicao;

    @Column(name = "tipo_item")
    private String tipoItem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TITULO")
    private Titulo titulo;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Timestamp getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Timestamp dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }
}
