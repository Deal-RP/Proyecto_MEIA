/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

 

/**
 *
 * @author Santiago Bocel
 */
public class ABB {

   private int counter = 1;

   public Node raiz = null;
    
   private List listDataTree;
   
   private List List_msg;
   
   private List List_Search;
public void Cargar() throws FileNotFoundException, IOException
{
    counter = 1;
    raiz = null;
    try
    {
   String Archivo  = "C:/MEIA/tree.txt";
   FileReader fReader = new FileReader(Archivo);
    BufferedReader br = new BufferedReader(fReader);
     var Linea = br.readLine();
                while(Linea != null)
                {
                    if(!"".equals(Linea))
                    {
                        var datos = Linea.split(Pattern.quote("|"));
                        String llave = datos[3] + datos[4] + datos[5];
                        var Linea_datos = datos[3] +"|" + datos[4] + "|" + datos[5] + "|" + datos[6] + "|" 
                                + datos[7] + "|" + datos[8]+ "|" + datos[9];
                         Insertar(llave,Linea_datos);       
                    }
                    Linea = br.readLine();
                }
                br.close();
                fReader.close();
    } catch (IOException ex) {}           
} 
public void Insertar(String llave, String data){  
    
    var newNode = new Node(counter, llave, data);

    if (raiz == null) {
        raiz = newNode;
        counter++;
        
    }
    else {
       raiz =  InsertNode(raiz, newNode);
       counter++;
    }
  }

private Node InsertNode(Node currentNode, Node newNode){

    if (newNode.llave.compareTo(currentNode.llave) > 0) {
             if (currentNode.Der == null) {             
                 currentNode.Der = newNode;                  
             return currentNode;
        } else {
            currentNode.Der = InsertNode(currentNode.Der, newNode);
            return currentNode;
        }
      }
     if (newNode.llave.compareTo(currentNode.llave) < 0) {
        if (currentNode.Izq == null) {                    
            currentNode.Izq = newNode;
            return currentNode;
        } else {
            currentNode.Izq = InsertNode(currentNode.Izq, newNode);
            return currentNode;
        }
     }   
   return null;
}

 private void inOrder(Node root) {
    var izqNo = "";
    var derNo = "";   
     if (root != null) {
            inOrder(root.Izq);
            if (root.Izq == null) {
                izqNo = "-1";
            }else{
                izqNo =   Integer.toString(root.Izq.No_registro);  
            }
            if (root.Der == null) {
                derNo = "-1";
            }else{
                derNo =  Integer.toString(root.Der.No_registro); 
            }
            var raizNo =  Integer.toString(root.No_registro);   
            var register =  raizNo + "|" + derNo + "|" + izqNo  + "|" + root.datos;

            listDataTree.add(register);
            inOrder(root.Der);    
        }
    }
 
 public void posOrden(Node root){
       var izqNo = "";
       var derNo = "";
     if (root != null) {
          posOrden(root.Der);
          if (root.Der == null) {
                derNo = "-1";
            }else{
                derNo =  Integer.toString(root.Der.No_registro); 
            }
            if (root.Izq == null) 
            {
               izqNo = "-1";
            }else{
               izqNo =  Integer.toString(root.Izq.No_registro);  
            }          
            var raizNo =  Integer.toString(root.No_registro);   
            var register =  raizNo + "|" + derNo + "|" + izqNo  + "|" + root.datos;          
            listDataTree.add(register);
           posOrden(root.Izq);     
        }
 
 }
 public void PreOrden(Node root){
     var izqNo = "";
       var derNo = "";
     if (root != null) {
         if (root.Der == null) {
              derNo = "-1";
         }
         else{
             derNo = Integer.toString(root.Der.No_registro); 
         }
           if (root.Izq == null) 
            {
               izqNo = "-1";
            }else{
               izqNo =  Integer.toString(root.Izq.No_registro);  
            }          
          var raizNo =  Integer.toString(root.No_registro);   
          var register =  raizNo + "|" + izqNo + "|" +  derNo + "|" + root.datos;         
            listDataTree.add(register);
            PreOrden(root.Izq);  
            PreOrden(root.Der);    
                                     
        }
 }

public void WriteTree(String pathFileTree) throws IOException{
    listDataTree = new ArrayList(); 
    PreOrden(raiz);
    var manejoArchivos = new ManejoArchivo();
    var strError = "";   
    for (int i = 0; i < listDataTree.size(); i++) {
        var Actual_linea = (String) listDataTree.get(i);
        var Actual = Actual_linea.split(Pattern.quote("|"));
        var Archivo_txt = manejoArchivos.LecturaLinea(new File(pathFileTree), strError, Integer.parseInt(Actual[0]) -1);
        var Array_Archivo_txt = Archivo_txt.split(Pattern.quote("|"));
         if (Archivo_txt.equals("")) {
            manejoArchivos.Escritura(new File(pathFileTree), Actual_linea, strError, true);
        }
         else
         {
         var Modificar = Actual[0] + "|" + Actual[1]+ "|" + Actual[2] + "|"+Array_Archivo_txt[3]+ "|"+Array_Archivo_txt[4]+ "|"+Array_Archivo_txt[5]
              + "|"+Array_Archivo_txt[6] + "|"+Array_Archivo_txt[7]+ "|"+Array_Archivo_txt[8]+ "|" + Array_Archivo_txt[9];
      manejoArchivos.Modificar(new File(pathFileTree),Archivo_txt, Modificar , strError);
         }
    }
    listDataTree.clear();
}


 private String Buscar_(String usuario, Node root, String llave_){
     String dato_busqueda = "";
     if (root != null) 
     {
        if ( usuario.compareTo(llave_) == 0) {
             // devolver string con la informacion del dato
             return root.datos;
            }
        else {
                if (usuario.compareTo(llave_) < 0) {
                   dato_busqueda = Buscar_(usuario,root.Izq, llave_);
            
                }
                else {
                   dato_busqueda = Buscar_(usuario,root.Der,llave_);
                }
           }
     }
      return dato_busqueda;
 }    
 // Este metodo es el que llaman para llenar la tabla 
 //parametro: user -> usuario que esta logiado en este momento
 //parametro: posicion -> es la posicion de la persona que se desea buscar, posicion 3 emisor y posicion 4 emisor
 public List display_msg_(String user,int position)
 {
   List_msg = new ArrayList();
   display_msg(user ,raiz,position);
   return List_msg;
 }
 public List search_user_(String Referrer, String Name,int position1, int position2 )
 {
  List_Search = new ArrayList();
  search_user(Referrer, Name,raiz,position1, position2);
  return List_Search;
 }

 //Mostrar mensajes del usuario dependiendo de E/S
 private void display_msg(String user, Node root, int position)
 {
   if (root != null) {
    var arreglo_llave = root.datos.split(Pattern.quote("|"));
    var llave_ = arreglo_llave[position];                   
             var dato = Buscar_(user, root, llave_); 
             if (!dato.equals("")) {
                  List_msg.add(dato); 
                
             }
       
             display_msg(user,root.Izq,position);  
             display_msg(user,root.Der,position); 
        }
 }
 //Buscar mensaje del usuario unicamente Salida
 private void search_user(String Referrer, String Name,Node root ,int position1, int position2 )
 {
  if (root != null) {
    var arreglo_llave = root.datos.split(Pattern.quote("|"));
    var llave_ = arreglo_llave[position1];   
    var llave_R = arreglo_llave[position2];
             var dato = Buscar_referencia(Referrer,Name, root, llave_, llave_R );  
             if (dato != "") {
              List_Search.add(dato); 
              }             
             search_user(Referrer, Name,root.Izq,position1, position2);  
             search_user(Referrer, Name,root.Der,position1, position2); 
        }
     
 }
 private String Buscar_referencia(String Referrer, String Name,Node root, String llave_, String llave_R)
 {
  String dato_busqueda = "";
     if (root != null) 
     {
        if ( Name.compareTo(llave_) == 0 && Referrer.compareTo(llave_R) == 0) {
             // devolver string con la informacion del dato
             return root.datos;
            }
        else {
                if (Name.compareTo(llave_) < 0 ) {
                   dato_busqueda = Buscar_referencia(Referrer,Name,root.Izq, llave_,llave_R);
            
                }
                else {
                   dato_busqueda = Buscar_referencia(Referrer, Name,root.Der,llave_,llave_R);
                }
           }
     }
      return dato_busqueda;
 }
}
    
