package Management;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class Data
{
    
      private String user;
        
      private String tempUser;
      
      private String role;
        
      private String tempRole;
  
      private static  Data miUser = null;
      
      

      public  static Data getData()
      {
        if (miUser==null) 
        {
          miUser=new Data();
        }
          return miUser;
      }

     public Data()
     {
         this.user = tempUser;
         this.role= tempRole;
     }

     public String getUser()
     {
          return tempUser;
     }

     public void setUser(String user)
     {
           tempUser = user;
     }
     
      public String getRole()
     {
          return tempRole;
     }

     public void setRole(String role)
     {
           tempRole = role;
     }
     
 
    
    
}
