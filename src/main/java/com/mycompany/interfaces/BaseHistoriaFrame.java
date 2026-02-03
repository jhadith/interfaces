/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.*;
import java.util.Date;

public abstract class BaseHistoriaFrame extends JFrame {

    protected final JSpinner spFecha;
    protected final JSpinner spHora;
    protected final JButton btnConfirmar;

    protected BaseHistoriaFrame(String huId, String titulo, String descripcionCorta) {
        super(huId + " - " + titulo);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(560, 360);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(14, 14, 14, 14));

        // Header
        JPanel header = new JPanel(new GridLayout(0, 1, 6, 6));
        header.add(UiKit.title(huId + ": " + titulo));
        header.add(UiKit.hint("<html><body style='width:520px'>" + descripcionCorta + "</body></html>"));
        root.add(header, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;

        // Fecha
        g.gridx = 0; g.gridy = 0; g.weightx = 0;
        form.add(new JLabel("Fecha:"), g);

        spFecha = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor deFecha = new JSpinner.DateEditor(spFecha, "yyyy-MM-dd");
        spFecha.setEditor(deFecha);

        g.gridx = 1; g.gridy = 0; g.weightx = 1;
        form.add(spFecha, g);

        // Hora
        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        form.add(new JLabel("Hora:"), g);

        spHora = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE));
        JSpinner.DateEditor deHora = new JSpinner.DateEditor(spHora, "HH:mm");
        spHora.setEditor(deHora);

        g.gridx = 1; g.gridy = 1; g.weightx = 1;
        form.add(spHora, g);

        root.add(form, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnConfirmar = UiKit.primaryButton("Confirmar");
        footer.add(btnConfirmar);
        root.add(footer, BorderLayout.SOUTH);

        setContentPane(root);

        btnConfirmar.addActionListener(e -> {
            LocalDateTime ldt = getSelectedDateTime();
            String err = validateInputs(ldt);
            if (err != null) {
                UiKit.error(this, err);
                return;
            }
            onConfirm(ldt);
        });
    }

    protected LocalDateTime getSelectedDateTime() {
        Date dFecha = (Date) spFecha.getValue();
        Date dHora = (Date) spHora.getValue();

        ZoneId zone = ZoneId.systemDefault();

        LocalDate fecha = dFecha.toInstant().atZone(zone).toLocalDate();
        LocalTime hora = dHora.toInstant().atZone(zone).toLocalTime().withSecond(0).withNano(0);

        return LocalDateTime.of(fecha, hora);
    }

    /** Retorna null si todo OK, o un mensaje de error si falla validación. */
    protected String validateInputs(LocalDateTime selected) {
        if (selected == null) return "Fecha y hora inválidas.";
        return null;
    }

    /** Implementa la lógica del botón Confirmar (y muestra alertas). */
    protected abstract void onConfirm(LocalDateTime selected);
}

