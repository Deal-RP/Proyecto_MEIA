/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Management.Data;
import Management.ManejoArchivo;
import java.awt.Image;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author derly
 */
public class ListaDistribucion extends javax.swing.JFrame {

    /**
     * Creates new form ListaDistribucion
     */
    public ListaDistribucion() {
        initComponents();
        
        var dataUser = Data.getData();
        var user = dataUser.getUser();
        var strError = "";
        var objManejoArchivo = new ManejoArchivo();
        
        var listaMostrar = new DefaultListModel();
        File Archivo = new File("C:/MEIA/lista.txt");
        var lista = objManejoArchivo.LecturaCompleta(Archivo, strError);
        for (int i = 0; i < lista.size(); i++) {
            var splitAux = lista.get(i).split(Pattern.quote("|"));
            if(splitAux[1].equals(user) && splitAux[5].equals("1")){
               listaMostrar.addElement(splitAux[0] + "-" + splitAux[2]);
            }
        }

        Archivo = new File("C:/MEIA/bitacora_lista.txt");
        lista = objManejoArchivo.LecturaCompleta(Archivo, strError);
        for (int i = 0; i < lista.size(); i++) {
            var splitAux = lista.get(i).split(Pattern.quote("|"));
            if(splitAux[1].equals(user) && splitAux[5].equals("1")){
               listaMostrar.addElement(splitAux[0] + "-" + splitAux[2]);
            }
        }
        lListas.setModel(listaMostrar);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lListas = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TF_Nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BT_crear = new javax.swing.JButton();
        BT_Eliminar = new javax.swing.JButton();
        BT_Modificar = new javax.swing.JButton();
        BT_Salir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Listas de distribucion");

        lListas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lListas);

        jLabel2.setText("Crear lista de distribucion");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Descripcion:");

        BT_crear.setText("Crear");
        BT_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_crearActionPerformed(evt);
            }
        });

        BT_Eliminar.setText("Eliminar");
        BT_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EliminarActionPerformed(evt);
            }
        });

        BT_Modificar.setText("Modificar");
        BT_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ModificarActionPerformed(evt);
            }
        });

        BT_Salir.setText("Salir");
        BT_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SalirActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BT_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BT_Modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TF_Nombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(BT_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BT_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TF_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BT_crear)
                            .addComponent(BT_Salir)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BT_Eliminar)
                            .addComponent(BT_Modificar))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_crearActionPerformed
        if(TF_Nombre.getText().equals("") && jTextArea1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Llene los campos", "FALLO", 1);
        }
        else{
            var objManejoArchivo = new ManejoArchivo();
            var dataUser = Data.getData();
            var user = dataUser.getUser();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var linea = TF_Nombre.getText() + "|" + user + "|" + jTextArea1.getText() + "|0|" + dateFormat.format(date) + "|1";
            var strError = objManejoArchivo.insertarLinea(linea, "lista", TF_Nombre.getText(), user, 5);

            if (strError.equals("Registro ya existe")) {
                JOptionPane.showMessageDialog(null, strError, "FALLO", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Lista creada", "EXITO", 1);
            }

            var listaMostrar = new DefaultListModel();
            File Archivo = new File("C:/MEIA/lista.txt");
            var lista = objManejoArchivo.LecturaCompleta(Archivo, strError);
            for (int i = 0; i < lista.size(); i++) {
                var splitAux = lista.get(i).split(Pattern.quote("|"));
                if(splitAux[1].equals(user) && splitAux[5].equals("1")){
                   listaMostrar.addElement(splitAux[0] + "-" + splitAux[2]);
                }
            }

            Archivo = new File("C:/MEIA/bitacora_lista.txt");
            lista = objManejoArchivo.LecturaCompleta(Archivo, strError);
            for (int i = 0; i < lista.size(); i++) {
                var splitAux = lista.get(i).split(Pattern.quote("|"));
                if(splitAux[1].equals(user) && splitAux[5].equals("1")){
                   listaMostrar.addElement(splitAux[0] + "-" + splitAux[2]);
                }
            }
            lListas.setModel(listaMostrar);
        }
    }//GEN-LAST:event_BT_crearActionPerformed

    private void BT_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_EliminarActionPerformed
        var seleccion = lListas.getSelectedValue();
        var strError = "";
        var dataUser = Data.getData();
        var user = dataUser.getUser();
        if (seleccion != null) {
            int iRespuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la lista? ", "¿Eliminar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (iRespuesta == 0) {
                File Archivo = new File("C:/MEIA/lista.txt");
                File Bitacora = new File("C:/MEIA/bitacora_lista.txt");
                var objManejoArchivo = new ManejoArchivo();
                var  split = seleccion.split(Pattern.quote("-"));
                var ArchivoUser = objManejoArchivo.BuscarLinea2(Archivo, split[0], user, strError, 0, 5);
                var ArchivoBita = objManejoArchivo.BuscarLinea2(Bitacora, split[0], user, strError, 0, 5);               
                if(!ArchivoBita.equals("") ){
                  var  splitAux = ArchivoBita.split(Pattern.quote("|"));
                    var strContenido = splitAux[0] + "|" + splitAux[1] + "|" + splitAux[2] + "|" + splitAux[3] + "|" + splitAux[4] + "|" + "0";
                     objManejoArchivo.Modificar(Bitacora, ArchivoBita, strContenido, strError);
                     objManejoArchivo.ModifyFilesDescBita("lista", 5, user, false, strError);

                }
                else
                {
                    var splitAux = ArchivoUser.split(Pattern.quote("|"));
                     var strContenido = splitAux[0] + "|" + splitAux[1] + "|" + splitAux[2] + "|" + splitAux[3] + "|" + splitAux[4] + "|" + "0";
                  objManejoArchivo.Modificar(Archivo, ArchivoUser, strContenido, strError);
                  objManejoArchivo.ModifyFilesDescUser("lista", 5, user,false, strError);
                }
                var listaMostrar = new DefaultListModel();
                var lista = objManejoArchivo.LecturaCompleta(Archivo, strError);
                for (int i = 0; i < lista.size(); i++) {
                    var splitAux = lista.get(i).split(Pattern.quote("|"));
                    if(splitAux[1].equals(user) && splitAux[5].equals("1")){
                       listaMostrar.addElement(splitAux[0]+ "-" + splitAux[2]);
                    }
                }

                lista = objManejoArchivo.LecturaCompleta(Bitacora, strError);
                for (int i = 0; i < lista.size(); i++) {
                    var splitAux = lista.get(i).split(Pattern.quote("|"));
                    if(splitAux[1].equals(user) && splitAux[5].equals("1")){
                       listaMostrar.addElement(splitAux[0]+ "-" + splitAux[2]);
                    }
                }
                lListas.setModel(listaMostrar);
                JOptionPane.showMessageDialog(null, "Lista eliminada", "EXITO", 1);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una lista", "ERROR", 1);
        }
    }//GEN-LAST:event_BT_EliminarActionPerformed

    private void BT_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_ModificarActionPerformed
        var seleccion = lListas.getSelectedValue();
        var strError = "";
        var dataUser = Data.getData();
        var user = dataUser.getUser();
        if (seleccion != null) {
            int iRespuesta = JOptionPane.showConfirmDialog(null, "¿Desea modificar la lista? ", "¿Modificar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (iRespuesta == 0) {
                var modificar = JOptionPane.showInputDialog("Ingrese nueva descripcion");
                if(!modificar.equals("")){
                    File Archivo = new File("C:/MEIA/lista.txt");
                    File Bitacora = new File("C:/MEIA/bitacora_lista.txt");
                    var objManejoArchivo = new ManejoArchivo();
                    var  split = seleccion.split(Pattern.quote("-"));
                    var ArchivoUser = objManejoArchivo.BuscarLinea2(Archivo, split[0], user, strError, 0, 5);
                    var ArchivoBita = objManejoArchivo.BuscarLinea2(Bitacora, split[0], user, strError, 0, 5);               
                    if(!ArchivoBita.equals("") ){
                        var  splitAux = ArchivoBita.split(Pattern.quote("|"));
                        var strContenido = splitAux[0] + "|" + splitAux[1] + "|" + modificar + "|" + splitAux[3] + "|" + splitAux[4] + "|" + splitAux[5];

                        objManejoArchivo.Modificar(Bitacora, ArchivoBita, strContenido, strError);
                        objManejoArchivo.ModifyFilesDescBita("lista", 5, user, false, strError);

                    }
                    else
                    {
                        var splitAux = ArchivoUser.split(Pattern.quote("|"));
                        var strContenido = splitAux[0] + "|" + splitAux[1] + "|" + modificar + "|" + splitAux[3] + "|" + splitAux[4] + "|" + splitAux[5];
                      objManejoArchivo.Modificar(Archivo, ArchivoUser, strContenido, strError);
                      objManejoArchivo.ModifyFilesDescUser("lista", 5, user,false, strError);
                    }
                    var listaMostrar = new DefaultListModel();
                    var lista = objManejoArchivo.LecturaCompleta(Archivo, strError);
                    for (int i = 0; i < lista.size(); i++) {
                        var splitAux = lista.get(i).split(Pattern.quote("|"));
                        if(splitAux[1].equals(user) && splitAux[5].equals("1")){
                           listaMostrar.addElement(splitAux[0]+ "-" + splitAux[2]);
                        }
                    }

                    lista = objManejoArchivo.LecturaCompleta(Bitacora, strError);
                    for (int i = 0; i < lista.size(); i++) {
                        var splitAux = lista.get(i).split(Pattern.quote("|"));
                        if(splitAux[1].equals(user) && splitAux[5].equals("1")){
                           listaMostrar.addElement(splitAux[0]+ "-" + splitAux[2]);
                        }
                    }
                    lListas.setModel(listaMostrar);
                    JOptionPane.showMessageDialog(null, "Lista modificada", "EXITO", 1);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione una lista", "ERROR", 1);
        }
    }//GEN-LAST:event_BT_ModificarActionPerformed

    private void BT_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SalirActionPerformed
        File Archivo = new File("C:/MEIA/usuario.txt");
        var objManejoArchivo = new ManejoArchivo();
        var strError = "";
        var dataUser = Data.getData();
        var user = dataUser.getUser();
        var ArchivoUser = objManejoArchivo.BuscarLinea(Archivo, user, strError, 0, 9);
        var split = ArchivoUser.split(Pattern.quote("|"));
        var sistema = new AplicacionMenu();
        sistema.L_Bienvenida.setText("BIENVENIDO:" + split[0]);
        sistema.Dato.setText(split[0]);
        sistema.Dato.setVisible(false);
        if(split[4].equals("1")){
            sistema.L_Rol.setText("Rol: Administrador");
        }else{
            sistema.L_Rol.setText("Rol: Usuario");
        }

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
    }//GEN-LAST:event_BT_SalirActionPerformed

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
            java.util.logging.Logger.getLogger(ListaDistribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaDistribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaDistribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaDistribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaDistribucion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_Eliminar;
    private javax.swing.JButton BT_Modificar;
    private javax.swing.JButton BT_Salir;
    private javax.swing.JButton BT_crear;
    private javax.swing.JTextField TF_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<String> lListas;
    // End of variables declaration//GEN-END:variables
}