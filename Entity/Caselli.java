package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Caselli implements EntityInterface{
    private String uscitaAutostradale;
    private Integer numero;

    public Caselli(){
        uscitaAutostradale=null;
        numero=null;
    }
    public Caselli(String uscita,int numero){
        this.uscitaAutostradale=uscita;
        this.numero=numero;
    }
    @Override
    public String toString(){
        String res="[";
        if(uscitaAutostradale!=null)res+="uscita autostradale= "+uscitaAutostradale+" ";
        if(numero!=null)res+="numero= "+numero;
        
        res+="]";
        return res;
    }
    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery) throws SQLException {
        if(attributi==null){
            uscitaAutostradale=risQuery.getString("uscitaAutostradale");
            numero=risQuery.getInt("numero");
        }
        else{
            String []attributilist=attributi.split(" ");
            for(int i=0;i<attributilist.length;i++){
                if(attributilist[i].equals("uscitaAutostradale")) uscitaAutostradale=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("numero")) numero=risQuery.getInt(attributilist[i]);
            }
        } 
        return this;   
    }
    @Override
    public String insert() {
        System.out.println("inserisci nome uscita");
        uscitaAutostradale=Utente.input();
        
        System.out.println("inserisci numero uscita");
        numero=Utente.inputInt();

        String query="Insert into caselli values ('"+uscitaAutostradale+"',"+numero+")";
        return query;
    }
    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        System.err.println("operazione non consentita");
        return null;
    }
    
}
