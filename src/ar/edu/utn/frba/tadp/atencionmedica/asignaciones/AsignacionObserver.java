package ar.edu.utn.frba.tadp.atencionmedica.asignaciones;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.UnidadMedica;

public interface AsignacionObserver {

	/**
	 * Evento que se dispara cuando se ha asignado una unidad medica a un paciente
	 * @param unidadMedica
	 * @param paciente
	 */
	void notificar(UnidadMedica unidadMedica, Paciente paciente);

}
