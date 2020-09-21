import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.RowFilter.Entry;
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
    //Lectura linea por linea
    public String LecturaLinea(File Archivo, String strError, int pos){
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
                        if(pos == cont){
                            br.close();
                            fReader.close();
                            return Linea;
                        }
                    }
                    Linea = br.readLine();
                    cont++;
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
    //Lectura completa
    public ArrayList<String> LecturaCompleta(File Archivo, String strError){
        ArrayList<String> lista = new ArrayList();
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
                        lista.add(Linea);
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
            } catch (IOException ex) {
                strError= ex.getMessage();
            }
        } catch (FileNotFoundException ex) {
            strError= ex.getMessage();
        }
        return lista;
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
                            br.close();
                            fReader.close();
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
    //Cantidad total de registros
    public int cantVigente(File Archivo, String strError, int lastPos, int vigente){
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
                        var split = Linea.split(Pattern.quote("|"));
                        if(split[lastPos].equals(vigente)){
                            cont++;
                        }
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
            writer.write("nombre simbolico:usuario\n");
            writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario creacion:root\n");
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:root\n");
            writer.write("# registros:0\n");
            writer.write("registros activos:0\n");
            writer.write("registros incactivos:0\n");
            writer.close();
            writer = new FileWriter(pathFileBitaDesc);
            writer.write("nombre simbolico:bitacora_usuario\n");
            writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario creacion:root\n");
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:root\n");
            writer.write("# registros:0\n");
            writer.write("registros activos:0\n");
            writer.write("registros incactivos:0\n");
            writer.write("max_reorganizacion:3\n");
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
    //Obtener maximo de organizacion
    public int maximoReorganizar(){
        File Bita = new File("C:/MEIA/desc_bitacora_usuario.txt");
        var strError = "";
        var split = LecturaLinea(Bita, strError, 8).split(Pattern.quote(":"));
        return Integer.parseInt(split[1]);
    }
    //Insertar ordenado
    public void orderInsert(File Archivo, String strContenido, String strError){
        RegresarPrincipio(Archivo, strError);
        var list = LecturaCompleta(Archivo, strError);
        list.add(strContenido);
        PrintWriter writer = new PrintWriter(Archivo);
        writer.print("");
        writer.close();
        Map<String, Integer> listDesorder = new HashMap<String, Integer>();
        for(int i = 0; i < list.size(); i++){
            var split = list.get(i).split(Pattern.quote("|"));
            listDesorder.put(split[0], i);
        }
        Map<String, Integer> listOrder = new TreeMap<String, Integer>(listDesorder);
        for (Map.Entry<String, Integer> entry : listOrder.entrySet()) {
            Escritura(Archivo, list.get(entry.getValue()), strError, true);
        }
    }
    //Limpieza bitacora
    public void LimpiarBitacora(){
        File Bita = new File("C:/MEIA/bitacora_usuario.txt");
        File Archivo = new File("C:/MEIA/usuario.txt");
        var strError = "";
        var actual = "";
        var cont = 0;
        while(cont < CantidadRegistros(Archivo, strError) && !(actual = LecturaLinea(Bita, strError, cont)).equals("")){
            var split = actual.split(Pattern.quote("|"));
            if(split[9].equals("1")){
                orderInsert(Archivo, actual, strError);
            }
        }
    }
}
