package ejercicio3_3;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Persona implements Serializable {

	private static final long serialVersionUID = 4818360910425676421L;

	String nif;
	String nombre;
	String apellido;
	Date fechaNascimiento;
	char genero;

	@Override
	public int hashCode() {
		return Objects.hash(apellido, fechaNascimiento, genero, nif, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(fechaNascimiento, other.fechaNascimiento)
				&& genero == other.genero && Objects.equals(nif, other.nif) && Objects.equals(nombre, other.nombre);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNascimiento() {
		return fechaNascimiento;
	}

	public void setFechaNascimiento(Date fechaNascimiento) {
		this.fechaNascimiento = fechaNascimiento;
	}
	
	
	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Persona [nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNascimiento="
				+ fechaNascimiento + ", genero=" + genero + "]";
	}

}
