package ar.edu.utn.frba.tadp.atencionmedica;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.tadp.atencionmedica.mocks.asignaciones.AsignacionObserverMock;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Ambulancia;
import ar.edu.utn.frba.tadp.atencionmedica.unidades.Medico;

public class ConsultaUnidadesMedicasTestCase {

	private UnidadesMedicasLocator locator;
	private Ambulancia ambulancia;
	private Ambulancia ambulancia2;
	private Ambulancia ambulancia3;
	private Medico medico;

	@Before
	public void configurarFixture() {
		this.locator = new UnidadesMedicasLocator();

		this.ambulancia = newAmbulancia();
		this.locator.addAmbulancia(this.ambulancia);

		this.ambulancia2 = newAmbulancia();
		this.locator.addAmbulancia(this.ambulancia2);

		this.ambulancia3 = newAmbulancia();
		this.locator.addAmbulancia(this.ambulancia3);

		this.medico = newMedico();
		this.locator.addMedico(this.medico);
	}

	private Medico newMedico() {
		return new Medico(new AsignacionObserverMock());
	}

	private Ambulancia newAmbulancia() {
		return new Ambulancia(new AsignacionObserverMock());
	}

	@Test
	public void consultaAmbulancias() {
		Set<Ambulancia> ambulanciasDisponibles = this.locator.getAmbulanciasDisponibles();

		Assert.assertTrue(ambulanciasDisponibles.contains(this.ambulancia));
		Assert.assertTrue(ambulanciasDisponibles.contains(this.ambulancia2));
		Assert.assertTrue(ambulanciasDisponibles.contains(this.ambulancia3));

		Assert.assertFalse(ambulanciasDisponibles.contains(newAmbulancia()));
	}

	@Test
	public void consultaConAmbulanciasOcupadas() {
		this.ambulancia.ocupada();
		Set<Ambulancia> ambulanciasDisponibles = this.locator.getAmbulanciasDisponibles();

		Assert.assertFalse(ambulanciasDisponibles.contains(this.ambulancia));

		Assert.assertTrue(ambulanciasDisponibles.contains(this.ambulancia2));
		Assert.assertTrue(ambulanciasDisponibles.contains(this.ambulancia3));
	}

	@Test
	public void consultaMedicosDisponibles() {
		Set<Medico> medicosDisponibles = this.locator.getMedicosDisponibles();
		Assert.assertTrue(medicosDisponibles.contains(this.medico));

		Assert.assertFalse(medicosDisponibles.contains(newMedico()));
	}

	@Test
	public void consultaPacientesAsignados() {
		Medico favaloro = newMedico();
		favaloro.asignar(new Paciente("Johann Sebastian", "Mastropiero", "Masculino", "38"));
		favaloro.asignar(new Paciente("Homero", "Simpson", "Masculino", "40"));
		Assert.assertEquals(2, favaloro.getCantidadPacientesAsignados());
	}
}
