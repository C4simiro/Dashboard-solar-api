package com.dashboard.solar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RegistroEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeUsina;
    private Double energiaGeradaKwh;
    private String dataRegistro;
    private String numeroContato;
    private String status;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeUsina() { return nomeUsina; }
    public void setNomeUsina(String nomeUsina) { this.nomeUsina = nomeUsina; }

    public Double getEnergiaGeradaKwh() { return energiaGeradaKwh; }
    public void setEnergiaGeradaKwh(Double energiaGeradaKwh) { this.energiaGeradaKwh = energiaGeradaKwh; }

    public String getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getNumeroContato() { return numeroContato; }
    public void setNumeroContato(String numeroContato) { this.numeroContato = numeroContato; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
