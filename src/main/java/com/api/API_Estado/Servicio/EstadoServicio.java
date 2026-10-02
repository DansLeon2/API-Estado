
package com.api.API_Estado.Servicio;

import com.api.API_Estado.DAO.EstadoDAO;
import com.api.API_Estado.Modelo.Estado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author admin
 */
@Service
public class EstadoServicio {
    
    @Autowired
    private EstadoDAO estadoDAO;
    

    
    public List<Estado> ListaEstado(){
        return estadoDAO.findAll();
    }
    
    public List<Estado> ListaEstadoActivo(){
        return estadoDAO.ConsultaEstadoActivo(1);
    }
    
    public Estado CrearEstado(Estado pEstado){
        return estadoDAO.save(pEstado);
    }
    
    /*public String CrearEstadoSP(String pNombre){
        String MsgRespuesta="";
        
        estadoDAO.spCliente(pNombre);
        
    }*/
    
    public Estado ActualizarEstado(Estado pEstado){
        return estadoDAO.save(pEstado);
    }
    
    public String EliminarEstado(Integer pId){
        
        String MsgRespuesta="";
        try {
            estadoDAO.deleteById(pId);
            MsgRespuesta="Estado con id "+ pId + " ELIMINADO correctamente.";
        } catch (Exception e) {
            MsgRespuesta="Error al eliminar el Estado con id "+ pId + " ERROR:: " +e.getMessage();
        }
        return MsgRespuesta;
    }

}
