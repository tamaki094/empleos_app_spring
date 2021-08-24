package net.edrialan.controller;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.edrialan.model.Vacante;
import net.edrialan.service.ICategoriasService;
import net.edrialan.service.IVacantesService;
import net.edrialan.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacantesService service;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/")
	public String mostrarIndex(Model model)
	{
		List<Vacante> vacantes = service.buscarTodas();
		model.addAttribute("vacantes", vacantes);
		
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String crear(Vacante result, Model model)
	{
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacante";
	}
	
	/*@PostMapping("/save")
	public String guardar(
			@RequestParam("nombre") String nombre, 
			@RequestParam("descripcion") String descripcion, 
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles
	)
	{
		System.out.println("Detalles:" + detalles);
		return "vacantes/listVacantes";
	}*/
	
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes atributes, @RequestParam("archivoImagen")MultipartFile multipart)
	{		
		if(result.hasErrors())
		{
			for(ObjectError error : result.getAllErrors())
			{
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		
		if(!multipart.isEmpty())
		{
			String ruta = "c:/empleos/img-vacantes/";
			String nombreImagen = Utileria.guardarArchivo(multipart, ruta);
			
			if (nombreImagen != null)
			{
				vacante.setImagen(nombreImagen); 
			}
		}
		System.out.println("Detalles:" + vacante.toString());
		service.guardar(vacante);
		
		atributes.addFlashAttribute("msg", "Registro Guardado");
		
		
		return "redirect:/vacantes/";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model)
	{
		System.out.println("Borrando vacante con id:" + idVacante);
		model.addAttribute("id" , idVacante);
		
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model)
	{
		Vacante vacante = service.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		
		return "info_vacante";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
