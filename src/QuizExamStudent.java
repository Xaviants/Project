/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Project.ConnectionProvider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author DINA
 */
public class QuizExamStudent extends javax.swing.JFrame {
    
    public String questionID = "1";
    public String answer;
    public int minute = 0;
    public int second = 0;
    public int marks = 0;
    public Timer time;
    
    //Checking Answer
    public void answerCheck(){
        String studentAnswer = "";
        
        if(opt1.isSelected()){
            studentAnswer = opt1.getText();
        }else if(opt2.isSelected()){
            studentAnswer = opt2.getText();
        }else if(opt3.isSelected()){
            studentAnswer = opt3.getText();
        }else{
            studentAnswer = opt4.getText();
        }
        
        if(studentAnswer.equals(answer)){
            marks = marks + 1;
            String grade = String.valueOf(marks);
            lblNilai.setText(grade);
        }
        
        //Question Number Change
        int questionIDChange = Integer.parseInt(questionID);
        questionIDChange = questionIDChange + 1;
        questionID = String.valueOf(questionIDChange);
        
        //Clear RadioButton
        opt1.setSelected(false);
        opt2.setSelected(false);
        opt3.setSelected(false);
        opt4.setSelected(false);
        
        //Last Question Hide Next Button
        if(questionID.equals("10")){
            nextBtn.setVisible(false);
        }
    }    
    
    public void question(){
        try{
            Connection cont = ConnectionProvider.getConnected();
            Statement st = cont.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM question WHERE id = '" + questionID + "'");
            
            while(rst.next()){
                lblNoSoal.setText(rst.getString(1));
                lblQuestion.setText(rst.getString(2));
                opt1.setText(rst.getString(3));
                opt2.setText(rst.getString(4));
                opt3.setText(rst.getString(5));
                opt4.setText(rst.getString(6));
                answer = rst.getString(7);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void submit(){
        String noDaftar = lblNoDaftar.getText();
        answerCheck();
        
        try{
            Connection cont = ConnectionProvider.getConnected();
            Statement st = cont.createStatement();
            st.executeUpdate("UPDATE studentdata SET nilai = '" + marks + "' WHERE no_pendaftaran = '" + noDaftar + "'");
            String grade = String.valueOf(marks);
            
            JOptionPane.showMessageDialog(null, "Kamu mendapatkan nilai " + grade, "Hasil Quiz", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Creates new form quizExamStudent
     */
    public QuizExamStudent() {
        initComponents();
    }
    
    public QuizExamStudent(String noDaftar) {
        initComponents();
        lblNoDaftar.setText(noDaftar);
        
        //Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        lblDateDisplay.setText(dateFormat.format(date));
        
        //First Question and Student Details
        try{
            Connection cont = ConnectionProvider.getConnected();
            Statement st = cont.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM studentdata WHERE no_pendaftaran = '" + noDaftar + "'");
            
            while(rs.next()){
                lblName.setText(rs.getString(2));
            }
            
            ResultSet rst = st.executeQuery("SELECT * FROM question WHERE id = '" + questionID + "'");
            
            while(rst.next()){
                lblNoSoal.setText(rst.getString(1));
                lblQuestion.setText(rst.getString(2));
                opt1.setText(rst.getString(3));
                opt2.setText(rst.getString(4));
                opt3.setText(rst.getString(5));
                opt4.setText(rst.getString(6));
                answer = rst.getString(7);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        //Time Program
        setLocationRelativeTo(this);
        
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblSec.setText(String.valueOf(second));
                lblMin.setText(String.valueOf(minute));
                
                if(second == 60){
                    second = 0;
                    minute++;
                    
                    if(minute == 10){
                        time.stop();
                        answerCheck();
                        submit();
                    }
                    second++;
                }else{
                    second++;
                }
            }
        });
        
        time.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMin = new javax.swing.JLabel();
        lblSec = new javax.swing.JLabel();
        lblDateDisplay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTimeTaken = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTotalTime = new javax.swing.JLabel();
        lblTime1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNilai = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblNoDaftar = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblNoSoal = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        opt1 = new javax.swing.JRadioButton();
        opt2 = new javax.swing.JRadioButton();
        opt3 = new javax.swing.JRadioButton();
        opt4 = new javax.swing.JRadioButton();
        nextBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMin.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblMin.setForeground(new java.awt.Color(255, 0, 0));
        lblMin.setText("00");
        jPanel1.add(lblMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 70, -1, -1));

        lblSec.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblSec.setForeground(new java.awt.Color(255, 0, 0));
        lblSec.setText("00");
        jPanel1.add(lblSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 70, 50, -1));

        lblDateDisplay.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblDateDisplay.setText(".");
        jPanel1.add(lblDateDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index student.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));

        jLabel3.setFont(new java.awt.Font("Algerian", 1, 50)); // NOI18N
        jLabel3.setText("welcome");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 22, -1, -1));

        lblTimeTaken.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblTimeTaken.setText("Time Taken:");
        jPanel1.add(lblTimeTaken, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, -1, -1));

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblDate.setText("Date:");
        jPanel1.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, -1, -1));

        lblTotalTime.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblTotalTime.setText("Total Time:");
        jPanel1.add(lblTotalTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, -1, -1));

        lblTime1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblTime1.setText("10 Minute");
        jPanel1.add(lblTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 120));

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNilai.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNilai.setText("00");
        jPanel2.add(lblNilai, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Nilai:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Nomor Pendaftaran:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setText("Nama:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        lblName.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblName.setText("Daffa Dians");
        jPanel2.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        lblNoDaftar.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNoDaftar.setText("10000");
        jPanel2.add(lblNoDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel10.setText("Jumlah Soal:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel11.setText("10");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel12.setText("Nomor Soal:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        lblNoSoal.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNoSoal.setText("00");
        jPanel2.add(lblNoSoal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 380, 600));

        lblQuestion.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblQuestion.setText("Question Demo?");
        getContentPane().add(lblQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        opt1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt1.setText("jRadioButton1");
        opt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt1ActionPerformed(evt);
            }
        });
        getContentPane().add(opt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, -1));

        opt2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt2.setText("jRadioButton2");
        opt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt2ActionPerformed(evt);
            }
        });
        getContentPane().add(opt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        opt3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt3.setText("jRadioButton3");
        opt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt3ActionPerformed(evt);
            }
        });
        getContentPane().add(opt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, -1));

        opt4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt4.setText("jRadioButton4");
        opt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt4ActionPerformed(evt);
            }
        });
        getContentPane().add(opt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        nextBtn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Next.png"))); // NOI18N
        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        getContentPane().add(nextBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, 140, -1));

        submitBtn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        getContentPane().add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(975, 600, 130, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pages background student.jpg"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        answerCheck();
        question();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to Submit?", "Select", JOptionPane.YES_NO_OPTION);
        
        if(a == 0){
            answerCheck();
            submit();
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void opt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt1ActionPerformed
        if(opt1.isSelected()){
            opt2.setSelected(false);
            opt3.setSelected(false);
            opt4.setSelected(false);
        }
    }//GEN-LAST:event_opt1ActionPerformed

    private void opt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt2ActionPerformed
        if(opt2.isSelected()){
            opt1.setSelected(false);
            opt3.setSelected(false);
            opt4.setSelected(false);
        }
    }//GEN-LAST:event_opt2ActionPerformed

    private void opt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt3ActionPerformed
        if(opt3.isSelected()){
            opt1.setSelected(false);
            opt2.setSelected(false);
            opt4.setSelected(false);
        }
    }//GEN-LAST:event_opt3ActionPerformed

    private void opt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt4ActionPerformed
        if(opt4.isSelected()){
            opt1.setSelected(false);
            opt2.setSelected(false);
            opt3.setSelected(false);
        }
    }//GEN-LAST:event_opt4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizExamStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDateDisplay;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNilai;
    private javax.swing.JLabel lblNoDaftar;
    private javax.swing.JLabel lblNoSoal;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblSec;
    private javax.swing.JLabel lblTime1;
    private javax.swing.JLabel lblTimeTaken;
    private javax.swing.JLabel lblTotalTime;
    private javax.swing.JButton nextBtn;
    private javax.swing.JRadioButton opt1;
    private javax.swing.JRadioButton opt2;
    private javax.swing.JRadioButton opt3;
    private javax.swing.JRadioButton opt4;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
