package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Carte  implements EntityInterface{
    private Integer codiceCarta;
    private Integer CVV;
    private String scadenza;
    private String proprietarioCarta;
    
    public Carte() {
        codiceCarta=null;
        CVV=null;
        scadenza=null;
        proprietarioCarta=null;
    }

    public Carte(Integer codice, Integer cVV, String scadenza,String proprietarioCarta) {
        this.codiceCarta = codice;
        CVV = cVV;
        this.scadenza = scadenza;
        this.proprietarioCarta=proprietarioCarta;
    }

    @Override
    public String toString() {
        String ris="[ ";
        if(codiceCarta!=null)ris+="codice="+codiceCarta+" ";
        if(CVV!=null)ris+="CVV="+CVV+" ";
        if(scadenza!=null)ris+="scadenza="+scadenza+" ";
        if(proprietarioCarta!=null)ris+="proprietario carta="+proprietarioCarta+" ";

        ris+="]";
        return ris;
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery) throws SQLException {
        if(attributi==null){
            codiceCarta=risQuery.getInt("codiceCarta");
            CVV=risQuery.getInt("CVV");
            scadenza=risQuery.getString("scadenza");
            proprietarioCarta=risQuery.getString("proprietarioCarta");
        }
        else{
            String []attributilist=attributi.split(" ");
            for(int i=0;i<attributilist.length;i++){
                if(attributilist[i].equals("codiceCarta")) codiceCarta=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("CVV")) CVV=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("proprietarioCarta")) proprietarioCarta=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("scadenza"))scadenza=risQuery.getString(attributilist[i]);
            }
        }

        return this;
    }

    @Override
    public String insert() {
        boolean errore=false;
        while(errore)
        errore=true;
        
        System.out.println("inserire codice carta");
        codiceCarta=Utente.inputInt();
                
        System.out.println("inserire scadenza");
        scadenza=Utente.input();

        System.out.println("inserire CVV");
        CVV=Utente.inputInt();

        return codiceCarta+","+scadenza+","+CVV;
    }

    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        String query="update carte set "+attrModificato+"= ";
        
        if(attrModificato.equals("codiceCarta")||attrModificato.equals("CVV"))query+=newValue;
        else query+="'"+newValue+"'";

        return query+" where "+conditions;
    }

    
    
}
