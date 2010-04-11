package ar.edu.utn.frba.tadp.atencionmedica;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.Asignacion;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.CreadorAsignaciones;
import ar.edu.utn.frba.tadp.atencionmedica.asignaciones.PacienteSinCoberturaException;
import ar.edu.utn.frba.tadp.atencionmedica.mocks.asignaciones.AsignacionObserverMock;
import ar.edu.utn.frba.tadp.atencionmedica.mocks.obrasocial.ValidadorCoberturaQueExplota;
import ar.edu.utn.frba.tadp.atencionmedica.tiempo.Hora;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Ambulancia;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Medico;

public class AsignacionesTestCase {

	private Paciente mastropiero;
	private Ambulancia ambulancia;
	private CreadorAsignaciones creadorAsignaciones;
	private Medico favaloro;
	private AsignacionObserverMock ambulanciaAsignacionObserver;
	private AsignacionObserverMock medicoAsignacionObserver;

	@Before
	public void configurarFixture() {
		mastropiero = new Paciente("Johann Sebastian", "Mastropiero", "Masculino", "38");

		creadorAsignaciones = new CreadorAsignaciones();
		creadorAsignaciones.registrarObraSocial("OSDE", new ValidadorCoberturaMock(mastropiero));
		creadorAsignaciones.registrarObraSocial("SABARABARA", new ValidadorCoberturaMock());
		creadorAsignaciones.registrarObraSocial("KABOOM", new ValidadorCoberturaQueExplota());

		ambulanciaAsignacionObserver = new AsignacionObserverMock();
		medicoAsignacionObserver = new AsignacionObserverMock();
		ambulancia = new Ambulancia(ambulanciaAsignacionObserver);
		this.favaloro = new Medico(medicoAsignacionObserver);
	}

	@Test
	public void asignarAmbulancia() {
		Assert.assertTrue(ambulancia.isDisponible());
		creadorAsignaciones.asignar(ambulancia, mastropiero, "su casa 333", "OSDE", new Hora());
		Assert.assertFalse(ambulancia.isDisponible());
		Assert.assertTrue(ambulanciaAsignacionObserver.fueNotificado(this.ambulancia, this.mastropiero));
	}

	@Test
	public void avisarFinalizacionAmbulancia() {
		Asignacion asignacion = creadorAsignaciones.asignar(ambulancia, mastropiero, "su casa 333", "OSDE", new Hora());
		asignacion.finalizar(new Hora(10));
		Assert.assertTrue(ambulancia.isDisponible());
		Assert.assertFalse(asignacion.isDemorada());
		Assert.assertEquals(new BigDecimal(200), asignacion.getCosto());
	}

	@Test
	public void avisarFinalizacionAmbulanciaConDemora() {
		Asignacion asignacion = creadorAsignaciones.asignar(ambulancia, mastropiero, "su casa 333", "OSDE", new Hora());
		asignacion.finalizar(new Hora(40));
		Assert.assertTrue(ambulancia.isDisponible());
		Assert.assertTrue(asignacion.isDemorada());
		Assert.assertEquals(new BigDecimal(160), asignacion.getCosto());
	}

	@Test(expected = PacienteSinCoberturaException.class)
	public void asignarPacienteSinCobertura() {
		this.creadorAsignaciones.asignar(ambulancia, mastropiero, "su casa 333", "SABARABARA", new Hora());
	}

	@Test
	public void asignarPacienteConObraSocialRota() {
		this.creadorAsignaciones.asignar(ambulancia, mastropiero, "su casa 333", "KABOOM", new Hora());
	}

	@Test
	public void asignarMedico() {
		creadorAsignaciones.asignar(this.favaloro, this.mastropiero, "Su casa 333", "OSDE", new Hora());
	}

	@Test
	public void avisarFinalizacionMedico() {
		Asignacion asignacion = creadorAsignaciones.asignar(this.favaloro, mastropiero, "su casa 333", "OSDE", new Hora());
		asignacion.finalizar(new Hora(120));
		Assert.assertFalse(asignacion.isDemorada());
		Assert.assertEquals(new BigDecimal(100), asignacion.getCosto());
	}

	@Test
	public void avisarFinalizacionMedicoConDemora() {
		Asignacion asignacion = creadorAsignaciones.asignar(this.favaloro, mastropiero, "su casa 333", "OSDE", new Hora());
		asignacion.finalizar(new Hora(500));
		Assert.assertTrue(asignacion.isDemorada());
		Assert.assertEquals(new BigDecimal(90), asignacion.getCosto());
	}

	@Test
	public void costoInvalido() {
		Asignacion asignacion = creadorAsignaciones.asignar(this.favaloro, mastropiero, "su casa 333", "OSDE", new Hora());
		Assert.assertNull(asignacion.getCosto());

	}

}
