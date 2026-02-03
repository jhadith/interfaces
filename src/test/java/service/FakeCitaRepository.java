package service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import model.Cita;
import repository.CitaRepository;

// Fake repo para pruebas: NO usa Mongo.
public class FakeCitaRepository extends CitaRepository {

    private final Map<String, Cita> data = new HashMap<>();
    private final Map<LocalDateTime, String> byTime = new HashMap<>();

    @Override
    public void insert(Cita c) {
        data.put(c.getId(), c);
        byTime.put(c.getFechaHora(), c.getId());
    }

    @Override
    public Cita findById(String id) {
        return data.get(id);
    }

    @Override
    public boolean existsByFechaHora(LocalDateTime fechaHora) {
        return byTime.containsKey(fechaHora);
    }

    @Override
    public void updateEstadoPersonal(String id, String estado, String personal) {
        Cita c = data.get(id);
        if (c == null) return;
        c.setEstado(estado);
        c.setPersonal(personal);
    }

    @Override
    public void deleteById(String id) {
        Cita c = data.remove(id);
        if (c != null) byTime.remove(c.getFechaHora());
    }
}
