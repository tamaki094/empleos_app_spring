package net.edrialan.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.edrialan.model.Vacante;
import net.edrialan.repository.VacantesRepository;
import net.edrialan.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {

	@Autowired
	private VacantesRepository vacantesRepository;
	
	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepository.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional =  vacantesRepository.findById(idVacante);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepository.save(vacante);

	}

}
