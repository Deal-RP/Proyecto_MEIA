import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ManejoArchivo {
    //Escritura en el archivo
    public boolean Escritura(File Archivo, String strContenido, String strError, boolean Append){
        try
        {
            FileWriter fWriter = new FileWriter(Archivo, Append);
            BufferedWriter bw = new BufferedWriter(fWriter);
            try
            {
                bw.write(strContenido + System.getProperty( "line.separator" ));
                bw.close();
                fWriter.close();
                return true;
            } catch (FileNotFoundException ex) {
                strError= ex.getMessage();
                return false;
            }
        } catch(IOException ex){
            strError= ex.getMessage();
            return false;
        }
    }
    //Busqueda en el archivo
    public String BuscarLinea(File Archivo, String strBuscar, String strError, int pos, int lastPos){
        try 
        {
            FileReader fReader = new FileReader(Archivo);
            BufferedReader br = new BufferedReader(fReader);
            try
            {
                var Linea = br.readLine();
                while(Linea != null)
                {
                    if(!"".equals(Linea))
                    {
                        var split = Linea.split(Pattern.quote("|"));
                        if(split[pos].equals(strBuscar) && split[lastPos].equals("1")){
                            return Linea;
                        }
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
            } catch (IOException ex) {
                strError= ex.getMessage();
                return "";
            }
        } catch (FileNotFoundException ex) {
            strError= ex.getMessage();
            return "";
        }
        return "";
    }
    //Lectura completa del archivo
    public int Lectura(File Archivo, String strError, List<String> info){
        var cont = 0;
        try 
        {
            FileReader fReader = new FileReader(Archivo);
            BufferedReader br = new BufferedReader(fReader);
            try
            {
                var Linea = br.readLine();
                while(Linea != null)
                {
                    if(!"".equals(Linea))
                    {
                        info.add(Linea);
                        cont++;
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
            } catch (IOException ex) {
                strError= ex.getMessage();
                return 0;
            }
        } catch (FileNotFoundException ex) {
            strError= ex.getMessage();
            return 0;
        }
        return cont;
    }
    //Cantidad de usuarios
    public int CantidadRegistros(File Archivo, String strError){
        var cont = 0;
        try 
        {
            FileReader fReader = new FileReader(Archivo);
            BufferedReader br = new BufferedReader(fReader);
            try
            {
                var Linea = br.readLine();
                while(Linea != null)
                {
                    if(!"".equals(Linea))
                    {
                        cont++;
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
            } catch (IOException ex) {
                strError= ex.getMessage();
                return 0;
            }
        } catch (FileNotFoundException ex) {
            strError= ex.getMessage();
            return 0;
        }
        return cont;
    }
    //Regresar cursor al principio del txt
    public boolean RegresarPrincipio(File Archivo, String strError){
        try
        {
            RandomAccessFile rac = new RandomAccessFile(Archivo, "r");
            rac.seek(0);
        }catch (IOException ex){
            strError = ex.getMessage();
            return false;
        }
        return true;
    }
    //method to create the files of the user in the folder, the user file only create and the desc user create and write the description 
    //The user of creation and modification we put root and the records are 0
    public void CreationFilesUsers(String strError){
        try {
            File pathFileUser = new File("C:/MEIA/usuario.txt");
            File pathFileUserDesc = new File("C:/MEIA/desc_usuario.txt");
            File pathFileBita = new File("C:/MEIA/bitacora_usuario.txt");
            File pathFileBitaDesc = new File("C:/MEIA/desc_bitacora_usuario.txt");
            pathFileUser.createNewFile();
            pathFileUserDesc.createNewFile();
            pathFileBita.createNewFile();
            pathFileBitaDesc.createNewFile();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writer = new FileWriter(pathFileUserDesc);
            writer.write("nombre simbolico: usuario\n");
            writer.write("fecha creacion: "+dateFormat.format(date)+"\n");
            writer.write("usuario creacion: root\n");
            writer.write("fecha modificacion: "+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion: root\n");
            writer.write("# registros: 0\n");
            writer.write("registros activos: 0\n");
            writer.write("registros incactivos: 0\n");
            writer.close();
        } catch (IOException ex) {
            //TODO: handle exception
             strError = ex.getMessage();
        }

    }
    //method to validate if exists the folder and files of the users and if is not exist create them 
    // return true if the file users do not exist and false if they exists
    public boolean ValidationUserFiles(){
        File pathFolder = new File("C:/MEIA");
        File pathFileUser = new File("C:/MEIA/usuario.txt");
        File pathFileUserDesc = new File("C:/MEIA/desc_usuario.txt");
        File pathFileBita = new File("C:/MEIA/bitacora_usuario.txt");
        File pathFileBitaDesc = new File("C:/MEIA/desc_bitacora_usuario.txt");
        File pathFolderFoto = new File("C:/MEIA/fotografia");
        
        if (pathFolder.exists()){
            if(!pathFileUser.exists() || !pathFileUserDesc.exists() || !pathFileBita.exists() || !pathFileBitaDesc.exists()){
                pathFileUser.delete();
                pathFileUserDesc.delete();
                pathFileBita.delete();
                pathFileBitaDesc.delete();
            }
            else{
                return false;
            }
        }
        else{
            pathFolder.mkdir();
            pathFolderFoto.mkdir();
        }
        return true;
    }
}
