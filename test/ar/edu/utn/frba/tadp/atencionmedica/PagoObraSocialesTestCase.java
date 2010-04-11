package ar.edu.utn.frba.tadp.atencionmedica;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.Asignacion;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.CreadorAsignaciones;
import ar.edu.utn.frba.tadp.atencionmedica.mocks.asignaciones.AsignacionObserverMock;
import ar.edu.utn.frba.tadp.atencionmedica.obraSocial.ObraSocial;
import ar.edu.utn.frba.tadp.atencionmedica.tiempo.Hora;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Ambulancia;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Medico;

public class PagoObraSocialesTestCase {

	private Paciente mastropiero;
	private Ambulancia ambulancia;
	private CreadorAsignaciones creadorAsignaciones;
	private Medico favaloro;
	private ObraSocial osde;

	@Before
	public void configurarFixture() {
		this.mastropiero = new Paciente("Johann Sebastian", "Mastropiero", "Masculino", "38");

		this.creadorAsignaciones = new CreadorAsignaciones();
		this.osde = creadorAsignaciones.registrarObraSocial("OSDE", new ValidadorCoberturaMock(mastropiero));

		this.ambulancia = new Ambulancia(new AsignacionObserverMock());
		this.favaloro = new Medico(new AsignacionObserverMock());
	}

	@Test
	public void calcularCobro() {
		Asignacion asignacion = creadorAsignaciones.asignar(this.favaloro, this.mastropiero, "Blah", "OSDE", new Hora());
		asignacion.finalizar(new Hora(30));

		Asignacion asignacion2 = creadorAsignaciones.asignar(this.ambulancia, this.mastropiero, "Blah", "OSDE", new Hora(1000));
		asignacion2.finalizar(new Hora(1010));

		ObraSocial obraSocial = this.creadorAsignaciones.getObraSocial("OSDE");
		Assert.assertEquals(new BigDecimal(300), obraSocial.getMontoAFacturar());
	}

	@Test
	public void calcularCobroConAsignacionesSinFinalizar() {
		Asignacion asignacion = creadorAsignaciones.asignar(this.favaloro, this.mastropiero, "Blah", "OSDE", new Hora());
		asignacion.finalizar(new Hora(30));

		Asignacion asignacion2 = creadorAsignaciones.asignar(this.ambulancia, this.mastropiero, "Blah", "OSDE", new Hora(1000));
		asignacion2.finalizar(new Hora(1010));

		creadorAsignaciones.asignar(this.ambulancia, this.mastropiero, "Blah", "OSDE", new Hora(1000));

		Assert.assertEquals(new BigDecimal(300), this.osde.getMontoAFacturar());
	}
}
