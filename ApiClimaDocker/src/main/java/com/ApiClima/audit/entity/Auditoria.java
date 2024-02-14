package com.ApiClima.audit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="auditoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String url;
    @NotNull
    private Date fecha;
    @NotNull
    private String request;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String response;
    @NotNull
    private String usuario;

    public Auditoria(String url, Date fecha, String request, String response, String usuario) {
        this.url = url;
        this.fecha = fecha;
        this.request = request;
        this.response = response;
        this.usuario = usuario;
    }
}
