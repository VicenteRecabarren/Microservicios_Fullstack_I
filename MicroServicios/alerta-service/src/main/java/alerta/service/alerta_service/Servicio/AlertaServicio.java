package alerta.service.alerta_service.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alerta.service.alerta_service.Entidades.Alerta;
import alerta.service.alerta_service.Repositorio.AlertaRepositorio;

@Service
public class AlertaServicio {

    @Autowired
    private AlertaRepositorio alertaRepositorio;
    
    public List<Alerta> getAll(){
        return alertaRepositorio.findAll();
    }
    
    public List<Alerta> obtenerTodos(){
        return alertaRepositorio.findAll();
    }

    public Alerta getCursoById(int id){
        return alertaRepositorio.findById(id).orElse(null);
    }
    
    public Alerta obtenerPorId(int id){
        return alertaRepositorio.findById(id).orElse(null);
    }

    public Alerta save(Alerta alerta){
        Alerta nuevaAlerta = alertaRepositorio.save(alerta);
        return nuevaAlerta;
    }

    public List<Alerta> byUsuarioId(int usuarioid){
        return alertaRepositorio.findByUsuarioid(usuarioid);
    }

}
