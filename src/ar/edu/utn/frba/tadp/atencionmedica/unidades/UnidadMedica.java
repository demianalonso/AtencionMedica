package ar.edu.utn.frba.tadp.atencionmedica.unidades;

import java.math.BigDecimal;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.AsignacionObserver;

public abstract class UnidadMedica {

	private final AsignacionObserver asignacionObserver;

	public UnidadMedica(AsignacionObserver asignacionObserver) {
		this.asignacionObserver = asignacionObserver;
	}

	public void asignar(Paciente paciente) {
		this.asignacionObserver.notificar(this, paciente);
		this.realizarAsignacion(paciente);
	}

	public abstract void realizarAsignacion(Paciente paciente);

	public abstract void liberarPaciente(Paciente paciente);

	protected abstract int minutosDemora(int tiempoServicioEnMinutos);

	protected abstract BigDecimal getCostoBase();

	protected abstract BigDecimal getCostoDemora(int tiempoServicioEnMinutos, BigDecimal costoBase);

	public BigDecimal getCosto(int tiempoServicioEnMinutos) {
		BigDecimal costoBase = getCostoBase();
		if (this.isDemorada(tiempoServicioEnMinutos)) {
			BigDecimal costoDemora = getCostoDemora(tiempoServicioEnMinutos, costoBase);
			return costoBase.subtract(costoDemora);
		} else {
			return costoBase;
		}
	}

	public boolean isDemorada(int tiempoServicioEnMinutos) {
		return minutosDemora(tiempoServicioEnMinutos) > 0;
	}

}