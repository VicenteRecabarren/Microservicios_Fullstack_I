package usuario.service.usuario_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usuario.service.usuario_service.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    Usuario findByEmail(String email);
}
