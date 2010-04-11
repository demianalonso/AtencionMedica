package ar.edu.utn.frba.tadp.atencionmedica.asignaciones;

import java.math.BigDecimal;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.tiempo.Hora;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.UnidadMedica;

public class Asignacion {

	private Hora horaLlegada;
	private final UnidadMedica unidadMedica;
	private final String direccion;
	private final Hora horaAsignacion;
	private final Paciente paciente;

	public Asignacion(UnidadMedica unidadMedica, Paciente paciente, String direccion, Hora horaAsignacion) {
		this.unidadMedica = unidadMedica;
		this.paciente = paciente;
		this.direccion = direccion;
		this.horaAsignacion = horaAsignacion;
	}

	public void finalizar(Hora horaLlegada) {
		this.horaLlegada = horaLlegada;
		this.unidadMedica.liberarPaciente(paciente);
	}

	public boolean isDemorada() {
		return this.unidadMedica.isDemorada(this.tiempoServicio());
	}

	public boolean isFinalizada() {
		return this.horaLlegada != null;
	}

	private int tiempoServicio() {
		return this.horaLlegada.diferenciaMinutos(this.horaAsignacion);
	}

	public BigDecimal getCosto() {
		return this.isFinalizada() ? unidadMedica.getCosto(tiempoServicio()) : null;
	}

	public String getDireccion() {
		return direccion;
	}

}
