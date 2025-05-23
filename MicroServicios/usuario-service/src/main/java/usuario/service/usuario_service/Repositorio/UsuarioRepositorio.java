package usuario.service.usuario_service.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usuario.service.usuario_service.Entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

}
