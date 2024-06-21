/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DND_QUIZ;

import javax.swing.JOptionPane;

/**
 *
 * @author DAFFA
 */
public interface ExceptionError {
    static void errorMessage(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
