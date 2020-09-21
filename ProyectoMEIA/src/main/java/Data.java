/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class Data {
    
        private String url;
        
        private String temp;
  
      private static  Data miconfigurador = null;
      
      

      public  static Data getData() {

      if (miconfigurador==null) {

      miconfigurador=new Data();
      }
      return miconfigurador;
      }

     public Data(){
         this.url = temp;
     }

     public String getUrl() {
      return temp;
      }

     public void setUrl(String url) {
      temp = url;
      }
 
    
    
}
