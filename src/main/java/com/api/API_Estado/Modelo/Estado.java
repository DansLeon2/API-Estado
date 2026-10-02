
package com.api.API_Estado.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author admin
 */
@Entity
@Table(name ="TEstado")
@Getter
@Setter
public class Estado {
    
    @Id
    @Column(name="EST_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="EST_Nombre")
    private String nombre;
    
    @Column(name="EST_Descripcion")
    private String descripcion;
    
    @Column(name="EST_Activo")
    private Integer activo;
    
}
