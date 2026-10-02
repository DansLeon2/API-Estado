
package com.api.API_Estado.DAO;

import com.api.API_Estado.Modelo.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author admin
 */
public interface EstadoDAO extends JpaRepository<Estado, Integer>{
    
    @Query(value="select est.* from TEstado est where est.EST_Activo= ?1 ", nativeQuery= true)
    List<Estado> ConsultaEstadoActivo (@Param("Id") Integer pId);
    
    @Procedure("SP_InsertarEstado")
    void spCliente(
            @Param("Nombre") String nombre);
}
