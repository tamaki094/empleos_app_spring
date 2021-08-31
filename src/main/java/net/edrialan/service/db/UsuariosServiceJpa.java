package net.edrialan.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.edrialan.model.Usuario;
import net.edrialan.repository.UsuariosRepository;
import net.edrialan.repository.VacantesRepository;
import net.edrialan.service.IUsuariosService;


@Service
public class UsuariosServiceJpa implements IUsuariosService 
{

	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Override
	public void guardar(Usuario usuario) 
	{
		usuariosRepository.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) 
	{
		usuariosRepository.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() 
	{
		return usuariosRepository.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) 
	{
		Optional<Usuario> opt = usuariosRepository.findById(idUsuario);
		if(opt.isPresent())
		{
			return opt.get();
		}
		return null;
	}

}
