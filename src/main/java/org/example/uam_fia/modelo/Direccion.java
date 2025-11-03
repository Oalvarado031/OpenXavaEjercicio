package org.example.uam_fia.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class Direccion {

    @Column(length = 30)
    private String viaPublica;

    @Column(length = 5)
    private int codigoPostal;

    @Column(length = 20)
    private String municipio;

    @Column(length = 30)
    private String provincia;
}
