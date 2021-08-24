package net.edrialan.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.edrialan.model.Categoria;

@Service
public class CategoriasServiceImpl implements ICategoriasService
{
	private List<Categoria> lista = null;
	
	public CategoriasServiceImpl()
	{
		lista = new LinkedList<Categoria>();
		
		Categoria categoria1 = new Categoria();
		categoria1.setId(1);
		categoria1.setNombre("Ventas");
		categoria1.setDescripcion("");
		
		lista.add(categoria1);
		
		Categoria categoria2 = new Categoria();
		categoria2.setId(2);
		categoria2.setNombre("Contabilidad");
		categoria2.setDescripcion("");
		
		lista.add(categoria2);
		
		Categoria categoria3 = new Categoria();
		categoria3.setId(3);
		categoria3.setNombre("Informatica");
		categoria3.setDescripcion("");
		
		lista.add(categoria3);
		
		Categoria categoria4 = new Categoria();
		categoria4.setId(4);
		categoria4.setNombre("Transporte");
		categoria4.setDescripcion("");
		
		lista.add(categoria4);
		
		Categoria categoria5 = new Categoria();
		categoria5.setId(5);
		categoria5.setNombre("Gerencia");
		categoria5.setDescripcion("");
		
		lista.add(categoria5);
	}
	

	@Override
	public void guardar(Categoria categoria) {
		
		try 
		{
			int id = 0;
			
			for (Categoria item : buscarTodas()) 
			{
				id = item.getId();
			}
			categoria.setId(id + 1);
			lista.add(categoria);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}

	@Override
	public List<Categoria> buscarTodas() {
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria categoria : lista) 
		{
			if(categoria.getId() == idCategoria)
			{
				return categoria;
			}
		}
		return null;
	}
	

}
