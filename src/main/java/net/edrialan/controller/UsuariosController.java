package net.edrialan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.edrialan.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private IUsuariosService serviceUsuarios;

	
    @GetMapping("/index")
	public String mostrarIndex(Model model) {

    	model.addAttribute("usuarios", serviceUsuarios.buscarTodos());
    	
    	return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	System.out.println("eliminando el usuario: " + idUsuario);
    	attributes.addFlashAttribute("msg", "Se borro el usuario " + idUsuario);
    	
    	serviceUsuarios.eliminar(idUsuario);
    	
		return "redirect:/usuarios/index";
	}
}
