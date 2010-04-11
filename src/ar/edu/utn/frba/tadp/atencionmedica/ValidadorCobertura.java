package ar.edu.utn.frba.tadp.atencionmedica;

public interface ValidadorCobertura {

	/**
	 * Determinar si un paciente posee o no cobertura.
	 * @param paciente
	 * @return
	 * @exception Siempre puede fallar :)
	 */
	public boolean cubrePaciente(Paciente paciente);

}