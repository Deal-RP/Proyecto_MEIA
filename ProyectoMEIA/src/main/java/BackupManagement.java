
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class BackupManagement {
    
    // method to do the backup. The parameter that receives is the path destiny where the user want to make the backup
    public void DoBackup(String pathDestiny_){

            var pathDestiny = pathDestiny_;
            var folderSource = new File("C:/MEIA");
            var folderDestiny = new File(pathDestiny +"/MEIA Backup");

            if (folderDestiny.exists()){

                folderDestiny.delete();
                folderDestiny.mkdir();

            }else{
                folderDestiny.mkdir();
            }


             try{
                    FileUtils.copyDirectory(folderSource, folderDestiny);
                } 
             catch(IOException e){

                }
    }
    
    // method to check if the backup files exist if only one exist delet de other and return false 
    // and if the two file exist return true
    public boolean CheckFilesBackup(){

        var pathBackupFile = new File("C:/MEIA/bitacora_backup.txt");
        var pathDescBackupFile = new File("C:/MEIA/desc_bitacora_backup.txt");
        if (pathBackupFile.exists()) {
            if (pathDescBackupFile.exists()) {
                return true;
            }else {
                pathBackupFile.delete();
                return false;
            }

        }else{
            if (pathDescBackupFile.exists()) {
                pathDescBackupFile.delete();
                return false;
            }else{
                return false;
            }
        }
    }

    // Method to Check if the folder MEIA exist, if this exist return true and if not return false
    public boolean CheckFolerMeia(){
        var path = new File("C:/MEIA");
        if (path.exists()) {
            return true;
        }
        else{
            return false;
        }
    }

    // method to create the files of the backup if they don't exist 
    public void CreateFiles(String pathDestiny){
        var pathBackupFile = new File("C:/MEIA/bitacora_backup.txt");
        var pathDescBackupFile = new File("C:/MEIA/desc_bitacora_backup.txt");


        var dataUser = Data.getData();
        var user = dataUser.getUser();


        try {

            pathBackupFile.createNewFile();
            pathDescBackupFile.createNewFile();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            var writerBackup = new FileWriter("C:/MEIA/bitacora_backup.txt");
            writerBackup.write(pathDestiny + "|" + user + "|" + dateFormat.format(date)+"\n");
            writerBackup.close();


            var writerDescBackup = new FileWriter("C:/MEIA/desc_bitacora_backup.txt");
            writerDescBackup.write("nombre_simbolico:bitacora_backup \n");
            writerDescBackup.write("fecha_creacion:" + dateFormat.format(date) + "\n");
            writerDescBackup.write("usuario_creacion:" + user + "\n");
            writerDescBackup.write("fecha_modificacion:" + dateFormat.format(date) + "\n");
            writerDescBackup.write("usuario_modificacion:" + user + "\n");
            writerDescBackup.write("#_registros:1 \n");
            writerDescBackup.close();

        } catch (IOException e) {
            //TODO: handle exception
        }
    }


    public void ModificateFilesBackup(String pathDestiny){

        try {

            var dataUser=Data.getData();
            var user = dataUser.getUser();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writerBackup = new FileWriter("C:/MEIA/bitacora_backup.txt",true);
            String modificateDate = dateFormat.format(date);
            writerBackup.write(pathDestiny + "|" + user + "|" + modificateDate+"\n");
            writerBackup.close();

            ModificateFileDes(modificateDate);

        } catch (Exception e) {
            //TODO: handle exception
        }


    }

    //method to modificate the file desc bitacora to change de modificate user, modificate date and count of registers
    public void ModificateFileDes(String date){


        try {
            var dataUser=Data.getData();
            var user = dataUser.getUser();

            Path path = Paths.get("C:/MEIA/bitacora_backup.txt");
            long lineCount = Files.lines(path).count();

            var descBackupFile = new File("C:/MEIA/desc_bitacora_backup.txt");

            var arrayData = new String[6];
            var counter = 0;

            BufferedReader br = new BufferedReader(new FileReader(descBackupFile)); 

            String st; 
            while ((st = br.readLine()) != null){
                arrayData[counter] = st;
                counter ++;
            }

            br.close();

            for (int i = 3; i < 6; i++) {


                if (i==3) {

                     var split = arrayData[i].split(":");
                    arrayData[i] = split[0] + ":" + date ;

                }else if (i==4) {
                     var split = arrayData[i].split(":");
                    arrayData[i] = split[0] + ":" + user ;

                }else if (i==5) {
                     var split = arrayData[i].split(":");
                    arrayData[i] = split[0] + ":" + lineCount ;

                }

            }

            var writer = new FileWriter(descBackupFile);

            for (int i = 0; i < arrayData.length; i++) {

                writer.write(arrayData[i] + "\n");

            }

        writer.close();

        } catch (Exception e) {
            //TODO: handle exception

        }

    }

    
    
}
