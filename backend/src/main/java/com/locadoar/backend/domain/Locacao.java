package com.locadoar.backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "LOCACAO")
public class Locacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATOR")
    private Integer id;

    @NotNull
    @Column(name = "DT_LOCACAO")
    private Timestamp dtLocacao;

    @NotNull
    @Column(name = "DT_DEVOLUCAO_PREVISTA")
    private Timestamp dtDevolucaoPrevista;

    @Column(name = "DT_DEVOLUCAO_EVETIVA")
    private Timestamp dtDevolucaoEfetiva;

    @NotNull
    @Column(name = "VALOR_COBRADO")
    private BigDecimal valorCobrado;

    @NotNull
    @Column(name = "MULTA_COBRADA")
    private BigDecimal multaCobrada;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDtLocacao() {
        return dtLocacao;
    }

    public void setDtLocacao(Timestamp dtLocacao) {
        this.dtLocacao = dtLocacao;
    }

    public Timestamp getDtDevolucaoPrevista() {
        return dtDevolucaoPrevista;
    }

    public void setDtDevolucaoPrevista(Timestamp dtDevolucaoPrevista) {
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
    }

    public Timestamp getDtDevolucaoEfetiva() {
        return dtDevolucaoEfetiva;
    }

    public void setDtDevolucaoEfetiva(Timestamp dtDevolucaoEfetiva) {
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
    }

    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(BigDecimal valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public BigDecimal getMultaCobrada() {
        return multaCobrada;
    }

    public void setMultaCobrada(BigDecimal multaCobrada) {
        this.multaCobrada = multaCobrada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
