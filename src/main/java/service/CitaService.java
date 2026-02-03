package service;

import java.time.LocalDateTime;
import model.Cita;
import repository.CitaRepository;

public class CitaService {

    private final CitaRepository repo;

    public CitaService() {
        this.repo = new CitaRepository();
    }

    // Constructor para pruebas
    public CitaService(CitaRepository repo) {
        this.repo = repo;
    }

    public void registrarCita(Cita c) {
        if (c.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se puede agendar en el pasado.");
        }
        if (repo.existsByFechaHora(c.getFechaHora())) {
            throw new IllegalStateException("Ese horario ya está ocupado.");
        }
        repo.insert(c);
    }

    public Cita buscarPorId(String id) {
        return repo.findById(id);
    }

    public void confirmarCita(String id, String personal) {
        repo.updateEstadoPersonal(id, "Confirmada", personal);
    }

    public void eliminarCita(String id) {
        repo.deleteById(id);
    }
}
