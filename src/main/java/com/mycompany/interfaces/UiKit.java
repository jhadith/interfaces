/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public final class UiKit {
    private UiKit() {}

    public static void applyLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equalsIgnoreCase(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    public static JPanel wrap(JComponent c, int pad) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new EmptyBorder(pad, pad, pad, pad));
        p.add(c, BorderLayout.CENTER);
        return p;
    }

    public static JLabel title(String text) {
        JLabel l = new JLabel(text);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 18f));
        return l;
    }

    public static JLabel hint(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(new Color(90, 90, 90));
        return l;
    }

    public static JButton primaryButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setFont(b.getFont().deriveFont(Font.BOLD, 13f));
        return b;
    }

    public static void ok(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}