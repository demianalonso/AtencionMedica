package ar.edu.utn.frba.tadp.atencionmedica;

import java.util.HashSet;
import java.util.Set;

import ar.edu.utn.frba.tadp.atencionmedica.unidades.Ambulancia;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Medico;

public class UnidadesMedicasLocator {

	private Set<Ambulancia> ambulancias;
	private Set<Medico> medicos;
	
	public UnidadesMedicasLocator() {
		this.ambulancias = new HashSet<Ambulancia>();
		this.medicos = new HashSet<Medico>();
	}

	public void addAmbulancia(Ambulancia ambulancia) {
		this.ambulancias.add(ambulancia);
	}

	public Set<Ambulancia> getAmbulanciasDisponibles() {
		Set<Ambulancia> ambulanciasDisponibles = new HashSet<Ambulancia>();
		for (Ambulancia ambulancia : this.ambulancias) {
			if(ambulancia.isDisponible()) {
				ambulanciasDisponibles.add(ambulancia);
			}
		}
		return ambulanciasDisponibles;
	}

	public Set<Medico> getMedicosDisponibles() {
		return this.medicos;
	}

	public void addMedico(Medico medico) {
		this.medicos.add(medico);
	}

}
