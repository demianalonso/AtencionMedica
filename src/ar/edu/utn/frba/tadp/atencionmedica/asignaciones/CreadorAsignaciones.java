package ar.edu.utn.frba.tadp.atencionmedica.asignaciones;

import java.util.HashMap;
import java.util.Map;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.ValidadorCobertura;
import ar.edu.utn.frba.tadp.atencionmedica.obraSocial.ObraSocial;
import ar.edu.utn.frba.tadp.atencionmedica.tiempo.Hora;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.UnidadMedica;

public class CreadorAsignaciones {

	private Map<String, ObraSocial> obrasSociales;

	public CreadorAsignaciones() {
		this.obrasSociales = new HashMap<String, ObraSocial>();
	}

	public ObraSocial registrarObraSocial(String nombreObraSocial, ValidadorCobertura validadorCobertura) {
		ObraSocial obraSocial = new ObraSocial(validadorCobertura);
		this.obrasSociales.put(nombreObraSocial, obraSocial);
		return obraSocial;
	}

	public Asignacion asignar(UnidadMedica unidadMedica, Paciente paciente, String direccion, String nombreObraSocial, Hora horaAsignacion) {
		this.validarCobertura(nombreObraSocial, paciente);
		unidadMedica.asignar(paciente);

		Asignacion asignacion = new Asignacion(unidadMedica, paciente, direccion, horaAsignacion);
		this.getObraSocial(nombreObraSocial).addAsignacion(asignacion);

		return asignacion;
	}

	public void validarCobertura(String nombreObraSocial, Paciente paciente) {
		ObraSocial obraSocial = this.obrasSociales.get(nombreObraSocial);
		obraSocial.validarCobertura(paciente);
	}

	public ObraSocial getObraSocial(String nombreObraSocial) {
		return this.obrasSociales.get(nombreObraSocial);
	}

}
