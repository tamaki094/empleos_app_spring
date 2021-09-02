package net.edrialan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.edrialan.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> 
{
	Usuario findByUsername(String username);
}

