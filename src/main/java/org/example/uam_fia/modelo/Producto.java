package org.example.uam_fia.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "producto")
@Views({
    @View(name = "Simple", members = "numero, descripcion, precio")
})
@Tab(name="Productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @Column(name = "numero", length = 20, nullable = false, unique = true)
    @Required
    @DisplaySize(20)
    private String numero;

    @Column(name = "descripcion", length = 255, nullable = false)
    @Required
    @DisplaySize(80)
    private String descripcion;

    @Money
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @DescriptionsList
    private Categoria categoria;

    @Files
    @Column(length = 32)
    private String fotos;

    @TextArea
    private String observaciones;
}
