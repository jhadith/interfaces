/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interfaces;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Administrador
 */
public class Interfaces {

   public static void main(String[] args) {
        UiKit.applyLookAndFeel();

        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Prototipos HU-01 a HU-12");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(520, 520);
            f.setLocationRelativeTo(null);

            JPanel p = new JPanel(new GridLayout(0, 1, 10, 10));
            p.setBorder(new EmptyBorder(14, 14, 14, 14));

            p.add(btn("HU-01 Agendar", () -> new HU01AgendarCitaFrame().setVisible(true)));
            p.add(btn("HU-02 Disponibilidad", () -> new HU02DisponibilidadTiempoRealFrame().setVisible(true)));
            p.add(btn("HU-03 Confirmación", () -> new HU03ConfirmacionCitaFrame().setVisible(true)));
           /* p.add(btn("HU-04 Cancelar/Reprogramar", () -> new HU04CancelacionReprogramacionFrame().setVisible(true)));
            p.add(btn("HU-05 Horarios Personal", () -> new HU05GestionHorariosPersonalFrame().setVisible(true)));
            p.add(btn("HU-06 Usuarios Admin", () -> new HU06GestionUsuariosAdminFrame().setVisible(true)));
            p.add(btn("HU-07 Notificaciones", () -> new HU07NotificacionesCambiosFrame().setVisible(true)));
            p.add(btn("HU-08 Historial", () -> new HU08HistorialCitasFrame().setVisible(true)));
            p.add(btn("HU-09 Marcar Atendida", () -> new HU09MarcarAtendidaFrame().setVisible(true)));
            p.add(btn("HU-10 Solapamientos", () -> new HU10ValidacionSolapamientosFrame().setVisible(true)));
            p.add(btn("HU-11 Reportes", () -> new HU11ReportesGestionFrame().setVisible(true)));
            p.add(btn("HU-12 Acceso Web", () -> new HU12AccesoWebResponsiveFrame().setVisible(true)));
*/
            f.setContentPane(new JScrollPane(p));
            f.setVisible(true);
        });
    }

    private static JButton btn(String text, Runnable action) {
        JButton b = UiKit.primaryButton(text);
        b.addActionListener(e -> action.run());
        return b;
    }
}
