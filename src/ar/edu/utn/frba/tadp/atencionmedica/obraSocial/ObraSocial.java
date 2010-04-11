package ar.edu.utn.frba.tadp.atencionmedica.obraSocial;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.ValidadorCobertura;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.Asignacion;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.PacienteSinCoberturaException;

public class ObraSocial {

	private final ValidadorCobertura validadorCobertura;
	private Set<Asignacion> asignaciones;

	public ObraSocial(ValidadorCobertura validadorCobertura) {
		this.validadorCobertura = validadorCobertura;
		this.asignaciones = new HashSet<Asignacion>();
	}

	public void validarCobertura(Paciente paciente) {
		if (pacienteSinCobertura(paciente)) {
			throw new PacienteSinCoberturaException();
		}
	}

	private boolean pacienteSinCobertura(Paciente paciente) {
		try {
			return !this.validadorCobertura.cubrePaciente(paciente);
		} catch (Exception exception) {
			return false;
		}
	}

	public void addAsignacion(Asignacion asignacion) {
		this.asignaciones.add(asignacion);
	}

	public BigDecimal getMontoAFacturar() {
		BigDecimal total = BigDecimal.ZERO;
		for (Asignacion asignacion : this.asignaciones) {
			if (asignacion.isFinalizada()) {
				total = total.add(asignacion.getCosto());
			}
		}
		return total;
	}

}
