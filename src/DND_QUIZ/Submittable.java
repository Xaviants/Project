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
public interface Submittable {
    void submit();
    
    default int confirmSubmission() {
        return JOptionPane.showConfirmDialog(null, "Apakah kamu benar ingin Submit?", "SELECT OPTION", JOptionPane.YES_NO_OPTION);
    }
}
