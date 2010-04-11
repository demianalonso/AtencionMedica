package ar.edu.utn.frba.tadp.atencionmedica.mocks.obrasocial;

import ar.edu.utn.frba.tadp.atencionmedica.ValidadorCobertura;
import ar.edu.utn.frba.tadp.atencionmedica.Paciente;

public class ValidadorCoberturaQueExplota implements ValidadorCobertura {

	@Override
	public boolean cubrePaciente(Paciente paciente) {
		throw new RuntimeException("ah ah ah me rompi todo");
	}

}
