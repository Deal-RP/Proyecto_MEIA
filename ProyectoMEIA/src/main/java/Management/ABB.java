/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

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

   private int counter = 0;

   public Node raiz = null;
    
   private List listDataTree;

 
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

    if (currentNode.llave.compareTo(newNode.llave) < 0) {
        if (currentNode.Izq == null) {
            currentNode.Izq = newNode;
            return currentNode;
        } else {
            currentNode.Izq = InsertNode(currentNode.Izq, newNode);
            return currentNode;
        }
    }else if (currentNode.llave.compareTo(newNode.llave) > 0) {
        if (currentNode.Der == null) {
            currentNode.Der = newNode;
            return currentNode;
        } else {
            currentNode.Der = InsertNode(currentNode.Der, newNode);
            return currentNode;
        }
    } else {         
        return null;
    }
}

 private void inOrder(Node root) {
        if (root != null) {
            inOrder(raiz.Izq);
            var izqNo = "";
            var derNo = "";
            if (raiz.Izq == null) {
                izqNo = "-1";
            }else{
                izqNo =   Integer.toString(raiz.Der.No_registro);  
            }
            if (raiz.Izq == null) {
                derNo = "-1";
            }else{
                derNo =  Integer.toString(raiz.Izq.No_registro); 
            }
            var raizNo =  Integer.toString(raiz.No_registro);   
            var register =  raizNo + "|" + derNo + "|" + izqNo  + "|" + raiz.datos;

            listDataTree.add(register);
            inOrder(raiz.Der);    
        }
    }

public void WriteTree(String pathFileTree) throws IOException{
    listDataTree = new ArrayList(); 
    PrintWriter writer = new PrintWriter(pathFileTree);
    writer.print("");
    writer.close();
    inOrder(raiz);
    var writerFileTree = new FileWriter(pathFileTree, true);
    for (int i = 0; i < listDataTree.size(); i++) {
        writer.write((String) listDataTree.get(i));
    }
    writerFileTree.close();
    listDataTree.clear();
}
 

// public ABB buscar(String dato){
//        ABB arbolito = null;
//        if (!esVacio()) {
//            if (dato.compareTo(raiz.llave) == 0) {
//            return this;
//            }
//            else {
//                if (dato.compareTo(raiz.llave) < 0) {
//                    arbolito = raiz.Izq.buscar(dato);
//                }
//                else {
//                    arbolito = raiz.Der.buscar(dato);
//                }
//            }
//        }
//        return arbolito;
//    }
   
// public boolean esHoja() {
//        boolean hoja = false;
//        if( raiz.Izq.IsLeaf() && raiz.Der) {
//            hoja = true;
//        }
//        return hoja;
//    }
// 
// public String buscarMin() {
//        ABB arbolActual = this;
//        while( !arbolActual.raiz.Izq.esVacio() ) {
//            arbolActual = arbolActual.raiz.Izq;
//        }
//        String devuelvo= arbolActual.raiz.llave;
//        arbolActual.raiz=null;
//        return devuelvo;
//    }
 
//   public void eliminar(String dato) {
//        ABB paraEliminar = buscar(dato);
//        if (!paraEliminar.esVacio()) {
//            if (paraEliminar.esHoja()) {
//                paraEliminar.raiz = null;
//            }
//            else {
//                if (!paraEliminar.raiz.Izq.esVacio() && !paraEliminar.raiz.Der.esVacio()) {
//                    paraEliminar.raiz.llave = paraEliminar.raiz.Der.buscarMin();
//                }
//                else {
//                    if (paraEliminar.raiz.Izq.esVacio()) {
//                        paraEliminar.raiz = paraEliminar.raiz.Der.raiz;
//                    }else{
//                        paraEliminar.raiz = paraEliminar.raiz.Izq.raiz;
//                    }
//                }
//            }
//        }
//    }
   
     
}
