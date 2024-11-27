package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Dispositivi implements EntityInterface {
    
    private Integer codice;

    public Dispositivi(int codice){
        this.codice=codice;
    }
    public Dispositivi(){
        this.codice=0;
    }
    
    @Override
    public String toString(){
        return "[codice="+codice+"]";
    }

    
    public void setAttributes(int codice) {
       this.codice=codice;       
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery)throws SQLException {  
        codice=risQuery.getInt("codice");
        return this;
    }
    
    @Override
    public String insert(){
        System.out.println("inserire codice: ");
        codice=Utente.inputInt();
        String query="Insert into dispositivi values ("+codice+")";
        return query;
    }
   
    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        return "update dispositivi set "+attrModificato+"="+newValue+" where "+conditions;
    }
}
