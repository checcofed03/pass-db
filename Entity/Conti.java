package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Conti implements EntityInterface {
    private Integer codiceConto;
    private String iban;
    private String proprietarioConto;
    
    public Conti() {
        codiceConto=null;
        iban=null;
        proprietarioConto=null;
    }

    public Conti(Integer codiceConto, String iban, String proprietarioConto) {
        this.codiceConto = codiceConto;
        this.iban = iban;
        this.proprietarioConto = proprietarioConto;
    }

    @Override
    public String toString() {
        String ris="[ ";
        if(codiceConto!=null)ris+="codiceConto="+codiceConto+" ";
        if(iban!=null)ris+="IBAN="+iban+" ";
        if(proprietarioConto!=null)ris+="proprietario conto="+proprietarioConto+" ";

        ris+="]";
        return ris;
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery) throws SQLException {
        if(attributi==null){
            codiceConto=risQuery.getInt("codiceConto");
            iban=risQuery.getString("IBAN");
            proprietarioConto=risQuery.getString("proprietarioConto");
        }
        else{
            String []attributilist=attributi.split(" ");
            for(int i=0;i<attributilist.length;i++){
                if(attributilist[i].equals("codiceConto")) codiceConto=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("proprietarioConto")) proprietarioConto=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("IBAN"))iban=risQuery.getString(attributilist[i]);
            }
        }

        return this;
    }

    @Override
    public String insert() {
        boolean errore=true;
        while(errore)
        errore=false;
        System.out.println("inserire codice conto");
        codiceConto=Utente.inputInt();
        
        System.out.println("inserire iban");
        iban=Utente.input();

        return codiceConto+","+iban;
    }

    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        String query="update conti set "+attrModificato+"= ";
        if(attrModificato.equals("codiceConto"))query+=newValue;
        else query+="'"+newValue+"'";

        return query+" where "+conditions;
    }
    

    
}
