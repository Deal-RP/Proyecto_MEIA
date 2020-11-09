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
    //archivo = archivo donde se va a modificar el registros
    // strContenidoBusqueda = el registro completo a modifcar
    // strContenido = regitro completo modificado
    // strError = error
    // retorna un bool, true si se logro realizar y false sino se logro modificar
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
    // Archivo = el arhcivo donde se desea buscar el dato
    //strBuscar = el dato que se esta buscando 
    // strError = el error 
    // pos = la posicion donde del registro donde se desea buscar el dato
    // lastPos = el numero de la ultima posicion para verificar si el registro esta acitvo o inactivo
    // Retorna el dato que se esta buscando
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
            File pathFileTree = new File("C:/MEIA/tree.txt");
            pathFileUser.createNewFile();
            pathFileUserDesc.createNewFile();
            pathFileBita.createNewFile();
            pathFileBitaDesc.createNewFile();
            pathFileTree.createNewFile();
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
        File pathFileListaUsuario = new File("C:/MEIA/Lista_usuario0.txt");
        File pathFileListaUsuarioDesc = new File("C:/MEIA/desc_Lista_usuario0.txt");
        File pathFileListaUsuarioInd = new File("C:/MEIA/ind_Lista_usuario.txt");
        File pathFileListaUsuarioIndDesc = new File("C:/MEIA/desc_ind_Lista_usuario.txt");
        File pathFileTree = new File("C:/MEIA/tree.txt");
        
        if (pathFolder.exists()){
            if(!pathFileUser.exists() || !pathFileUserDesc.exists() || !pathFileBita.exists() || !pathFileBitaDesc.exists()
                    || !pathFileContact.exists() || !pathFileContactDesc.exists() || !pathFileLista.exists() 
                    || !pathFileListaDesc.exists() || !pathFileContactBita.exists() || !pathFileContactBitaDesc.exists()
                    || !pathFileListaBita.exists() || !pathFileListaBitaDesc.exists()  
                    || !pathFileListaUsuarioInd.exists() || !pathFileListaUsuarioIndDesc.exists() || !pathFileTree.exists()){
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
                pathFileListaUsuarioDesc.delete();
                pathFileListaUsuarioInd.delete();
                pathFileListaUsuarioIndDesc.delete();
                pathFileTree.delete();
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
    public void creationDescListContact(String nombre){
        try {
            File pathFileListaUsuario = new File("C:/MEIA/" + nombre + "0.txt");
            File pathFileListaUsuarioDesc = new File("C:/MEIA/desc_" + nombre + "0.txt");
            File pathFileListaUsuarioInd = new File("C:/MEIA/ind_" + nombre + ".txt");
            File pathFileListaUsuarioIndDesc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
            pathFileListaUsuario.createNewFile();
            pathFileListaUsuarioDesc.createNewFile();
            pathFileListaUsuarioInd.createNewFile();
            pathFileListaUsuarioIndDesc.createNewFile();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writer = new FileWriter(pathFileListaUsuarioDesc);
            writer.write("nombre simbolico:" + nombre + "\n");
            writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario creacion:root\n");
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:root\n");
            writer.write("# registros:0\n");
            writer.write("registros activos:0\n");
            writer.write("registros incactivos:0\n");
            writer.close();
            writer = new FileWriter(pathFileListaUsuarioIndDesc);
            writer.write("nombre simbolico:ind_" + nombre + "\n");
            writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario creacion:root\n");
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:root\n");
            writer.write("# registros:0\n");
            writer.write("registros activos:0\n");
            writer.write("registros incactivos:0\n");
            writer.write("max_reorganizacion:3\n");
            writer.write("registro inicial:0\n");
            writer.write("No. bloques:0\n");
            writer.close();
        } catch (IOException ex) {
            
        }
    }
    
    public void modifyDescInd(String nombre, int lastPos, boolean creacion, String user){
        try{
            var strError = "";
            File pathFileListaUsuarioIndDesc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
            var cant = maximoReorganizar2(nombre);
            var fechaCreacion = LecturaLinea(pathFileListaUsuarioIndDesc, strError, 1);
            var split = fechaCreacion.split(Pattern.quote(":"));
            fechaCreacion = split[1] +":"+ split[2]+":"+ split[3];
            
            var userCreacion = LecturaLinea(pathFileListaUsuarioIndDesc, strError, 2);
            split = userCreacion.split(Pattern.quote(":"));
            userCreacion = split[1];
            
            var numInicial = getNumInicial(nombre);
            var numBloques = getNumBloques(nombre);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            var writer = new FileWriter(pathFileListaUsuarioIndDesc);
            writer.write("nombre simbolico:ind_" + nombre + "\n");
            if(creacion){
                writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
                writer.write("usuario creacion:"+user+"\n");
            }
            else{
                writer.write("fecha creacion:"+fechaCreacion+"\n");
                writer.write("usuario creacion:"+userCreacion+"\n");
            }
            writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
            writer.write("usuario modificacion:" + user + "\n");
            writer.write("# registros:"+CantidadRegistros(new File("C:/MEIA/ind_" + nombre + ".txt"), strError)+"\n");
            writer.write("registros activos:"+cantVigente(new File("C:/MEIA/ind_" + nombre + ".txt"), strError, lastPos, 1)+"\n");
            writer.write("registros inactivos:"+cantVigente(new File("C:/MEIA/ind_" + nombre + ".txt"), strError, lastPos, 0)+"\n");
            writer.write("max_reorganizacion:"+cant+"\n");
            writer.write("registro inicial:" + numInicial + "\n");
            writer.write("No. bloques:" + numBloques + "\n");
            writer.close();
        }catch(IOException ex){
            
        }
    }
    
    public int maximoReorganizar2(String nombre){
        File desc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
        var strError = "";
        var split = LecturaLinea(desc, strError, 8).split(Pattern.quote(":"));
        return Integer.parseInt(split[1]);
    }
    
    public int getNumInicial(String nombre){
        File desc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
        var strError = "";
        var split = LecturaLinea(desc, strError, 9).split(Pattern.quote(":"));
        return Integer.parseInt(split[1]);
    }
    
    public void modNumInicial(String nombre, int newNum){
        File desc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
        var strError = "";
        var linea = LecturaLinea(desc, strError, 9);
        var split = linea.split(Pattern.quote(":"));
        Modificar(desc, linea, split[0] + ":" + newNum, strError);
    }
    
    public int getNumBloques(String nombre){
        File desc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
        var strError = "";
        var split = LecturaLinea(desc, strError, 10).split(Pattern.quote(":"));
        return Integer.parseInt(split[1]);
    }
    
    public void modNumBloques(String nombre, int newNum){
        File desc = new File("C:/MEIA/desc_ind_" + nombre + ".txt");
        var strError = "";
        var linea = LecturaLinea(desc, strError, 10);
        var split = linea.split(Pattern.quote(":"));
        Modificar(desc, linea, split[0] + ":" + newNum, strError);
    }
    
    void orderIndex(File Archivo){
        var strError = "";
        var list = LecturaCompleta(Archivo, strError);
        try{
            PrintWriter writer = new PrintWriter(Archivo);
            writer.print("");
            writer.close();
        }catch(IOException ex){
            
        }
        
        
        Map<String, Integer> listDesorder = new HashMap<String, Integer>();
        for(int i = 1; i <= list.size(); i++){
            var split = list.get(i - 1).split(Pattern.quote("|"));
            if(split[7].equals("1")){
                listDesorder.put(split[3] + split[4] + split[5], i);
            }
        }
        TreeMap<String, Integer> sorted = new TreeMap<>(); 
        sorted.putAll(listDesorder);
        
        for(int i = 0; i < list.size(); i++){
            var split = list.get(i).split(Pattern.quote("|"));
            var newPos = 0;
            if(sorted.containsKey(split[3] + split[4] + split[5])){
                var cont = 0;
                var cantVigentes = -1;
                for(Map.Entry<String,Integer> entry : sorted.entrySet()){ 
                    if(entry.getKey().equals(split[3] + split[4] + split[5])){
                        cantVigentes = cont + 1;
                    }
                    if(cont == cantVigentes){
                        newPos = entry.getValue();
                        break;
                    }
                    cont++;
                }
            }
            var line = split[0] + "|" + split[1] + "|" + newPos + "|" +
                    split[3] + "|" + split[4] + "|" + split[5] + "|" +
                    split[6] + "|" + split[7];
            
            Escritura(Archivo, line,strError, true);
        }
        
        if(sorted.size() != 0){
            for(Map.Entry<String,Integer> entry : sorted.entrySet()){ 
                modNumInicial("Lista_usuario", entry.getValue());
                break;
            }
        }
        else{
            modNumInicial("Lista_usuario", 0);
        }
    }
    
    public ArrayList<String> lecturaCompleta(String nombre, String llave1, String llave2){
        ArrayList<String> lista = new ArrayList();
        var strError = "";
        var Archivo = new File("C:/MEIA/ind_" + nombre + ".txt");
        var busqueda = getNumInicial(nombre);
        while(busqueda != 0){
            var linea = BuscarLinea(Archivo, String.valueOf(busqueda), strError, 0, 7);
            if(!linea.equals("")){
                var split = linea.split(Pattern.quote("|"));
                if(split[3].equals(llave1) && split[4].equals(llave2)){
                    var numBI = split[1].split(Pattern.quote("."));
                    var bloque = new File("C:/MEIA/" + nombre + numBI[0] + ".txt");
                    lista.add(LecturaLinea(bloque, strError, Integer.parseInt(numBI[1]) - 1));
                }
                busqueda = Integer.parseInt(split[2]);
            }
        }
        if(busqueda == 0){
            var linea = BuscarLinea(Archivo, String.valueOf(busqueda), strError, 0, 7);
            if(!linea.equals("")){
                var split = linea.split(Pattern.quote("|"));
                if(split[3].equals(llave1) && split[4].equals(llave2)){
                    var numBI = split[1].split(Pattern.quote("."));
                    var bloque = new File("C:/MEIA/" + nombre + numBI[0] + ".txt");
                    lista.add(LecturaLinea(bloque, strError, Integer.parseInt(numBI[1]) - 1));
                }
            }
        }
        return lista;
    }
    
    public String buscar(String nombre, String llave1, String llave2, String llave3){
        var strError = "";
        var Archivo = new File("C:/MEIA/ind_" + nombre + ".txt");
        var busqueda = getNumInicial(nombre);
        while(busqueda != 0){
            var linea = BuscarLinea(Archivo, String.valueOf(busqueda), strError, 0, 7);
            if(!linea.equals("")){
                var split = linea.split(Pattern.quote("|"));
                if(split[3].equals(llave1) && split[4].equals(llave2) && split[5].equals(llave3)){
                    var numBI = split[1].split(Pattern.quote("."));
                    var bloque = new File("C:/MEIA/" + nombre + numBI[0] + ".txt");
                    return LecturaLinea(bloque, strError, Integer.parseInt(numBI[1]) - 1);
                }
                busqueda = Integer.parseInt(split[2]);
            }
        }
        if(busqueda == 0){
            var linea = BuscarLinea(Archivo, String.valueOf(busqueda), strError, 0, 7);
            if(!linea.equals("")){
                var split = linea.split(Pattern.quote("|"));
                if(split[3].equals(llave1) && split[4].equals(llave2) && split[5].equals(llave3)){
                    var numBI = split[1].split(Pattern.quote("."));
                    var bloque = new File("C:/MEIA/" + nombre + numBI[0] + ".txt");
                    return LecturaLinea(bloque, strError, Integer.parseInt(numBI[1]) - 1);
                }
            }
        }
        return "";
    }
    
    public void darBaja(String nombre, String llave1, String llave2, String llave3){
        var strError = "";
        var Archivo = new File("C:/MEIA/ind_" + nombre + ".txt");
        var busqueda = getNumInicial(nombre);
        while(busqueda != 0){
            var linea = BuscarLinea(Archivo, String.valueOf(busqueda), strError, 0, 7);
            var split = linea.split(Pattern.quote("|"));
            if(split[3].equals(llave1) && split[4].equals(llave2) && split[5].equals(llave3)){
                var numBI = split[1].split(Pattern.quote("."));
                
                var lineaEscribir = split[0] + "|" + split[1] + "|" +  split[2] + "|" +  split[3] + "|" + split[4] + "|" + 
                split[5] + "|" + split[6] + "|0";
                Modificar(Archivo, linea, lineaEscribir, strError);
                
                var numRegistro = CantidadRegistros(Archivo, strError) + 1;
                if(numRegistro == 1){
                    modifyDescInd(nombre, 7, true, llave2);
                }
                else{
                    modifyDescInd(nombre, 7, false, llave2);
                }
                
                var bloque = new File("C:/MEIA/" + nombre + numBI[0] + ".txt");
                linea = LecturaLinea(bloque, strError, Integer.parseInt(numBI[1]) - 1);
                split = linea.split(Pattern.quote("|"));
                lineaEscribir = split[0] + "|" + split[1] + "|" +  split[2] + "|" +  split[3] + "|" + split[4] + "|" + 
                split[5] + "|0";
                Modificar(bloque, linea, lineaEscribir, strError);
                ModifyFilesDescUser(nombre + numBI[0], 6, llave2, false, strError);
                break;
            }
            busqueda = Integer.parseInt(split[2]);
        }
        orderIndex(Archivo);
    }
    
    public boolean insertar(String nombre, String strContenido, String strError){
        //Validar existencia de registro
        var splitAux = strContenido.split(Pattern.quote("|"));
        if(!buscar(nombre, splitAux[0], splitAux[1], splitAux[2]).equals("")){
            return false;
        }
        var dataUser = Data.getData();
        var user = dataUser.getUser();
        var numBloque = getNumBloques(nombre);
        var Archivo = new File("C:/MEIA/" + nombre + numBloque + ".txt");
        var indice =  CantidadRegistros(Archivo, strError) + 1;

        if(maximoReorganizar2(nombre) == indice - 1){
            numBloque++;
            indice = 1;
            modNumBloques(nombre, numBloque);
            Archivo = new File("C:/MEIA/" + nombre + numBloque + ".txt");
            File pathFileListaUsuarioDesc = new File("C:/MEIA/desc_" + nombre + numBloque + ".txt");
            try{
                Archivo.createNewFile();
                pathFileListaUsuarioDesc.createNewFile();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                var writer = new FileWriter(pathFileListaUsuarioDesc);
                writer.write("nombre simbolico:" + nombre + "\n");
                writer.write("fecha creacion:"+dateFormat.format(date)+"\n");
                writer.write("usuario creacion:root\n");
                writer.write("fecha modificacion:"+dateFormat.format(date)+"\n");
                writer.write("usuario modificacion:root\n");
                writer.write("# registros:0\n");
                writer.write("registros activos:0\n");
                writer.write("registros incactivos:0\n");
                writer.close();
            } catch (IOException ex) {

            }
            Escritura(Archivo, indice + "|" + strContenido, strError, true);
            ModifyFilesDescUser(nombre + numBloque, 6, user, true, strError);

        }
        else{
            Escritura(Archivo, indice + "|" + strContenido, strError, true);
            ModifyFilesDescUser(nombre + numBloque, 6, user, false, strError);
        }

        Archivo = new File("C:/MEIA/ind_" + nombre + ".txt");
        var numRegistro = CantidadRegistros(Archivo, strError) + 1;
        var split = strContenido.split(Pattern.quote("|"));
        var linea = numRegistro + "|" + numBloque + "." + indice + "|0|" + split[0] + "|" + split[1]
                + "|" + split[2] + "|" + split[4] + "|1";
        Escritura(Archivo,  linea, strError, true);
        orderIndex(Archivo);
        if(numRegistro == 1){
            modifyDescInd(nombre, 7, true, user);
        }
        else{
            modifyDescInd(nombre, 7, false, user);
        }
        return true;
    }
    
    void limpiarIndice(String nombre, int lastPos){
        Path destino = Paths.get("C:/MEIA/ind_" + nombre + "_temp.txt");
        Path origen = Paths.get("C:/MEIA/ind_" + nombre + ".txt");
        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {}
        File inputFile = new File("C:/MEIA/ind_" + nombre + "_temp.txt");
        File outputFile = new File("C:/MEIA/ind_" + nombre + ".txt");
        
        var max = maximoReorganizar2(nombre);
        var bloques = 0;
        var bloqueRegistro = 1;
        var registro = 1;
        var strError = "";
        try
        {
            PrintWriter writer = new PrintWriter(outputFile);
            writer.print("");
            writer.close();
        }catch(FileNotFoundException ex){
            strError = ex.getMessage();
        }
        try{
            FileReader fReader = new FileReader(inputFile); 
            BufferedReader br = new BufferedReader(fReader);
            try
            {
                var Linea = br.readLine();
                while(Linea != null)
                {
                    if(!"".equals(Linea))
                    {
                        var split = Linea.split(Pattern.quote("|"));
                        if(split[lastPos].equals("1")){
                            var newLine = registro + "|" + bloques + "." + bloqueRegistro + "|0|" + split[3] + "|" 
                                    + split[4] + "|" + split[5] + "|" + split[6] + "|" + split[7];
                            Escritura(outputFile, newLine, strError, true);
                            registro++;
                            if(bloqueRegistro == max){
                                bloqueRegistro = 1;
                                bloques++;
                            }
                            else{
                                bloqueRegistro++;
                            }
                        }
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
            } catch (IOException ex) {
            }
        }catch(FileNotFoundException ex){
        }
        inputFile.delete();
        orderIndex(outputFile);
        modifyDescInd(nombre, lastPos, false, "root");
    }
    
    void limpiarBloque(String nombre, int lastPos){
        var dataUser = Data.getData();
        dataUser.setUser("root");
        var strError = "";
        var max = maximoReorganizar2(nombre);
        var numTemp = getNumBloques(nombre);
        for(int i = 0; i <= numTemp; i++){
            Path destino = Paths.get("C:/MEIA/" + nombre + i + "_temp.txt");
            Path origen = Paths.get("C:/MEIA/" + nombre + i + ".txt");
            try {
                Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {}
            var archivo = new File("C:/MEIA/" + nombre + i + ".txt");
            try
            {
                PrintWriter writer = new PrintWriter(archivo);
                writer.print("");
                writer.close();
            }catch(FileNotFoundException ex){
                strError = ex.getMessage();
            }
        }
        
        var totalRegistros = CantidadRegistros(new File("C:/MEIA/ind_" + nombre + ".txt"), strError);
        try
        {
            PrintWriter writer = new PrintWriter(new File("C:/MEIA/ind_" + nombre + ".txt"));
            writer.print("");
            writer.close();
        }catch(FileNotFoundException ex){
            strError = ex.getMessage();
        }
        
        var indice = 0;
        var contBloque = 0;
        var contBloqueVigente = 0;
        var cantVigente = 0;
        modNumBloques(nombre, 0);
        modNumInicial(nombre, 0);
        for(int i = 0; i < totalRegistros; i++){
            if(indice == max){
                indice = 0;
                contBloque++;
            }
            if(cantVigente == max){
                cantVigente = 0;
                contBloqueVigente++;
            }
            var origen = new File("C:/MEIA/" + nombre + contBloque + "_temp.txt");
            var linea = LecturaLinea(origen, strError, indice);
            var split = linea.split(Pattern.quote("|"));
            if(split[lastPos].equals("1")){
                var newLine = split[1] + "|" + split[2] + "|" + split[3] + "|" + split[4] + "|" + split[5] + "|" + split[6];
                insertar(nombre, newLine, strError);
                cantVigente++;
            }
            indice++;
        }
        for(int i = 0; i <= numTemp; i++){
            var origen = new File("C:/MEIA/" + nombre + i + "_temp.txt");
            origen.delete();
        }
        for(int i = contBloqueVigente + 1; i <= numTemp; i++){
            var origen = new File("C:/MEIA/" + nombre + i + ".txt");
            origen.delete();
            origen = new File("C:/MEIA/desc_" + nombre + i + ".txt");
            origen.delete();
        }
    }
    
    public void limpiarBI(String nombre){
        limpiarBloque("Lista_usuario", 6);
    }
}