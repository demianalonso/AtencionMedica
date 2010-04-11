package ar.edu.utn.frba.tadp.atencionmedica.tiempo;

public class Hora {

	private final int minutos;

	public Hora(int minutos) {
		this.minutos = minutos;
	}

	public Hora() {
		this.minutos = 0;
	}

	public int diferenciaMinutos(Hora hora) {
		return this.minutos - hora.minutos;
	}

}
