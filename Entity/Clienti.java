package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Clienti implements EntityInterface {
    private String nome;
    private String cognome;
    private String codicefiscale;
    private String dataNascita;
    
    public Clienti(){
        nome=null;
        cognome=null;
        codicefiscale=null;
        dataNascita=null;
    }

    public Clienti(String nome, String cognome, String codicefiscale, String dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.codicefiscale = codicefiscale;
        this.dataNascita = dataNascita;
    }

    @Override
    public String toString() {
        String risultato="Clienti [";
        if(nome!=null)risultato+="nome="+nome+" ";
        if(cognome!=null)risultato+="cognome="+cognome+" ";
        if(codicefiscale!=null)risultato+="codice fiscale= "+codicefiscale+" ";
        if(dataNascita!=null)risultato+="data di nascita= "+dataNascita;

        risultato+="]";
        return risultato;
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery)throws SQLException {
    
        if(attributi==null){
            nome=risQuery.getString("nome");
            cognome=risQuery.getString("cognome");
            codicefiscale=risQuery.getString("CF");
            dataNascita=risQuery.getString("dataNascita");
        }
        else{
            String []attributilist=attributi.split(" ");
            for(int i=0;i<attributilist.length;i++){
                if(attributilist[i].equals("nome")) nome=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("cognome")) cognome=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("CF")) codicefiscale=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("dataNascita"))dataNascita=risQuery.getString(attributilist[i]);
            }
        }    
    
        return this;
    }

    @Override
    public String insert() {
        System.out.println("Inserisci nome");
        nome=Utente.input();

        System.out.println("inserisci cognome");
        cognome=Utente.input();

        System.out.println("Inserire codice fiscale");
        codicefiscale=Utente.input();

        System.out.println("Inserire data di nascita");
        dataNascita=Utente.input();

        boolean errore=true;
        String querymetodo=null;
        while(errore){
            errore=false;
            System.out.println("Paghi con carta o con conto?");
            String metodo=Utente.input();

            
            if(metodo.equals("carta")){
                Carte carta=new Carte();
                querymetodo=carta.insert();
            }
            else if(metodo.equals("conto")){
                Conti conto=new Conti();
                querymetodo=conto.insert();
            }
            else{
                System.err.println("Errore, metodo invalido, riprova");
                errore=true;
            }
        }
        String query="call inserisci_dati_cliente('"+codicefiscale+"','"+nome+"','"+cognome+"','"+dataNascita+"',";
        
        String[]listMetodo=querymetodo.split(",");
        
        if(listMetodo.length==3){
            query+=listMetodo[0]+",null,'"+listMetodo[1]+"',"+listMetodo[2]+")";
        }
        else{
            query+=listMetodo[0]+",'"+listMetodo[1]+"',null,null)";
        }
        
        return query;
    }

    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        return "update clienti set "+attrModificato+"='"+newValue+"' where "+conditions;
    }    
    
}
