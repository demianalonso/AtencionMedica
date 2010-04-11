package ar.edu.utn.frba.tadp.atencionmedica.unidades;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.AsignacionObserver;

public class Medico extends UnidadMedica {

	private Set<Paciente> pacientes;

	public Medico(AsignacionObserver asignacionObserver) {
		super(asignacionObserver);
		this.pacientes = new HashSet<Paciente>();
	}

	public void realizarAsignacion(Paciente paciente) {
		this.pacientes.add(paciente);
	}

	@Override
	public void liberarPaciente(Paciente paciente) {
		this.pacientes.remove(paciente);
	}

	@Override
	protected int minutosDemora(int tiempoServicioEnMinutos) {
		int minutosDemora = tiempoServicioEnMinutos;
		int tiempoMaximo = 60 * 3;
		return minutosDemora > tiempoMaximo ? minutosDemora - tiempoMaximo : 0;
	}

	@Override
	protected BigDecimal getCostoDemora(int tiempoServicioEnMinutos, BigDecimal costoBase) {
		return costoBase.divide(BigDecimal.TEN);
	}

	@Override
	protected BigDecimal getCostoBase() {
		return new BigDecimal(100);
	}

	public int getCantidadPacientesAsignados() {
		return this.pacientes.size();
	}
}
