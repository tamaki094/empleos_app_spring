package net.edrialan.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.edrialan.model.Vacante;


@Service
public class VacantesServiceImpl implements IVacantesService 
{
	private List<Vacante> lista = null;
	public VacantesServiceImpl()
	{	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		 
		try 
		{
			Vacante vacante1 = new Vacante();
			
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero de comunicaciones");
			vacante1.setDescripcion("Se solicita ing para dar soporte a intranet");
			vacante1.setFecha(sdf.parse("08-02-2021"));
			//vacante1.setFecha("08-02-2021");
			vacante1.setDestacado(1);
			vacante1.setSalario(9700.00);
			vacante1.setImagen("empresa1.png");
			vacante1.setCategoria("Informatica");
			vacante1.setEstatus("Creada");
			
			lista.add(vacante1);
			
			
			Vacante vacante2 = new Vacante();
			
			vacante2.setId(2);
			vacante2.setNombre("Contador");
			vacante2.setDescripcion("Hacer cosas de contadores");
			vacante2.setFecha(sdf.parse("08-02-2021"));
			//vacante2.setFecha("08-02-2021");
			vacante2.setDestacado(0);
			vacante2.setSalario(600.00);
			vacante2.setImagen("empresa2.png");
			vacante2.setCategoria("Contabilidad");
			vacante2.setEstatus("Aprobada");
			
			lista.add(vacante2);
			
			Vacante vacante3 = new Vacante();
			
			vacante3.setId(3);
			vacante3.setNombre("Conserje");
			vacante3.setDescripcion("Limpiar cosas feas");
			vacante3.setFecha(sdf.parse("08-02-2021"));
			//vacante3.setFecha("02-02-2021");
			vacante3.setDestacado(1);
			vacante3.setSalario(200.00);
			vacante3.setCategoria("Construccion");
			vacante3.setEstatus("Creada");
			
			lista.add(vacante3);
			
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}		
	}
	
	
	@Override
	public List<Vacante> buscarTodas() 
	{
		return lista;
	}


	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for(Vacante v : lista)
		{
			if(v.getId() == idVacante)
			{
				return v;
			}
		}
		return null;
	}


	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);		
	}

}
