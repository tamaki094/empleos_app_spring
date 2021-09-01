package net.edrialan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.edrialan.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	List<Vacante> findByEstatus(String estatus);
	
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);
	
	List<Vacante> findBySalarioBetween(double s1, double s2);
	
	List<Vacante> findByEstatusIn(String[] estatuses);
}
