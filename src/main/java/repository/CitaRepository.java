/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Cita;
import model.MongoDB;

public class CitaRepository {

    private final MongoCollection<Document> col;

    public CitaRepository() {
        this.col = MongoDB.database().getCollection("citas");
    }

    private Document toDoc(Cita c) {
        return new Document("_id", c.getId())
                .append("servicio", c.getServicio())
                .append("fechaHora", c.getFechaHora().toString())
                .append("estado", c.getEstado())
                .append("personal", c.getPersonal());
    }

    private Cita fromDoc(Document d) {
        return new Cita(
                d.getString("_id"),
                d.getString("servicio"),
                LocalDateTime.parse(d.getString("fechaHora")),
                d.getString("estado"),
                d.getString("personal")
        );
    }

    // CREATE
    public void insert(Cita c) {
        col.insertOne(toDoc(c));
    }

    // READ by id
    public Cita findById(String id) {
        Document d = col.find(Filters.eq("_id", id)).first();
        return d == null ? null : fromDoc(d);
    }

    // READ all
    public List<Cita> findAll() {
        List<Cita> out = new ArrayList<>();
        for (Document d : col.find()) out.add(fromDoc(d));
        return out;
    }

    // READ by fechaHora (para evitar duplicados)
    public boolean existsByFechaHora(LocalDateTime fechaHora) {
        return col.find(Filters.eq("fechaHora", fechaHora.toString())).first() != null;
    }

    // UPDATE estado/personal
    public void updateEstadoPersonal(String id, String estado, String personal) {
        col.updateOne(
                Filters.eq("_id", id),
                Updates.combine(
                        Updates.set("estado", estado),
                        Updates.set("personal", personal)
                )
        );
    }

    // DELETE
    public void deleteById(String id) {
        col.deleteOne(Filters.eq("_id", id));
    }
}