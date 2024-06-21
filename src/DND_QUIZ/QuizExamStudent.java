package DND_QUIZ;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import ConnectionProvider.ConnectionProvider;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * Kelas ini merepresentasikan GUI untuk ujian kuis mahasiswa.
 * Implementasi dari interface AnswerCheckable, Submittable, dan Question.
 * Memungkinkan pengguna untuk menjawab pertanyaan, memeriksa jawaban,
 * dan mengirimkan nilai ujian ke database.
 * 
 * @author DINA
 */
public class QuizExamStudent extends javax.swing.JFrame implements AnswerCheckable, Submittable, Question, ExceptionError {
    
    private String questionID = "1";
    private String answer;
    private int marks = 0;
    
    /**
     * Memeriksa jawaban yang dipilih oleh mahasiswa dan menghitung nilai.
     */
    @Override
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
            marks = marks + 10;
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
    
    /**
     * Menampilkan pertanyaan ke layar berdasarkan nomor soal.
     */
    @Override
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
            ExceptionError.errorMessage(e);
        }
    }
    
     /**
     * Mengirimkan nilai ujian ke database dan menampilkan jendela konfirmasi.
     */
    @Override
    public void submit(){
        String noDaftar = lblNoDaftar.getText();
        answerCheck();
        
        try{
            Connection cont = ConnectionProvider.getConnected();
            Statement st = cont.createStatement();
            st.executeUpdate("UPDATE studentdata SET nilai = '" + marks + "' WHERE no_pendaftaran = '" + noDaftar + "'");
            String grade = String.valueOf(marks);
            
            setVisible(false);
            new SuccessfullySubmitted(grade).setVisible(true);
        }catch(Exception e){
            ExceptionError.errorMessage(e);
        }
    }
    
    /**
     * Konstruktor untuk inisialisasi kelas QuizExamStudent.
     * Menginisialisasi komponen GUI.
     */
    public QuizExamStudent() {
        initComponents();
    }
    
    /**
     * Konstruktor untuk inisialisasi kelas QuizExamStudent dengan nomor pendaftaran.
     * Menginisialisasi komponen GUI, menampilkan nama dan tanggal, serta pertanyaan pertama.
     * 
     * @param noDaftar Nomor pendaftaran mahasiswa
     */
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
            ExceptionError.errorMessage(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNoDaftar = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblNoSoal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDateDisplay = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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

        lblName.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 0));
        lblName.setText("Daffa Dians");
        getContentPane().add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nomor Pendaftaran:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        lblNoDaftar.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNoDaftar.setForeground(new java.awt.Color(255, 255, 0));
        lblNoDaftar.setText("1");
        getContentPane().add(lblNoDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah Soal:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        lblNoSoal.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNoSoal.setForeground(new java.awt.Color(255, 255, 0));
        lblNoSoal.setText("00");
        getContentPane().add(lblNoSoal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 0));
        jLabel11.setText("10");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nomor Soal:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));

        lblDateDisplay.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblDateDisplay.setForeground(new java.awt.Color(255, 255, 0));
        lblDateDisplay.setText(".");
        getContentPane().add(lblDateDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblDate.setText("Date:");
        getContentPane().add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Algerian", 1, 50)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("welcome");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 22, -1, -1));

        lblQuestion.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblQuestion.setText("Question Demo?");
        getContentPane().add(lblQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, -1, -1));

        opt1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt1.setText("opt1");
        opt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt1ActionPerformed(evt);
            }
        });
        getContentPane().add(opt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, -1));

        opt2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt2.setText("opt2");
        opt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt2ActionPerformed(evt);
            }
        });
        getContentPane().add(opt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, -1));

        opt3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt3.setText("opt3");
        opt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt3ActionPerformed(evt);
            }
        });
        getContentPane().add(opt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));

        opt4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        opt4.setText("opt4");
        opt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt4ActionPerformed(evt);
            }
        });
        getContentPane().add(opt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, -1, -1));

        nextBtn.setBackground(new java.awt.Color(85, 161, 229));
        nextBtn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nextBtn.setForeground(new java.awt.Color(255, 255, 255));
        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        getContentPane().add(nextBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, 140, -1));

        submitBtn.setBackground(new java.awt.Color(85, 161, 229));
        submitBtn.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        getContentPane().add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(975, 600, 130, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_backgrounds_images/kolom kuis.jpg"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Implementasi aksi-aksi yang terkait dengan tombol dan RadioButton
    
    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        answerCheck();
        question();
    }//GEN-LAST:event_nextBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        int a = confirmSubmission();
        
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDateDisplay;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNoDaftar;
    private javax.swing.JLabel lblNoSoal;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JButton nextBtn;
    private javax.swing.JRadioButton opt1;
    private javax.swing.JRadioButton opt2;
    private javax.swing.JRadioButton opt3;
    private javax.swing.JRadioButton opt4;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
