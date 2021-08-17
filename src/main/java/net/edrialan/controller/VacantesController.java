package net.edrialan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.edrialan.model.Vacante;
import net.edrialan.service.IVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacantesService service;
	
	
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
}