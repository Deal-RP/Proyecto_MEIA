/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

/**
 *
 * @author pablo
 */
public class Node {
    public Node Izq;
    public Node Der;
    public String datos;
    public String llave;
    public int No_registro; 
 
    public Node(int numberRegister, String key , String data){
        Izq = null;
        Der = null;
        llave = key;
        No_registro = numberRegister;
        datos = data;
    }
    
    public boolean IsLeaf(){
        if (Der == null && Izq == null) {
            return true;
        }else{
            return false;
        }
    }
}
