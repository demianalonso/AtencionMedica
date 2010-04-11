package ar.edu.utn.frba.tadp.atencionmedica.asignaciones;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.UnidadMedica;

public interface AsignacionObserver {

	void notificar(UnidadMedica unidadMedica, Paciente paciente);

}
