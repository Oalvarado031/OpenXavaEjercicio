package org.example.uam_fia.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.example.uam_fia.calculadores.CalculadorSiguienteNumeroParaAnyo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "factura")
@Tab(name="Facturas")
@View(members=
    "anyo, numero, fecha;" +
    "cliente;" +
    "detalles;" +
    "observaciones"
)
public class Factura {
    
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Hidden
    @Column(length=32)
    private String oid;

    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    private int anyo;

    @Column(length=6)
    @DefaultValueCalculator(
        value=CalculadorSiguienteNumeroParaAnyo.class,
        properties=@PropertyValue(name="anyo")
    )
    private int numero;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("Simple")
    private Cliente cliente;

    @ElementCollection
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    private Collection<Detalle> detalles;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    @Required
    @Stereotype("MONEY")
    @ReadOnly
    private BigDecimal total;

    @TextArea
    private String observaciones;

    @PrePersist
    @PreUpdate
    private void calcularTotal() {
        if (detalles != null) {
            for (Detalle detalle : detalles) {
                if (detalle.getPrecioUnitario() != null) {
                    detalle.setImporte(BigDecimal.valueOf(detalle.getCantidad()).multiply(detalle.getPrecioUnitario()));
                }
            }
            total = detalles.stream()
                    .map(d -> d.getImporte() != null ? d.getImporte() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }
}
