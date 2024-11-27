package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Automobili implements EntityInterface {
    private String targa;
    private String modello;
    private Integer dispositivo;
    private String proprietario;
    
    public Automobili() {
        targa=null;
        modello=null;
        dispositivo=null;
        proprietario=null;
    }

    public Automobili(String targa, String modello, Integer dispositivo, String proprietario) {
        this.targa = targa;
        this.modello = modello;
        this.dispositivo = dispositivo;
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        String risultato="[";
        if(targa!=null)risultato+="targa="+targa+" ";
        if(modello!=null)risultato+="modello="+modello+" ";
        if(proprietario!=null)risultato+="proprietario= "+proprietario+" ";
        if(dispositivo!=null)risultato+="dispositivo= "+dispositivo+" ";

        risultato+="]";
        return risultato;
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery) throws SQLException {
        if(attributi==null){
            targa=risQuery.getString("targa");
            modello=risQuery.getString("modello");
            proprietario=risQuery.getString("proprietario");
            dispositivo=risQuery.getInt("dispositivo");
        }
        else{
            String []attributilist=attributi.split(" ");
            for(int i=0;i<attributilist.length;i++){
                if(attributilist[i].equals("targa")) targa=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("modello")) modello=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("proprietario")) proprietario=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("dispositivo"))dispositivo=risQuery.getInt(attributilist[i]);
            }
        }    
    
        return this;
    }

    @Override
    public String insert() {
        System.out.println("inserire targa");
        targa=Utente.input().toUpperCase();
        
        System.out.println("inserire modello");
        modello=Utente.input();

        System.out.println("Inserire CF proprietario");
        proprietario=Utente.input();
        
        String query=null;
        boolean errore=true;
        while(errore){
            errore=false;
            System.out.println("hai bisogno di un nuovo dispositivo? (s se si,n se no)");
            String ris=Utente.input();
            if(ris.equals("s")){
                if(proprietario.equals("")) query="call registra_dispositivo(null,'"+targa+"','"+modello+"')";
                else query="call registra_dispositivo('"+proprietario+"','"+targa+"','"+modello+"')";
            }
            else if(ris.equals("n")){
                System.out.println("inserisci codice dispositivo:");
                dispositivo=Utente.inputInt();
                if(proprietario.equals(""))query="insert into automobili values('"+targa+"','"+modello+"',null,"+dispositivo+")";
                else query="insert into automobili values('"+targa+"','"+modello+"','"+proprietario+"',"+dispositivo+")";
            }
            else {
                System.err.println("Hai inserito una risposta non valida");
                errore=true;
            }
        }
        
        return query;
    }

    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        String query="update automobili set "+attrModificato+"= ";
        if(attrModificato.equals("dispositivo"))query+=newValue;
        else query+="'"+newValue+"'";

        return query+" where "+conditions;
    }
    
    
    
    
}
