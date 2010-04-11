package ar.edu.utn.frba.tadp.atencionmedica.unidades;

import java.math.BigDecimal;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.AsignacionObserver;

public class Ambulancia extends UnidadMedica {

	boolean isDisponible;

	public Ambulancia(AsignacionObserver asignacionObserver) {
		super(asignacionObserver);
		this.desocupada();
	}

	public void ocupada() {
		this.isDisponible = false;
	}

	public boolean isDisponible() {
		return this.isDisponible;
	}

	@Override
	public void realizarAsignacion(Paciente paciente) {
		this.ocupada();
	}

	@Override
	public void liberarPaciente(Paciente paciente) {
		this.desocupada();
	}

	public void desocupada() {
		this.isDisponible = true;
	}

	protected int minutosDemora(int tiempoServicioEnMinutos) {
		int minutosDemora = tiempoServicioEnMinutos;
		return minutosDemora > 20 ? minutosDemora - 20 : 0;
	}

	@Override
	protected BigDecimal getCostoDemora(int tiempoServicioEnMinutos, BigDecimal costoBase) {
		BigDecimal recargoPorMinutoDemorado = costoBase.divide(new BigDecimal(100));
		BigDecimal minutosDemora = new BigDecimal(minutosDemora(tiempoServicioEnMinutos));
		BigDecimal costoDemora = recargoPorMinutoDemorado.multiply(minutosDemora);
		return costoDemora;
	}

	@Override
	protected BigDecimal getCostoBase() {
		return new BigDecimal(200);
	}

}
