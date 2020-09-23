import java.awt.Image;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author derly
 */
public class AccesoSistema extends javax.swing.JFrame {

    /**
     * Creates new form AccesoSistema
     */
    private boolean visible = false;
    public AccesoSistema() {
        initComponents();
        Image img = new ImageIcon("icon.png").getImage();
        Image newImg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        L_Visible.setIcon(new ImageIcon(newImg));
        ManejoArchivo objManejo = new ManejoArchivo();
        var strError = "";
        if(objManejo.ValidationUserFiles()){
            objManejo.CreationFilesUsers(strError);
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

        jLabel1 = new javax.swing.JLabel();
        TF_Usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BT_Ingresar = new javax.swing.JButton();
        BT_CrearUsuario = new javax.swing.JButton();
        BT_Salir = new javax.swing.JButton();
        TF_Password = new javax.swing.JPasswordField();
        L_Visible = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Ingresar usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ingresar contraseña:");

        BT_Ingresar.setText("INGRESAR");
        BT_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_IngresarActionPerformed(evt);
            }
        });

        BT_CrearUsuario.setText("CREAR USUARIO");
        BT_CrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_CrearUsuarioActionPerformed(evt);
            }
        });

        BT_Salir.setText("SALIR");
        BT_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SalirActionPerformed(evt);
            }
        });

        TF_Password.setText("Ingrese password");

        L_Visible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L_VisibleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(BT_Salir))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TF_Usuario)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BT_Ingresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BT_CrearUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TF_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L_Visible, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TF_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Visible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(TF_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BT_Ingresar)
                    .addComponent(BT_CrearUsuario)
                    .addComponent(BT_Salir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_IngresarActionPerformed

        File Archivo = new File("C:/MEIA/usuario.txt");
        File Bitacora = new File("C:/MEIA/bitacora_usuario.txt");
        var strError = "";
        ManejoArchivo objManejo = new ManejoArchivo();
        if(objManejo.CantidadRegistros(Archivo, strError) != 0 || 
                objManejo.CantidadRegistros(Bitacora, strError) != 0){
            if(!TF_Usuario.getText().equals("") && !String.valueOf(TF_Password.getPassword()).equals("")){
                objManejo.RegresarPrincipio(Archivo, strError);
                var strActual = objManejo.BuscarLinea(Archivo, TF_Usuario.getText(), strError, 0, 9);
                if(strActual.equals("")){ 
                    strActual = objManejo.BuscarLinea(Bitacora, TF_Usuario.getText(), strError, 0, 9);
                }
                if(!strActual.equals("")){
                    var split = strActual.split(Pattern.quote("|"));
                    var objUsuario = new ManejoUsuario();
                    if(String.valueOf(TF_Password.getPassword()).equals(objUsuario.decrypt(split[3]))){
                        //INGRESO AL SISTEMA
                        JOptionPane.showMessageDialog(null, "Bienvenido", "EXITO", 1);
                        if(split[4].equals("1")){
                            Data.getData().setRole("1");
                        }else{
                            Data.getData().setRole("0");
                        }
                        var sistema = new AplicacionMenu();
                        sistema.L_Bienvenida.setText("BIENVENIDO:" + split[0]);
                        sistema.Dato.setText(split[0]);
                        sistema.Dato.setVisible(false);
                        try
                        {
                            Image img = new ImageIcon(split[8]).getImage();
                            Image newImg = img.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
                            sistema.L_Image.setIcon(new ImageIcon(newImg));
                        } catch(Exception ex){
                            strError = ex.getMessage();
                        }
                        sistema.setVisible(true);
                        this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "FALLO", 1);
                    }
                }
                else{
                    int iRespuesta = JOptionPane.showConfirmDialog(null, "¿Desea crearlo?", "No existe el usuario ingresado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (iRespuesta == 0) 
                    {            
                        var fCrearUsuario = new CrearUsuario();
                        Data.getData().setRole("0"); 
                        fCrearUsuario.setVisible(true);
                        this.dispose();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Llenar todos los campos", "FALLO", 1);
            }
        }
        else{
            int iRespuesta = JOptionPane.showConfirmDialog(null, "¿Desea crearlo?", "No existe ningun usuario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (iRespuesta == 0) 
            {
                var fCrearUsuario = new CrearUsuario();
                Data.getData().setRole("1"); 
                fCrearUsuario.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_BT_IngresarActionPerformed

    private void BT_CrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_CrearUsuarioActionPerformed
        File Archivo = new File("C:/MEIA/usuario.txt");
        File Bitacora = new File("C:/MEIA/bitacora_usuario.txt");
        var fCrearUsuario = new CrearUsuario();
        ManejoArchivo objManejo = new ManejoArchivo();
        var strError = "";
        if(objManejo.CantidadRegistros(Archivo, strError) != 0 || 
                objManejo.CantidadRegistros(Bitacora, strError) != 0){ 
            Data.getData().setRole("0"); 
        }
        else{
            Data.getData().setRole("1");
        }
        fCrearUsuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BT_CrearUsuarioActionPerformed

    private void BT_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SalirActionPerformed
        int iRespuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir? ", "¿Salir?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (iRespuesta == 0) 
        {            
            System.exit(0);
        } 
    }//GEN-LAST:event_BT_SalirActionPerformed

    private void L_VisibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L_VisibleMouseClicked
        visible = !visible;
        if(visible){
            TF_Password.setEchoChar((char)0);
        }
        else{
            TF_Password.setEchoChar('*');
        }
    }//GEN-LAST:event_L_VisibleMouseClicked

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
            java.util.logging.Logger.getLogger(AccesoSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccesoSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccesoSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccesoSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccesoSistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_CrearUsuario;
    private javax.swing.JButton BT_Ingresar;
    private javax.swing.JButton BT_Salir;
    private javax.swing.JLabel L_Visible;
    private javax.swing.JPasswordField TF_Password;
    private javax.swing.JTextField TF_Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    private String Pattern(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
