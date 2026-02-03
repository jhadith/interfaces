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
import service.CitaService;

public class HU03ConfirmacionCitaFrame extends BaseHistoriaFrame {

    private final JTextField txtPersonal;
private final JTextField txtIdCita;

    public HU03ConfirmacionCitaFrame() {
        
        super("HU-03", "Confirmación automática de citas",
                "Como Usuario, quiero recibir un mensaje de confirmación para tener constancia de que mi cita fue registrada exitosamente.");

        txtPersonal = new JTextField("Dr./Dra. Asignado/a (simulado)");
        JPanel left = new JPanel(new GridLayout(0, 1, 10, 10));
        left.setBorder(new EmptyBorder(0,0,0,0));

        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.add(new JLabel("Personal que atiende:"), BorderLayout.WEST);
        row.add(txtPersonal, BorderLayout.CENTER);
  txtIdCita = new JTextField();
        JPanel rowId = new JPanel(new BorderLayout(10, 0));
rowId.add(new JLabel("ID de la cita:"), BorderLayout.WEST);
rowId.add(txtIdCita, BorderLayout.CENTER);

left.add(rowId);
left.add(row);

        getContentPane().add(UiKit.wrap(left, 14), BorderLayout.WEST);
        setSize(700, 360);
        setLocationRelativeTo(null);
      

    }

   private static final CitaService service = new CitaService();

@Override
protected void onConfirm(LocalDateTime selected) {
    String id = txtIdCita.getText().trim();
    String personal = txtPersonal.getText().trim();

    try {
        if (service.buscarPorId(id) == null) {
            UiKit.error(this, "No existe una cita con ID: " + id);
            return;
        }
        service.confirmarCita(id, personal);
        UiKit.ok(this, "Cita CONFIRMADA.\nID: " + id +
                "\nFecha/Hora: " + selected +
                "\nPersonal: " + personal);
    } catch (Exception ex) {
        UiKit.error(this, ex.getMessage());
    }
}
}