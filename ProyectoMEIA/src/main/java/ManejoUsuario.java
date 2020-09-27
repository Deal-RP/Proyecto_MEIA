import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.regex.Pattern;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ManejoUsuario {
    String cifrado (String input, int key) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String ABC = abc.toUpperCase();
        StringBuilder in = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++){
            char letter = input.charAt(i);
            int index = abc.indexOf(letter);
            if(index != -1){
                index = (index + key) % 26;
                in.setCharAt(i, abc.charAt(index));
            }
            else{
                index = ABC.indexOf(letter);
                if(index != -1){
                    index = (index + key) % 26;
                    in.setCharAt(i, ABC.charAt(index));
                }
            }
        }
        return in.toString();
    }
    String decrypt(String encryted) {
        String half1 = halfOfString(encryted, 0);
        String half2 = halfOfString(encryted, 1);
        return encrypt(encryted, 26 - 2, 26 - 8);
    }
    String halfOfString(String message, int start){
        String in = "";
        for(int i =0; i < message.length(); i++){
            if(i % 2 == start){
                in += message.charAt(i);
            }
        }
        return in;
    }
    String encrypt(String input, int key1, int key2){
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String ABC = abc.toUpperCase();
        StringBuilder in = new StringBuilder(input);
        int key;
        for(int i = 0; i < input.length(); i++){
            char letter = input.charAt(i);
            int index = abc.indexOf(letter);
            if(i % 2 == 0){
                key = key1;
            }
            else{
                key = key2;
            }
            if(index != -1){
                index = (index + key) % 26;
                in.setCharAt(i, abc.charAt(index));
            }
            else{
                index = ABC.indexOf(letter);
                if(index != -1){
                    index = (index + key) % 26;
                    in.setCharAt(i, ABC.charAt(index));
                }
                else{
                    in.setCharAt(i, letter);
                }
            }
        }
        return in.toString();
    }
    String copyImage(String strOrigen, String user){
        try{
            FileSystem sysArchivo = FileSystems.getDefault();
            Path ruta = sysArchivo.getPath(strOrigen);
            var nombre = ruta.getFileName().toString();
            var split = nombre.split(Pattern.quote("."));
            Path destino = Paths.get("C:/MEIA/fotografia/" + user + "." + split[1]);
            Path origen = Paths.get(strOrigen);
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            return "C:/MEIA/fotografia/" + user + "." + split[1];
        } catch(IOException e){
            return "No se ha podido guardar la foto";
        }
    }
    public String crearUsuario(String user, String nombre, String apellido, String pass, int rol, Date fecha, String correoAlt, String telefono, String foto, int status){
        var objManejoArchivo = new ManejoArchivo();
        File Archivo = new File("C:/MEIA/usuario.txt");
        File Bita = new File("C:/MEIA/bitacora_usuario.txt");
        var strError = "";
        var ArchivoUser = objManejoArchivo.BuscarLinea(Archivo, user, strError, 0, 9);
        var ArchivoBita = objManejoArchivo.BuscarLinea(Bita, user, strError, 0, 9);
        if(!ArchivoUser.equals("") || !ArchivoBita.equals("")){
            return "Usuario ya existe";
        }
        else{
            try
            {
                pass = encrypt(pass, 2, 8);
                foto = copyImage(foto, user);
                var nuevoUsuario = user + "|" + nombre + "|" + apellido + "|" + pass + "|" + rol + "|" + fecha + "|" + correoAlt + "|" + telefono + "|" + foto + "|" + status;
                if(objManejoArchivo.CantidadRegistros(Bita, strError) >= objManejoArchivo.maximoReorganizar()){
                    if(objManejoArchivo.CantidadRegistros(Archivo, strError) == 0){
                        objManejoArchivo.LimpiarBitacora();
                        objManejoArchivo.ModifyFilesDescUser(user, true, strError);
                    }
                    else{
                        objManejoArchivo.LimpiarBitacora();
                        objManejoArchivo.ModifyFilesDescUser(user, false, strError);
                    }
                }
                if(objManejoArchivo.CantidadRegistros(Bita, strError) == 0){
                    objManejoArchivo.orderInsert(Bita, nuevoUsuario, strError);
                    objManejoArchivo.ModifyFilesDescBita(user, true, strError);
                }
                else{
                    objManejoArchivo.orderInsert(Bita, nuevoUsuario, strError);
                    objManejoArchivo.ModifyFilesDescBita(user, false, strError);
                }
            }catch(Exception ex){
                return ex.getMessage();
            }
        }
        return "Se ha agregado existosamente el usuario";
    }
    boolean ModificarUsuario(String user, String nombre, String apellido, String pass,int rol, String correoAlt, String telefono, String foto)
    {
      // volver a sobreescribir los datos
        var objManejoArchivo = new ManejoArchivo();
        File Archivo = new File("C:/MEIA/usuario.txt");
        File Bita = new File("C:/MEIA/bitacora_usuario.txt");
        var strError = "";
        //strContenidoBusqueda
        var ArchivoUser = objManejoArchivo.BuscarLinea(Archivo, user, strError, 0, 9);
        var ArchivoBita = objManejoArchivo.BuscarLinea(Bita, user, strError, 0, 9);
        if(!ArchivoUser.equals("") ){
         //  Usuario ya existe y se sobreescribe                        
         try
         {
          var split = ArchivoUser.split(Pattern.quote("|"));
//             if (pass.equals("")) 
//             {
//              String password = split[3];
//              pass = password;  
//             }
              var strContenido = user + "|" + nombre + "|" + apellido + "|" + pass + "|" + rol + "|" + split[5] + "|" + correoAlt + "|" + telefono + "|" + foto + "|" + split[9];          
            boolean mensaje = objManejoArchivo.Modificar(Archivo, ArchivoUser, strContenido, strError);
            return mensaje;
         }
         catch(Exception ex){return false;}
         
        }
        else if(!ArchivoBita.equals(""))
        {
         try
         {
          var split = ArchivoBita.split(Pattern.quote("|"));
//             if (pass.equals("")) 
//             {
//              String password = split[3];
//              pass = password;  
//             }
              var strContenido = user + "|" + nombre + "|" + apellido + "|" + pass + "|" + rol + "|" + split[5] + "|" + correoAlt + "|" + telefono + "|" + foto + "|" + split[9];          
         boolean mensaje = objManejoArchivo.Modificar(Bita, ArchivoBita, strContenido, strError);
         return mensaje;
         }
         catch(Exception ex){  return false;}
        }
        return true;
    } 
    
    public String[] ReadFile(){
        
          var arrayData = new String[9];
         try {
            
            var descBitacoraUsuario = new File("C:/MEIA/des_bitacora_usuario");

            
            var counter = 0;

            BufferedReader br = new BufferedReader(new FileReader(descBitacoraUsuario)); 

            String st; 
            while ((st = br.readLine()) != null){
                arrayData[counter] = st;
                counter ++;
            }

            br.close();
            return arrayData;

        } catch (Exception e) {
            //TODO: handle exception
            return arrayData;
        }
    }
}

