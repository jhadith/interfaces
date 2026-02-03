/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import repository.CitaRepository;
import service.CitaService;

public class HU02DisponibilidadTiempoRealFrame extends BaseHistoriaFrame {

    private final JLabel lblEstado;

    public HU02DisponibilidadTiempoRealFrame() {
        super("HU-02", "Disponibilidad en tiempo real",
                "Como Usuario, quiero ver los espacios libres actualizados al instante para evitar errores al elegir mi turno.");

        lblEstado = new JLabel("Disponibilidad: (simulada) Lista");
        lblEstado.setBorder(new EmptyBorder(6, 6, 6, 6));

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> {
            lblEstado.setText("Disponibilidad: (simulada) Actualizada a las " + LocalDateTime.now().toLocalTime().withSecond(0));
            UiKit.ok(this, "Disponibilidad actualizada correctamente.");
        });

        JPanel left = new JPanel(new GridLayout(0, 1, 10, 10));
        left.add(lblEstado);
        left.add(btnRefrescar);

        getContentPane().add(UiKit.wrap(left, 14), BorderLayout.WEST);
        setSize(650, 360);
        setLocationRelativeTo(null);
    }

    @Override
    protected String validateInputs(LocalDateTime selected) {
        String base = super.validateInputs(selected);
        if (base != null) return base;

        if (selected.isBefore(LocalDateTime.now())) {
            return "No se permite seleccionar horarios en pasado.";
        }
        return null;
    }

    private static final CitaService service = new CitaService();

@Override
protected void onConfirm(LocalDateTime selected) {
    // si existe cita con ese horario => ocupado
    boolean ocupado = new CitaRepository()
            .existsByFechaHora(selected);

    if (ocupado) {
        UiKit.error(this, "Horario OCUPADO.\nFecha/Hora: " + selected);
    } else {
        UiKit.ok(this, "Horario LIBRE.\nFecha/Hora: " + selected);
    }
}
}
