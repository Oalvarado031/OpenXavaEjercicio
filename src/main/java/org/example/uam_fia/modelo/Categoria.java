package org.example.uam_fia.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Views({
    @View(name = "Simple", members = "descripcion")
})
@Tab(name="Categoría")
public class Categoria {
    
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Hidden
    @Column(length=32)
    private String oid;

    @Column(length=50)
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private java.util.Collection<Producto> productos;
}
