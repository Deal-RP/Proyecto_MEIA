/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
   
   private List listissuer;

public void Cargar() throws FileNotFoundException, IOException
{
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
                        String data = Linea;
                         Insertar(llave,data);       
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
           var register =  raizNo + "|" + derNo + "|" + izqNo  + "|" + root.datos;          
            listDataTree.add(register);
         PreOrden(root.Izq);   
         PreOrden(root.Der);          
        }
 }

public void WriteTree(String pathFileTree) throws IOException{
    listDataTree = new ArrayList(); 
    PreOrden(raiz);
    try {
    FileWriter fwriter = new FileWriter(pathFileTree, true);
    BufferedWriter bw = new BufferedWriter(fwriter);
    PrintWriter writer = new PrintWriter(pathFileTree);
    writer.print("");
    writer.close();

    for (int i = 0; i < listDataTree.size(); i++) {
        bw.write((String) listDataTree.get(i));
        bw.write("\n");
    }
     bw.close();
     fwriter.close();
    listDataTree.clear();
    }
    catch (IOException ex) {
            //TODO: handle exception
          
        }
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
 public String Buscar_Emisor()
 {
   return null;
 }
 //Buscar todos los mensajes recibidos
 public String Buscar_Receptor()
 {
     return null;    
 }
 // Buscar todos los mensajes enviados por el emisor
 private void search_issuer(String issuer, Node root)
 {
    var arreglo_llave = root.llave.split(Pattern.quote("|"));
    var llave_ = arreglo_llave[0];
    var dato = "";
   if (root != null) {
            inOrder(root.Izq);
            if (root.Izq != null) {
               dato = Buscar_(issuer, root.Izq, llave_);
              listissuer.add(dato);                
            }
            if (root.Der != null) {
               dato = Buscar_(issuer, root.Der, llave_);
              listissuer.add(dato);            
            }
              listissuer.add(dato);
            inOrder(root.Der);    
        }
}
 
 private void search_receiver(String issuer, Node root)
 {
    var arreglo_llave = root.llave.split(Pattern.quote("|"));
    var llave_ = arreglo_llave[1];
    var dato = "";
   if (root != null) {
            inOrder(root.Izq);
            if (root.Izq != null) {
               dato = Buscar_(issuer, root.Izq, llave_);
              listissuer.add(dato);                
            }
            if (root.Der != null) {
               dato = Buscar_(issuer, root.Der, llave_);
              listissuer.add(dato);            
            }
              listissuer.add(dato);
            inOrder(root.Der);    
        }
}
 
 
}
    
