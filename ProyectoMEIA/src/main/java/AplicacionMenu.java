import java.io.File;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author derly
 */
public class AplicacionMenu extends javax.swing.JFrame {

    /**
     * Creates new form AplicacionMenu
     */
    public AplicacionMenu() {
        initComponents();
        var dataUser = Data.getData();
        var user = dataUser.getRole();
        if(user.equals("1")){
            L_Rol.setText("Rol: Administrador");
            BT_Baja.setText("Dar de baja");
        }
        else{
            L_Rol.setText("Rol: Usuario");
            BT_CrearUsuario.setVisible(false);
            BT_Busqueda.setVisible(false);
            TF_Baja.setVisible(false);
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

        Dato = new javax.swing.JLabel();
        L_Bienvenida = new javax.swing.JLabel();
        BT_Salir = new javax.swing.JButton();
        L_Image = new javax.swing.JLabel();
        L_Rol = new javax.swing.JLabel();
        BT_Modificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton_crearBackup = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BT_Baja = new javax.swing.JButton();
        BT_Busqueda = new javax.swing.JButton();
        BT_CrearUsuario = new javax.swing.JButton();
        TF_Baja = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Dato.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        L_Bienvenida.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        BT_Salir.setText("CERRAR SESION");
        BT_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SalirActionPerformed(evt);
            }
        });

        BT_Modificar.setText("Modificar");
        BT_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ModificarActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario");

        jButton_crearBackup.setText("Crear");
        jButton_crearBackup.setToolTipText("");
        jButton_crearBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_crearBackupActionPerformed(evt);
            }
        });

        jLabel2.setText("Back up");

        BT_Baja.setText("Darse de baja");
        BT_Baja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_BajaActionPerformed(evt);
            }
        });

        BT_Busqueda.setText("Busqueda de usuario");
        BT_Busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_BusquedaActionPerformed(evt);
            }
        });

        BT_CrearUsuario.setText("Crear nuevo usuario");
        BT_CrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_CrearUsuarioActionPerformed(evt);
            }
        });

        TF_Baja.setText("Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L_Rol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton_crearBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BT_CrearUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BT_Modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BT_Baja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BT_Busqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TF_Baja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(jLabel2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(L_Image, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(BT_Salir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Dato, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(L_Bienvenida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1)
                        .addGap(194, 194, 194))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(L_Bienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BT_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BT_Baja)
                            .addComponent(TF_Baja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BT_Busqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BT_CrearUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_crearBackup)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L_Rol, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Dato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BT_Salir, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SalirActionPerformed
        int iRespuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cerrar sesion? ", "¿Salir?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (iRespuesta == 0) 
        {            
            var inicioSesion = new AccesoSistema();
            inicioSesion.setVisible(true);
            this.dispose();
        } 
    }//GEN-LAST:event_BT_SalirActionPerformed


    private void jButton_crearBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_crearBackupActionPerformed
        // TODO add your handling code here:
        var backup = new Backup();
        backup.setVisible(true);
    }//GEN-LAST:event_jButton_crearBackupActionPerformed

    private void BT_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_ModificarActionPerformed
        // TODO add your handling code here:
        // guardar el dato en el singleton
        //Data.getData().setUser("pablo");git  
         File Archivo = new File("C:/MEIA/usuario.txt");
         File Bitacora = new File("C:/MEIA/bitacora_usuario.txt");
        var objManejoArchivo = new ManejoArchivo();
        String dato = Dato.getText();
        String strError = "";
        var Modificar = new ModificarUsuario();    
        Modificar.TF_Usuariofijo.setText(dato);
        Modificar.TF_Usuariofijo.setEditable(false);
        var ArchivoUser = objManejoArchivo.BuscarLinea(Archivo, dato, strError, 0, 9);
        var ArchivoBita = objManejoArchivo.BuscarLinea(Bitacora, dato, strError, 0, 9);
         String linea = "";
        if (!ArchivoUser.equals("")) {
             linea =  objManejoArchivo.BuscarLinea(Archivo, dato, "", 0, 9);
        }
        else if(!ArchivoBita.equals(""))
        {
              linea =  objManejoArchivo.BuscarLinea(Bitacora, dato, "", 0, 9);
        }        
        var split = linea.split(Pattern.quote("|"));
        String Nombre = split[1];
        String Apellido = split[2];
        String Pasword = split[3];
        var Fecha = split[5];
        String Correo = split[6];
        String Telefono = split[7];
        String path_fotografia = split[8];
        Modificar.TF_NombreModificar.setText(Nombre);
        Modificar.TF_ApellidoModificar.setText(Apellido);
        //Modificar.DC_Fecha.setDate(Fecha);
        Modificar.TF_CorreoModificar.setText(Correo);
        Modificar.TF_TelefonoModificar.setText(Telefono);
        Modificar.TF_FotoModificar.setText(path_fotografia);
                       
        Modificar.setVisible(true);
        this.dispose();
            
        
    }//GEN-LAST:event_BT_ModificarActionPerformed

    private void BT_BajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_BajaActionPerformed
        var dataUser = Data.getData();
        var user = dataUser.getRole();               
        if(user.equals("1")){
            // administrativo
         File Archivo = new File("C:/MEIA/usuario.txt");
         File Bitacora = new File("C:/MEIA/bitacora_usuario.txt");
         var objManejoArchivo = new ManejoArchivo();
        String Usuariodardebaja = TF_Baja.getText();
        String strError = "";
        var ArchivoUser = objManejoArchivo.BuscarLinea(Archivo, Usuariodardebaja, strError, 0, 9);
        var ArchivoBita = objManejoArchivo.BuscarLinea(Bitacora, Usuariodardebaja, strError, 0, 9);               
        if(!ArchivoBita.equals("") ){
          var  split = ArchivoBita.split(Pattern.quote("|"));
            var strContenido = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] +
        "|" + split[4] + "|" + split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + "0";
             objManejoArchivo.Modificar(Bitacora, ArchivoBita, strContenido, strError);
        }
        else
        {
            var split = ArchivoUser.split(Pattern.quote("|"));
             var strContenido = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] +
        "|" + split[4] + "|" + split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + "0";
          objManejoArchivo.Modificar(Archivo, ArchivoUser, strContenido, strError);
        }
        JOptionPane.showMessageDialog(null, "Usuario dado de baja", "EXITO", 1);
        }
        else{            
            File Archivo = new File("C:/MEIA/usuario.txt");
         File Bitacora = new File("C:/MEIA/bitacora_usuario.txt");
         var objManejoArchivo = new ManejoArchivo();
        String Usuariodardebaja = Dato.getText();
        String strError = "";
        var ArchivoUser = objManejoArchivo.BuscarLinea(Archivo, Usuariodardebaja, strError, 0, 9);
        var ArchivoBita = objManejoArchivo.BuscarLinea(Bitacora, Usuariodardebaja, strError, 0, 9);               
        if(!ArchivoBita.equals("") ){
          var  split = ArchivoBita.split(Pattern.quote("|"));
            var strContenido = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] +
        "|" + split[4] + "|" + split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + "0";
             objManejoArchivo.Modificar(Bitacora, ArchivoBita, strContenido, strError);
        }
        else
        {
            var split = ArchivoUser.split(Pattern.quote("|"));
             var strContenido = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] +
        "|" + split[4] + "|" + split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + "0";
          objManejoArchivo.Modificar(Archivo,ArchivoUser, strContenido, strError);
        }
         JOptionPane.showMessageDialog(null, "Usuario dado de baja", "EXITO", 1);        
            var Busqueda = new BusquedaUsuario();
            Busqueda.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_BT_BajaActionPerformed

    private void BT_BusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_BusquedaActionPerformed
        // TODO add your handling code here:
           String dato = Dato.getText();          
           var Busqueda = new BusquedaUsuario();
           Busqueda.L_Usuario.setText(dato);
            Busqueda.setVisible(true);           
            this.dispose();        
    }//GEN-LAST:event_BT_BusquedaActionPerformed

    private void BT_CrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_CrearUsuarioActionPerformed
        // TODO add your handling code here:
           String dato = Dato.getText();          
           var CrearNuevo = new CrearNuevoUsuario();
                CrearNuevo.L_NUSUARIO.setText(dato);
            CrearNuevo.setVisible(true);           
            this.dispose();        
    }//GEN-LAST:event_BT_CrearUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(AplicacionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplicacionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplicacionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplicacionMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplicacionMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_Baja;
    private javax.swing.JButton BT_Busqueda;
    private javax.swing.JButton BT_CrearUsuario;
    private javax.swing.JButton BT_Modificar;
    private javax.swing.JButton BT_Salir;
    public javax.swing.JLabel Dato;
    public javax.swing.JLabel L_Bienvenida;
    public javax.swing.JLabel L_Image;
    public javax.swing.JLabel L_Rol;
    private javax.swing.JTextField TF_Baja;
    private javax.swing.JButton jButton_crearBackup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    private String Pattern(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
