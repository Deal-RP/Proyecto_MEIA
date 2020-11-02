/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

/**
 *
 * @author Santiago Bocel
 */
public class ABB {
     private class nodoArbol {
        private ABB Izq;
        private ABB Der;
        private String dato;
 
        private void nodoArbol(){
            Izq = null;
            Der = null;
            dato = " ";
        }
    }
 public nodoArbol raiz;
 
  public void abb(){
        nodoArbol raiz = new nodoArbol();
    }
  
  public boolean esVacio(){
        return (raiz == null);
    }
  
public void Insertar(String dato)
 {
        if (esVacio()) {
            nodoArbol nuevo = new nodoArbol();
            nuevo.dato = dato;
            nuevo.Der = new ABB();
            nuevo.Izq  = new ABB();
            raiz = nuevo;
        }
        else {
            if (dato.compareTo(raiz.dato) > 0) {
                (raiz.Izq).Insertar(dato);
            }
            if (dato.compareTo(raiz.dato) < 0){
                (raiz.Der).Insertar(dato);
            }
        }  
  }
 public ABB buscar(String dato){
        ABB arbolito = null;
        if (!esVacio()) {
            if (dato.compareTo(raiz.dato) == 0) {
            return this;
            }
            else {
                if (dato.compareTo(raiz.dato) < 0) {
                    arbolito = raiz.Izq.buscar(dato);
                }
                else {
                    arbolito = raiz.Der.buscar(dato);
                }
            }
        }
        return arbolito;
    }
   
 public boolean esHoja() {
        boolean hoja = false;
        if( (raiz.Izq).esVacio() && (raiz.Der).esVacio() ) {
            hoja = true;
        }
        return hoja;
    }
 
 public String buscarMin() {
        ABB arbolActual = this;
        while( !arbolActual.raiz.Izq.esVacio() ) {
            arbolActual = arbolActual.raiz.Izq;
        }
        String devuelvo= arbolActual.raiz.dato;
        arbolActual.raiz=null;
        return devuelvo;
    }
 
   public void eliminar(String dato) {
        ABB paraEliminar = buscar(dato);
        if (!paraEliminar.esVacio()) {
            if (paraEliminar.esHoja()) {
                paraEliminar.raiz = null;
            }
            else {
                if (!paraEliminar.raiz.Izq.esVacio() && !paraEliminar.raiz.Der.esVacio()) {
                    paraEliminar.raiz.dato = paraEliminar.raiz.Der.buscarMin();
                }
                else {
                    if (paraEliminar.raiz.Izq.esVacio()) {
                        paraEliminar.raiz = paraEliminar.raiz.Der.raiz;
                    }else{
                        paraEliminar.raiz = paraEliminar.raiz.Izq.raiz;
                    }
                }
            }
        }
    }
    
}
