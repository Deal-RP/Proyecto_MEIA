import java.io.File;
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
        var strError = "";
        var actual = objManejoArchivo.BuscarLinea(Archivo, user, strError, 0, 9);
        if(!actual.equals("")){
            return "Usuario ya existe";
        }
        else{
            try
            {
                pass = encrypt(pass, 2, 8);
                foto = copyImage(foto, user);
                var nuevoUsuario = user + "|" + nombre + "|" + apellido + "|" + pass + "|" + rol + "|" + fecha + "|" + correoAlt + "|" + telefono + "|" + foto + "|" + status;
                objManejoArchivo.Escritura(Archivo, nuevoUsuario, strError, true);
            }catch(Exception ex){
                return ex.getMessage();
            }
        }
        return "Se ha agregado existosamente el usuario";
    }
    void ModificarUsuario()
    {
    
    }
}
