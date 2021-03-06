package net.edrialan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.edrialan.model.Perfil;
import net.edrialan.model.Usuario;
import net.edrialan.model.Vacante;
import net.edrialan.service.ICategoriasService;
import net.edrialan.service.IUsuariosService;
import net.edrialan.service.IVacantesService;

import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	private IUsuariosService serviceUsuarios;
	
	@Autowired
	@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategorias;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model)
	{
		List<Vacante> lista= serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return"detalle";
	}	
	
	@GetMapping("/")
	public String mostrarHome(Model model)
	{
		List<Vacante> lista= serviceVacantes.buscarDestacadas();
		model.addAttribute("vacantes", lista);
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		model.addAttribute("search", vacanteSearch);
		return"home";
	}
	

	@GetMapping("/signup")
	public String registrarse(Usuario usuario) 
	{
		return "/usuarios/formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) 
	{
		
		String pwdPlano = usuario.getPassword();
		String   pwdEncriptado = passwordEncoder.encode(pwdPlano);
		usuario.setPassword(pwdEncriptado);
		
		usuario.setEstatus(1);
		usuario.setFechaRegistro(new Date());
		
		Perfil perfil = new Perfil();
		perfil.setId(2);
		
		usuario.agregar(perfil);
		
		System.out.println("Detalle usuairo: " + usuario.toString());	
		serviceUsuarios.guardar(usuario);
		attributes.addFlashAttribute("msg", "Registro Guardado");
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) 
	{
		vacante.reset();
		
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		System.out.println("Buscando por: " + vacante.toString());
		Example<Vacante> example = Example.of(vacante, matcher);
		List<Vacante> lista = serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "home";
	}
	
	
	/***
	 * IntBinder para strings si los detecta vacios en el data binding los setea en null
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session)
	{
		String username = auth.getName();
		System.out.println("USUARIO: " + username);
		
		for (GrantedAuthority rol : auth.getAuthorities()) 
		{
			System.out.println("Rol: " + rol.getAuthority());
		}
		
		if(session.getAttribute("usuario") == null)
		{
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
			System.out.println("usuairo:" + usuario.toString() );
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto")String texto)
	{
		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	}
	
	@GetMapping("/login" )
	public String mostrarLogin() 
	{
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}
	
}
