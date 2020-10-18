package Management;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            FileReader fReader = new FileReader(Archivo);
            BufferedReader br = new BufferedReader(fReader);
            var Linea = br.readLine();
            FileWriter fWriter = new FileWriter(Archivo, true);
            BufferedWriter bw = new BufferedWriter(fWriter);
            PrintWriter writer = new PrintWriter(Archivo);
            writer.print("");
            writer.close();
            while(Linea != null){                    
                if(Linea.equals(strContenidoBusqueda)){                
                    bw.write(strContenido + System.getProperty( "line.separator" ));                                                
                }
                else{
                    bw.write(Linea + System.getProperty( "line.separator" )); 
                }
                Linea = br.readLine();         
            }               
            bw.close();
            fWriter.close();
            br.close();
            fReader.close();                
        }
        catch(Exception ex){strError= ex.getMessage(); return false;}
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
    public String BuscarLinea2(File Archivo, String strBuscar1, String strBuscar2, String strError, int pos, int lastPos){
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
                        if(split[pos].equals(strBuscar1) && split[pos + 1].equals(strBuscar2) && split[lastPos].equals("1")){
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
        File pathFileContactBita =  new File("C:/MEIA/bitacora_contactos.txt");
        File pathFileContactBitaDesc =  new File("C:/MEIA/desc_bitacora_contactos.txt");
        File pathFileLista = new File("C:/MEIA/lista.txt");
        File pathFileListaDesc =  new File("C:/MEIA/desc_lista.txt");
        File pathFileListaBita = new File("C:/MEIA/bitacora_lista.txt");
        File pathFileListaBitaDesc =  new File("C:/MEIA/desc_bitacora_lista.txt");
        File pathFileListaUsuario = new File("C:/MEIA/Lista_usuario.txt");
        File pathFileListaUsuarioInd = new File("C:/MEIA/ind_Lista_usuario.txt");
        File pathFileListaUsuarioDesc = new File("C:/MEIA/desc_Lista_usuario.txt");
        
        if (pathFolder.exists()){
            if(!pathFileUser.exists() || !pathFileUserDesc.exists() || !pathFileBita.exists() || !pathFileBitaDesc.exists()
                    || !pathFileContact.exists() || !pathFileContactDesc.exists() || !pathFileLista.exists() 
                    || !pathFileListaDesc.exists() || !pathFileContactBita.exists() || !pathFileContactBitaDesc.exists()
                    || !pathFileListaBita.exists() || !pathFileListaBitaDesc.exists() ){//|| !pathFileListaUsuario.exists() || !pathFileListaUsuarioInd.exists() || !pathFileListaUsuarioDesc.exists()){
                pathFileUser.delete();
                pathFileUserDesc.delete();
                pathFileBita.delete();
                pathFileBitaDesc.delete();
                pathFileContact.delete();
                pathFileContactDesc.delete();
                pathFileContactBita.delete();
                pathFileContactBitaDesc.delete();
                pathFileLista.delete();
                pathFileListaDesc.delete();
                pathFileListaBita.delete();
                pathFileListaBitaDesc.delete();
                pathFileListaUsuario.delete();
                pathFileListaUsuarioInd.delete();
                pathFileListaUsuarioDesc.delete();
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
    public int maximoReorganizar(String nombre){
        File Bita = new File("C:/MEIA/desc_bitacora_" + nombre + ".txt");
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
    public void orderInsert2(File Archivo, String strContenido, String strError){
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
            listDesorder.put(split[0] + split[1], i);
        }
        Map<String, Integer> listOrder = new TreeMap<String, Integer>(listDesorder);
        for (Map.Entry<String, Integer> entry : listOrder.entrySet()) {
            Escritura(Archivo, list.get(entry.getValue()), strError, true);
        }
    }
    //Limpieza bitacora
    public void LimpiarBitacora(String nombre){
        File Bita = new File("C:/MEIA/bitacora_" + nombre + ".txt");
        File Archivo = new File("C:/MEIA/" + nombre + ".txt");
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
    public void LimpiarBitacora2(String nombre, int lastPos){
        File Bita = new File("C:/MEIA/bitacora_" + nombre + ".txt");
        File Archivo = new File("C:/MEIA/" + nombre + ".txt");
        var strError = "";
        var cont = 0;
        var actual = LecturaLinea(Bita, strError, cont);
        while(cont < CantidadRegistros(Bita, strError) && !actual.equals("")){
            var split = actual.split(Pattern.quote("|"));
            if(split[lastPos].equals("1")){
                orderInsert2(Archivo, actual, strError);
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
    public void LimpiarPrincipal(String nombre, int lastPos){
        Path destino = Paths.get("C:/MEIA/" + nombre + "_temp.txt");
        Path origen = Paths.get("C:/MEIA/" + nombre + ".txt");
        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {}
        File inputFile = new File("C:/MEIA/" + nombre + "_temp.txt");
        File outputFile = new File("C:/MEIA/" + nombre + ".txt");
        try {
          BufferedReader reader = new BufferedReader(new FileReader(inputFile));
          BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String Linea;

        while((Linea = reader.readLine()) != null) {
            var split = Linea.split(Pattern.quote("|"));
            if(split[lastPos].equals("0")){ 
                continue;
            }
            else{
                writer.write(Linea + System.getProperty("line.separator"));
            }
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
            var cant = maximoReorganizar(nombre);
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
    
    public String insertarLinea (String linea, String nombre, String llave1, String llave2, int lastPos){
        var objManejoArchivo = new ManejoArchivo();
        var dataUser = Data.getData();
        var user = dataUser.getUser();
        File Archivo = new File("C:/MEIA/"+ nombre +".txt");
        File Bita = new File("C:/MEIA/bitacora_" + nombre + ".txt");
        var strError = "";
        var ArchivoUser = objManejoArchivo.BuscarLinea2(Archivo, llave1, llave2, strError, 0, lastPos);       //Cambiar
        var ArchivoBita = objManejoArchivo.BuscarLinea2(Bita, llave1, llave2, strError, 0, lastPos);         //Cambiar
        if(!ArchivoUser.equals("") || !ArchivoBita.equals("")){
            return "Registro ya existe";
        }
        else{
            try
            {
                if(objManejoArchivo.CantidadRegistros(Bita, strError) >= objManejoArchivo.maximoReorganizar(nombre)){
                    if(objManejoArchivo.CantidadRegistros(Archivo, strError) == 0){
                        objManejoArchivo.LimpiarBitacora2(nombre, lastPos);                               //Cambiar
                        objManejoArchivo.ModifyFilesDescUser(nombre, lastPos, user, true, strError);
                    }
                    else{
                        objManejoArchivo.LimpiarBitacora2(nombre, lastPos);                               //Cambiar
                        objManejoArchivo.ModifyFilesDescUser(nombre, lastPos, user, false, strError);
                    }
                }
                if(objManejoArchivo.CantidadRegistros(Bita, strError) == 0){
                    objManejoArchivo.orderInsert2(Bita, linea, strError);                        //Cambiar
                    objManejoArchivo.ModifyFilesDescBita(nombre, lastPos, user, true, strError);
                }
                else{
                    objManejoArchivo.orderInsert2(Bita, linea, strError);                        //Cambiar
                    objManejoArchivo.ModifyFilesDescBita(nombre, lastPos, user, false, strError);
                }
            }catch(Exception ex){
                return ex.getMessage();
            }
        }
        return "Se ha agregado existosamente el usuario";
    }
    
    public void limpiarSalir(String nombre, int lastPos){
        File Archivo = new File("C:/MEIA/" + nombre + ".txt");
        File Bita = new File("C:/MEIA/bitacora_" + nombre + ".txt");
        var strError = "";
        if(CantidadRegistros(Bita, strError) != 0){
            if(CantidadRegistros(Archivo, strError) == 0){
                LimpiarBitacora2(nombre, lastPos);
                ModifyFilesDescUser(nombre, lastPos, "root", true, strError);
            }
            else{
                LimpiarBitacora2(nombre, lastPos);
                ModifyFilesDescUser(nombre, lastPos, "root", false, strError);
            }
            ModifyFilesDescBita(nombre, lastPos, "root", true, strError);
        }
        if(CantidadRegistros(Archivo, strError) != 0){
            LimpiarPrincipal(nombre, lastPos);
            if(CantidadRegistros(Archivo, strError) == 0){
                ModifyFilesDescUser(nombre, lastPos, "root", true, strError);
            }
            else{
                ModifyFilesDescUser(nombre, lastPos, "root", false, strError);
            }
        }
    }
    
    //MANEJO DE ARVHIVO TIPO SECUENCIAL INDEXADO
    boolean modificarIndex(File Archivo, int pos, int newIndex){
        try 
        {
            FileReader fReader = new FileReader(Archivo);
            BufferedReader br = new BufferedReader(fReader);
            var Linea = br.readLine();
            FileWriter fWriter = new FileWriter(Archivo, true);
            BufferedWriter bw = new BufferedWriter(fWriter);
            var cont = 0;
            while(Linea != null){                    
                if(pos == cont){
                    var split = Linea.split(Pattern.quote("|"));
                    bw.write(split[0] + "|" + split[1] + "|" + String.valueOf(newIndex) + "|" + split[3] + "|" 
                            + split[4] + "|" + split[5] + "|" + split[6]);                                                
                }        
            }               
            bw.close();
            fWriter.close();
            br.close();
            fReader.close();                
        }
        catch(Exception ex){return false;}
        return true;
    }
    
    void orderIndex(File Archivo){
        var strError = "";
        var list = LecturaCompleta(Archivo, strError);
        
        Map<String, Integer> listDesorder = new HashMap<String, Integer>();
        for(int i = 1; i <= list.size(); i++){
            var split = list.get(i).split(Pattern.quote("|"));
            if(split[7].equals("1")){
                listDesorder.put(split[3] + split[4] + split[5], i);
            }
        }
        TreeMap<String, Integer> sorted = new TreeMap<>(); 
        sorted.putAll(listDesorder);
        
        for(int i = 0; i <= list.size(); i++){
            var split = list.get(i).split(Pattern.quote("|"));
            var newPos = 0;
            if(sorted.containsKey(split[3] + split[4] + split[5])){
                newPos = sorted.get(split[3] + split[4] + split[5]);
            }
            modificarIndex(Archivo, i, newPos);
        }
        
        //AGREGAR MODIFICAR INCIO EN EL DESCRIPTOR
    }
    
    public void insertar(String nombre, String strContenido, String strError){
        var desc = new File("C:/MEIA/desc_" + nombre + ".txt");
        var num = 0;
        //Obtener num de bloque final
        
        var Archivo = new File("C:/MEIA/" + nombre + num + ".txt");
        
    }
}