package net.edrialan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.edrialan.model.Vacante;
import net.edrialan.service.IVacantesService;

import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService service;
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model)
	{
		List<Vacante> lista= service.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return"detalle";
	}	
	
	@GetMapping("/")
	public String mostrarHome(Model model)
	{
		List<Vacante> lista= service.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return"home";
	}	
}
