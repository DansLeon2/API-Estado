package com.api.API_Estado.Controlador;

import com.api.API_Estado.Modelo.Estado;
import com.api.API_Estado.Servicio.EstadoServicio;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org. springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoControlador {
    
    @Autowired
    private EstadoServicio estadoServicio;
    
    @GetMapping("/Lista")
    public List<Estado> Listar (){
        return estadoServicio.ListaEstado();
    }
    
    @GetMapping("/ListarActivo")
    public List<Estado> ListarActivo(){
        return estadoServicio.ListaEstadoActivo();
    }
    
    @PostMapping("/Crear")
    public Estado Crear(@RequestBody Estado pEstado){
        return estadoServicio.CrearEstado(pEstado);
    }
    
    @PutMapping("/Actualizar")
    public Estado Modificar(@RequestBody Estado pEstado){
        return estadoServicio.ActualizarEstado(pEstado);
    }
    
    @DeleteMapping("/Eliminar/{id}")
    public String Eliminar(@PathVariable Integer pId){
        return estadoServicio.EliminarEstado(pId);
    }
    
    
}
