package net.edrialan.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.edrialan.model.Categoria;
import net.edrialan.repository.CategoriasRepository;
import net.edrialan.service.ICategoriasService;

@Service
//@Primary
public class CategoriasServiceJpa implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository categoriasRepository;

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepository.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepository.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> cOptional = categoriasRepository.findById(idCategoria);
		
		if(cOptional.isPresent())
		{
			return cOptional.get();
		}
		return null;
		
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}

}
