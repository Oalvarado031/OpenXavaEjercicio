package org.example.uam_fia.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.example.uam_fia.acciones.OnChangeProductoDetalle;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class Detalle {
    
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @OnChange(OnChangeProductoDetalle.class)
    private Producto producto;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    @Stereotype("MONEY")
    private BigDecimal precioUnitario;

    @Column(name = "importe", precision = 10, scale = 2)
    @Stereotype("MONEY")
    @ReadOnly
    private BigDecimal importe;
}
