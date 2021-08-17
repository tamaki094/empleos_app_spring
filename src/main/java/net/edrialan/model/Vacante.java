package net.edrialan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacante 
{
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Double salario;
	private Integer destacado;
	private String imagen = "no-image.png";
	private String estatus;
	private String detalles;
	
	
	
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Integer getDestacado() 
	{
		return destacado;
	}
	public void setDestacado(Integer destacado) 
	{
		this.destacado = destacado;
	}
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getDescripcion() 
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public Date getFecha() 
	{		
		System.out.println("getFecha:" + fecha );
		System.out.println(new Date().toString());
		return fecha;
	}
	public void setFecha(Date fecha) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("setFecha: "  + fecha);
		this.fecha = fecha;
	}
	public Double getSalario()
	{
		return salario;
	}
	public void setSalario(Double salario)
	{
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen + ", estatus=" + estatus
				+ ", detalles=" + detalles + "]";
	}

	
	
	
	
	
	
	
	
}
