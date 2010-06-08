package ar.edu.utn.frba.tadp.atencionmedica;

public class Paciente {

	private final String nombre;
	private final String apellido;
	private final String sexo;
	private final String edad;

	public Paciente(String nombre, String apellido, String sexo, String edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEdad() {
		return edad;
	}

}
