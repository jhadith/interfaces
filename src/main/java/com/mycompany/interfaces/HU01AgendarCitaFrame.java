/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;
import model.Cita;

public class HU01AgendarCitaFrame extends BaseHistoriaFrame {

    private final JComboBox<String> cbServicio;

    public HU01AgendarCitaFrame() {
        super("HU-01", "Agendamiento de citas en línea",
                "Como Usuario, quiero agendar una cita seleccionando fecha y hora para asegurar mi atención sin acudir presencialmente.");

        JPanel extra = new JPanel(new GridLayout(0, 1, 8, 8));
        extra.setBorder(new EmptyBorder(0, 0, 0, 0));

        cbServicio = new JComboBox<>(new String[]{
                "Medicina General", "Odontología", "Pediatría", "Fisioterapia", "Psicología"
        });

        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.add(new JLabel("Servicio/Especialidad:"), BorderLayout.WEST);
        row.add(cbServicio, BorderLayout.CENTER);

        extra.add(row);

        getContentPane().add(UiKit.wrap(extra, 14), BorderLayout.WEST);
        pack();
        setSize(620, 360);
        setLocationRelativeTo(null);
    }

    @Override
    protected String validateInputs(LocalDateTime selected) {
        String base = super.validateInputs(selected);
        if (base != null) return base;

        if (selected.isBefore(LocalDateTime.now())) {
            return "No puedes agendar en una fecha/hora pasada.";
        }
        if (cbServicio.getSelectedItem() == null) {
            return "Selecciona un servicio/especialidad.";
        }
        return null;
    }
    
    private static final service.CitaService service = new service.CitaService();

@Override
protected void onConfirm(LocalDateTime selected) {
    String servicio = (String) cbServicio.getSelectedItem();
    String id = "CITA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    Cita nueva = new Cita(id, servicio, selected, "Asignado", "");
    try {
        service.registrarCita(nueva);
        UiKit.ok(this, "Cita agendada correctamente.\nID: " + id +
                "\nServicio: " + servicio + "\nFecha/Hora: " + selected);
    } catch (Exception ex) {
        UiKit.error(this, ex.getMessage());
    }
}}
