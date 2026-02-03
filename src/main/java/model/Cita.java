/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Cita {
 private String id;             // ej: CITA-8F12ABCD
    private String servicio;        // Medicina General...
    private LocalDateTime fechaHora;
    private String estado;          // Asignado / Confirmada / Cancelada
    private String personal;        // Dr./Dra.

    public Cita() {}

    public Cita(String id, String servicio, LocalDateTime fechaHora, String estado, String personal) {
        this.id = id;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.personal = personal;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getServicio() { return servicio; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getPersonal() { return personal; }
    public String getEstado() { return estado; }
public void setEstado(String estado) { this.estado = estado; }

public void setId(String id) { this.id = id; }
public void setServicio(String servicio) { this.servicio = servicio; }
public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

public void setPersonal(String personal) { this.personal = personal; }

}