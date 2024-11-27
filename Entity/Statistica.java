package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Statistica implements EntityInterface {
    private String risultato;
    
    public Statistica(){
        risultato=null;
    }

    public Statistica(String risultato){
        this.risultato=risultato;
    }   

    @Override
    public String toString() {
        return "[" + risultato + "]";
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery) throws SQLException {
        risultato="";
        while(risQuery.next()){
            if(attributi.equals("tragitti_in_corso"))risultato+="ci sono "+risQuery.getString(1)+" tragitti in corso";
            else if(attributi.equals("fattura")){
                risultato+="dispositivo= "+risQuery.getString(1)+"->spesa="+risQuery.getFloat(2)+"0 euro\n";
            }
            else if (attributi.equals("orario_piu_trafficato")){
                String ora=risQuery.getString(1);
                risultato+="l'orario più trafficato va dalle "+ora+" alle ";
                int limite=1+Integer.valueOf(ora);
                if(limite==24)limite=0;

                risultato+=String.valueOf(limite);
            }
        }
        
        return this;
    }

    @Override
    public String insert() {
        System.err.println("operazione non consentita");
        return null;
    }

    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        System.err.println("operazione non consentita");
        return null;
    }
    
}
