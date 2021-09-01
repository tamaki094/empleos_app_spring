package net.edrialan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.edrialan.model.Categoria;
import net.edrialan.model.Vacante;
import net.edrialan.service.ICategoriasService;
import net.edrialan.service.IVacantesService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) 
	{
		List<Categoria> categorias = serviceCategorias.buscarTodas();
		model.addAttribute("categorias", categorias);
		
		return "categorias/listCategorias";
	}
	
	
	@GetMapping("/create")
	public String crear() 
	{
		return "categorias/formCategoria";
	}
	@PostMapping("/save")
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes atributes) 
	{
		
		System.out.println("Categoria:" + categoria.getNombre());
		System.out.println("Descripcion:" + categoria.getDescripcion());
		
		serviceCategorias.guardar(categoria);
		
		atributes.addFlashAttribute("msg", "Registro Guardado");
		
		return "redirect:/categorias/index";
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) 
	{
		Page<Categoria> lista = serviceCategorias.buscarTodas(page);
		model.addAttribute("categorias", lista);
		return "categorias/listCategorias";
	}


}
