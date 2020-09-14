import java.io.*;
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
    //Existe archivo, sino lo crea
    public boolean ExisteCrear(File Archivo, String strError){
        if (!Archivo.exists()){
            try
            {
                Archivo.createNewFile();
            } catch (IOException ex){
                strError = ex.getMessage();
                return false;
            }
        }
        return true;
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
}
