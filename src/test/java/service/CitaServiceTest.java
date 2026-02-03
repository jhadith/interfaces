package service;

import model.Cita;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CitaServiceTest {

    @Test
    void registrarCita_valida_debeInsertar() {
        FakeCitaRepository fakeRepo = new FakeCitaRepository();
        CitaService service = new CitaService(fakeRepo);

        LocalDateTime futuro = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
        Cita c = new Cita("CITA-TEST1", "Medicina General", futuro, "Asignado", "");

        assertDoesNotThrow(() -> service.registrarCita(c));
        assertNotNull(service.buscarPorId("CITA-TEST1"));
    }

    @Test
    void registrarCita_enPasado_debeLanzarError() {
        FakeCitaRepository fakeRepo = new FakeCitaRepository();
        CitaService service = new CitaService(fakeRepo);

        LocalDateTime pasado = LocalDateTime.now().minusDays(1);
        Cita c = new Cita("CITA-TEST2", "Odontología", pasado, "Asignado", "");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.registrarCita(c));

        assertTrue(ex.getMessage().toLowerCase().contains("pasado"));
    }

    @Test
    void registrarCita_horarioDuplicado_debeLanzarError() {
        FakeCitaRepository fakeRepo = new FakeCitaRepository();
        CitaService service = new CitaService(fakeRepo);

        LocalDateTime mismoHorario = LocalDateTime.now().plusHours(5).withSecond(0).withNano(0);

        Cita c1 = new Cita("CITA-TEST3", "Pediatría", mismoHorario, "Asignado", "");
        Cita c2 = new Cita("CITA-TEST4", "Pediatría", mismoHorario, "Asignado", "");

        service.registrarCita(c1);

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> service.registrarCita(c2));

        assertTrue(ex.getMessage().toLowerCase().contains("ocupado"));
    }
    @Test
void confirmarCita_debeActualizarEstadoYPersonal() {
    FakeCitaRepository fakeRepo = new FakeCitaRepository();
    CitaService service = new CitaService(fakeRepo);

    LocalDateTime futuro = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
    Cita c = new Cita("CITA-TEST5", "Psicología", futuro, "Asignado", "");
    service.registrarCita(c);

    service.confirmarCita("CITA-TEST5", "Dra. Ana");

    Cita updated = service.buscarPorId("CITA-TEST5");
    assertNotNull(updated);
    assertEquals("Confirmada", updated.getEstado());
    assertEquals("Dra. Ana", updated.getPersonal());
}

@Test
void eliminarCita_debeEliminarPorId() {
    FakeCitaRepository fakeRepo = new FakeCitaRepository();
    CitaService service = new CitaService(fakeRepo);

    LocalDateTime futuro = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
    Cita c = new Cita("CITA-TEST6", "Fisioterapia", futuro, "Asignado", "");
    service.registrarCita(c);

    service.eliminarCita("CITA-TEST6");

    assertNull(service.buscarPorId("CITA-TEST6"));
}

}
