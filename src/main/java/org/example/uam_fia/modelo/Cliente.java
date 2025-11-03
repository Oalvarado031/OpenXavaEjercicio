package org.example.uam_fia.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "cliente")
@Views({
    @View(name = "Simple", members = "numero, nombre")
})
@Tab(name="Clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @Column(name = "numero", length = 20, nullable = false, unique = true)
    @Required
    @DisplaySize(20)
    private String numero;

    @Column(name = "nombre", length = 100, nullable = false)
    @Required
    @DisplaySize(50)
    private String nombre;

    @Column(name = "telefono", length = 20)
    @DisplaySize(20)
    @Stereotype("TELEPHONE")
    private String telefono;

    @Column(name = "email", length = 100)
    @DisplaySize(50)
    @Stereotype("EMAIL")
    private String email;

    @Embedded
    @NoFrame
    private Direccion direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @ListProperties("numero, fecha, total")
    private Collection<Factura> facturas;
}
