package Management;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;

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
    // Modificar
    public boolean Modificar(File Archivo,String strContenidoBusqueda ,String strContenido, String strError)
    {
     try 
     {
//         PrintWriter writer = new PrintWriter(file);
//         writer.print("");
//        writer.close();
            FileReader fReader = new FileReader(Archivo);
            BufferedReader br = new BufferedReader(fReader);
             var Linea = br.readLine();
              FileWriter fWriter = new FileWriter(Archivo, true);
                  BufferedWriter bw = new BufferedWriter(fWriter);
                PrintWriter writer = new PrintWriter(Archivo);
                 writer.print("");
                 writer.close();
                while(Linea != null)
                {                    
                 if(Linea.equals(strContenidoBusqueda))
                 {                
                  bw.write(strContenido + System.getProperty( "line.separator" ));                                                
                 }
                 else
                 {
                 bw.write(Linea + System.getProperty( "line.separator" )); 
                 }
                   Linea = br.readLine();         
                }               
                bw.close();
                fWriter.close();
                br.close();
                fReader.close();                
     }
     catch(Exception ex)
     {strError= ex.getMessage(); return false;}
     return true;
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
    
    //Busqueda de varias lineas en archivo
    public DefaultListModel BuscarContacto(File Archivo, String strBuscar, int cant, String strError, int lastPos){
        var lista = new DefaultListModel();
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
                        var cont = 0;
                        var split = Linea.split(Pattern.quote("|"));
                        var splitBuscar = strBuscar.split(Pattern.quote("|"));
                        if(!splitBuscar[0].equals("")){
                            if(split[0].equals(splitBuscar[0]) && split[lastPos].equals("1")){
                                cont++;
                            }
                        }
                        if(!splitBuscar[1].equals("")){
                            if(split[1].equals(splitBuscar[1]) && split[lastPos].equals("1")){
                                cont++;
                            }
                        }
                        if(!splitBuscar[2].equals("")){
                            if(split[2].equals(splitBuscar[2]) && split[lastPos].equals("1")){
                                cont++;
                            }
                        }
                        if(cont == cant){
                            lista.addElement(split[0] + "|" + split[1] + "|" + split[2]);
                        }
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
            } catch (IOException ex) {
                strError= ex.getMessage();
                return lista;
            }
        } catch (FileNotFoundException ex) {
            strError= ex.getMessage();
            return lista;
        }
        return lista;
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
                        if(Integer.parseInt(split[lastPos]) == vigente){
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
    public void CreationFilesUsers(String nombre, String strError){
        try {
            File pathFileUser = new File("C:/MEIA/" + nombre + ".txt");
            File pathFileUserDesc = new File("C:/MEIA/desc_" + nombre + ".txt");
            File pathFileBita = new File("C:/MEIA/bitacora_" + nombre + ".txt");
            File pathFileBitaDesc = new File("C:/MEIA/desc_bitacora_" + nombre + ".txt");
            pathFileUser.createNewFile();
            pathFileUserDesc.createNewFile();
            pathFileBita.createNewFile();
            pathFileBitaDesc.createNewFile();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writer = new FileWriter(pathFileUserDesc);
            writer.write("nombre simbolico:" + nombre + "\n");
            writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario creacion:root\n");
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:root\n");
            writer.write("# registros:0\n");
            writer.write("registros activos:0\n");
            writer.write("registros incactivos:0\n");
            writer.close();
            writer = new FileWriter(pathFileBitaDesc);
            writer.write("nombre simbolico:bitacora_" + nombre + "\n");
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
        File pathFileContact =  new File("C:/MEIA/contactos.txt");
        File pathFileContactDesc = new File("C:/MEIA/desc_contactos.txt");
        File pathFileLista = new File("C:/MEIA/lista.txt");
        File pathFileListaDesc =  new File("C:/MEIA/desc_lista.txt");
        File pathFileListaUsuario = new File("C:/MEIA/Lista_usuario.txt");
        File pathFileListaUsuarioInd = new File("C:/MEIA/ind_Lista_usuario.txt");
        
        if (pathFolder.exists()){
            if(!pathFileUser.exists() || !pathFileUserDesc.exists() || !pathFileBita.exists() || !pathFileBitaDesc.exists()
                    || !pathFileContact.exists() || !pathFileContactDesc.exists() || !pathFileLista.exists() 
                    || !pathFileListaDesc.exists() || !pathFileListaUsuario.exists() || !pathFileListaUsuarioInd.exists()){
                pathFileUser.delete();
                pathFileUserDesc.delete();
                pathFileBita.delete();
                pathFileBitaDesc.delete();
                pathFileContact.delete();
                pathFileContactDesc.delete();
                pathFileLista.delete();
                pathFileListaDesc.delete();
                pathFileListaUsuario.delete();
                pathFileListaUsuarioInd.delete();
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
        try
        {
            PrintWriter writer = new PrintWriter(Archivo);
            writer.print("");
            writer.close();
        }catch(FileNotFoundException ex){
            strError = ex.getMessage();
        }
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
        var cont = 0;
        var actual = LecturaLinea(Bita, strError, cont);
        while(cont < CantidadRegistros(Bita, strError) && !actual.equals("")){
            var split = actual.split(Pattern.quote("|"));
            if(split[9].equals("1")){
                orderInsert(Archivo, actual, strError);
            }
            cont ++;
            actual = LecturaLinea(Bita, strError, cont);
        }
        try
        {
            PrintWriter writer = new PrintWriter(Bita);
            writer.print("");
            writer.close();
        }catch(FileNotFoundException ex){
            strError = ex.getMessage();
        }
    }
    //Limpieza usuario
    public void LimpiarUsuario(){
        File oldfile = new File("C:/MEIA/usuario.txt");
        File newfile = new File("C:/MEIA/usuario_temp.txt");
        oldfile.renameTo(newfile);
        File inputFile = new File("C:/MEIA/usuario_temp.txt");
        File outputFile = new File("C:/MEIA/usuario.txt");
        try {
          BufferedReader reader = new BufferedReader(new FileReader(inputFile));
          BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String Linea;

        while((Linea = reader.readLine()) != null) {
            var split = Linea.split(Pattern.quote("|"));
            if(split[9].equals("0")){ 
                continue;
            }
            writer.write(Linea + System.getProperty("line.separator"));
        }       

        writer.close();
        reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        inputFile.delete();
    }
    //Modificacion desc_usuario_bitacora
    public void ModifyFilesDescBita(String nombre, int lastPos, String user, boolean creacion, String strError){
        try {
            File pathFileBitaDesc = new File("C:/MEIA/desc_bitacora_" + nombre + ".txt");
            var cant = maximoReorganizar();
            var fechaCreacion = LecturaLinea(pathFileBitaDesc, strError, 1);
            var split = fechaCreacion.split(Pattern.quote(":"));
            fechaCreacion = split[1] +":"+ split[2]+":"+ split[3];
            var userCreacion = LecturaLinea(pathFileBitaDesc, strError, 2);
            split = userCreacion.split(Pattern.quote(":"));
            userCreacion = split[1];
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writer = new FileWriter(pathFileBitaDesc);
            writer.write("nombre simbolico:bitacora_" + nombre + "\n");
            if(creacion){
                writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
                writer.write("usuario creacion:"+user+"\n");
            }
            else{
                writer.write("fecha creacion:"+fechaCreacion+"\n");
                writer.write("usuario creacion:"+userCreacion+"\n");
            }
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:"+user+"\n");
            writer.write("# registros:"+CantidadRegistros(new File("C:/MEIA/bitacora_" + nombre + ".txt"), strError)+"\n");
            writer.write("registros activos:"+cantVigente(new File("C:/MEIA/bitacora_" + nombre + ".txt"), strError, lastPos, 1)+"\n");
            writer.write("registros inactivos:"+cantVigente(new File("C:/MEIA/bitacora_" + nombre + ".txt"), strError, lastPos, 0)+"\n");
            writer.write("max_reorganizacion:"+cant+"\n");
            writer.close();
        } catch (IOException ex) {
            //TODO: handle exception
             strError = ex.getMessage();
        }
    }
    //Modificacion desc_usuario
    public void ModifyFilesDescUser(String nombre, int lastPos, String user, boolean creacion, String strError){
        try {
            File pathFileBitaDesc = new File("C:/MEIA/desc_" + nombre + ".txt");
            var fechaCreacion = LecturaLinea(pathFileBitaDesc, strError, 1);
            var split = fechaCreacion.split(Pattern.quote(":"));
            fechaCreacion = split[1] +":"+ split[2]+":"+ split[3];
            var userCreacion = LecturaLinea(pathFileBitaDesc, strError, 2);
            split = userCreacion.split(Pattern.quote(":"));
            userCreacion = split[1];
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writer = new FileWriter(pathFileBitaDesc);
            writer.write("nombre simbolico:" + nombre + "\n");
            if(creacion){
                writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
                writer.write("usuario creacion:"+user+"\n");
            }
            else{
                writer.write("fecha creacion:"+fechaCreacion+"\n");
                writer.write("usuario creacion:"+userCreacion+"\n");
            }
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:"+user+"\n");
            writer.write("# registros:"+CantidadRegistros(new File("C:/MEIA/" + nombre + ".txt"), strError)+"\n");
            writer.write("registros activos:"+cantVigente(new File("C:/MEIA/" + nombre + ".txt"), strError, lastPos, 1)+"\n");
            writer.write("registros incactivos:"+cantVigente(new File("C:/MEIA/" + nombre + ".txt"), strError, lastPos, 0)+"\n");
            writer.close();
        } catch (IOException ex) {
            //TODO: handle exception
             strError = ex.getMessage();
        }
    }
}