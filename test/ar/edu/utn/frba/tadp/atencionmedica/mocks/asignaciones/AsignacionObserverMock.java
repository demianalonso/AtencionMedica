package ar.edu.utn.frba.tadp.atencionmedica.mocks.asignaciones;

import java.util.HashMap;
import java.util.Map;

import ar.edu.utn.frba.tadp.atencionmedica.Paciente;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.AsignacionObserver;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.UnidadMedica;

public class AsignacionObserverMock implements AsignacionObserver {

	private Map<UnidadMedica, Paciente> notificaciones;

	public AsignacionObserverMock() {
		this.notificaciones = new HashMap<UnidadMedica, Paciente>();
	}

	@Override
	public void notificar(UnidadMedica unidadMedica, Paciente paciente) {
		this.notificaciones.put(unidadMedica, paciente);
	}

	public boolean fueNotificado(UnidadMedica unidadMedica, Paciente mastropiero) {
		if (this.notificaciones.containsKey(unidadMedica)) {
			return this.notificaciones.get(unidadMedica).equals(mastropiero);
		}
		return false;
	}

}
