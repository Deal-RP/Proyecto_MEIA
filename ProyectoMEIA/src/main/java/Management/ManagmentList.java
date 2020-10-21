/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.File;
import java.util.regex.Pattern;


/**
 *
 * @author pablo
 */
public class ManagmentList {
    
    public void ModifyQuantityFriends(String nameList, int option){
        
        var fileList = new File("C:/MEIA/lista.txt");
        var fileBinnacleList = new File("C:/MEIA/bitacora_lista.txt");
        var whereIs = false;

        var objManejoArchivo = new ManejoArchivo();
        var register = objManejoArchivo.BuscarLinea(fileList, nameList, "", 0, 5);

        if("".equals(register)){
            register = objManejoArchivo.BuscarLinea(fileBinnacleList, nameList, "", 0, 5);
            whereIs = true;
        }
        
        var splitRegister = register.split(Pattern.quote("|"));

        var quantityFriends = Integer.parseInt((splitRegister[3].trim()));
        if (option == 1){
            splitRegister[3]= Integer.toString(quantityFriends + 1 );
            
        }else{
            splitRegister[3]= Integer.toString(quantityFriends - 1);

        }
        var  newRegister = String.join("|", splitRegister);
        var objManagmentFile = new ManejoArchivo();
        var statusModifyFile = false;

        if(whereIs == false){
            statusModifyFile = objManagmentFile.Modificar(fileList, register, newRegister, "");

        }else{
            statusModifyFile =  objManagmentFile.Modificar(fileBinnacleList, register, newRegister, "");
        }


    }

}
