package ar.edu.utn.frba.tadp.atencionmedica;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidadorCoberturaMock implements ValidadorCobertura {

	private Set<Paciente> pacientes;

	public ValidadorCoberturaMock(Paciente... pacientes) {
		this.pacientes = new HashSet<Paciente>(Arrays.asList(pacientes));
	}

	@Override
	public boolean cubrePaciente(Paciente paciente) {
		return this.pacientes.contains(paciente);
	}

}
