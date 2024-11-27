package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaccia_utente.Utente;

public class Tragitti implements EntityInterface {
    private Integer dispositivoIndividuato;    
    private Integer numero;    
    private String nomeCaselloIngresso;
    private Integer numeroCaselloIngresso;    
    private String data_ora_ingresso;
    private String nomeCaselloUscita;
    private Integer numeroCaselloUscita;
    private String data_ora_uscita;
        
    public Tragitti() {
        this.dispositivoIndividuato = null;
        this.numero = null;
        this.nomeCaselloIngresso = null;
        this.numeroCaselloIngresso = null;
        this.data_ora_ingresso = null;
        this.nomeCaselloUscita = null;
        this.numeroCaselloUscita = null;
        this.data_ora_uscita = null;
    }

    public Tragitti(int dispositivoIndividuato, int numero, String nomeCaselloIngresso, int numeroCaselloIngresso,
            String data_ora_ingresso, String nomeCaselloUscita, int numeroCaselloUscita, String data_ora_uscita) {
        this.dispositivoIndividuato = dispositivoIndividuato;
        this.numero = numero;
        this.nomeCaselloIngresso = nomeCaselloIngresso;
        this.numeroCaselloIngresso = numeroCaselloIngresso;
        this.data_ora_ingresso = data_ora_ingresso;
        this.nomeCaselloUscita = nomeCaselloUscita;
        this.numeroCaselloUscita = numeroCaselloUscita;
        this.data_ora_uscita = data_ora_uscita;
    }

    @Override
    public String toString() {
        String ris="[";
        
        if(dispositivoIndividuato!=null) ris+="dispositivo individuato="+dispositivoIndividuato+" ";
        if(numero!=null) ris+="numero="+numero+" ";
        if(nomeCaselloIngresso!=null) ris+="nome casello ingresso="+nomeCaselloIngresso+" ";
        if(nomeCaselloUscita!=null)ris+="nome casello uscita="+nomeCaselloUscita+" ";
        if(numeroCaselloIngresso!=null) ris+="numero casello ingresso="+numeroCaselloIngresso+" ";
        if(numeroCaselloUscita!=null)ris+="numero casello uscita="+numeroCaselloUscita+" ";
        if(data_ora_ingresso!=null) ris+="data e ora ingresso="+data_ora_ingresso+" ";
        if(data_ora_uscita!=null)ris+="data e ora uscita"+data_ora_uscita+" ";

        ris+="]";
        return ris;
    }

    @Override
    public EntityInterface traduci(String attributi, ResultSet risQuery) throws SQLException {
        if(attributi==null){
            dispositivoIndividuato=risQuery.getInt("dispositivoIndividuato");
            numero=risQuery.getInt("numero");
            nomeCaselloIngresso=risQuery.getString("nomeCaselloIngresso");
            numeroCaselloIngresso=risQuery.getInt("numeroCaselloIngresso");
            data_ora_ingresso=risQuery.getString("data_ora_ingresso");
            
            nomeCaselloUscita=risQuery.getString("nomeCaselloUscita");
            if(nomeCaselloUscita!=null){
                numeroCaselloUscita=risQuery.getInt("numeroCaselloUscita");
                data_ora_uscita=risQuery.getString("data_ora_uscita");
            }
            
        }
        else{
            String []attributilist=attributi.split(" ");
            for(int i=0;i<attributilist.length;i++){
                if(attributilist[i].equals("dispositivoIndividuato")) dispositivoIndividuato=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("numero")) numero=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("nomeCaselloIngresso")) nomeCaselloIngresso=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("nomeCaselloUscita"))nomeCaselloUscita=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("numeroCaselloIngresso")) numeroCaselloIngresso=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("numeroCaselloUscita"))numeroCaselloUscita=risQuery.getInt(attributilist[i]);
                if(attributilist[i].equals("data_ora_ingresso")) data_ora_ingresso=risQuery.getString(attributilist[i]);
                if(attributilist[i].equals("data_ora_uscita"))data_ora_uscita=risQuery.getString(attributilist[i]);
                
            }
        } 
        return this;   
    }

    @Override
    public String insert() {
        String query=null;

        System.out.println("inserire dispositivoIndividuato");
        dispositivoIndividuato=Utente.inputInt();
        
        System.out.println("inserire nome ingresso");
        nomeCaselloIngresso=Utente.input();

        System.out.println("inserire numero ingresso");
        numeroCaselloIngresso=Utente.inputInt();

        System.out.println("inserire data e ora ingresso");
        data_ora_ingresso=Utente.input();
        
        System.out.println("Inserire nome uscita");
        nomeCaselloUscita=Utente.input();

        System.out.println("inserire numero uscita");
        numeroCaselloUscita=Utente.inputInt();

        System.out.println("Inserire data e ora uscita");
        data_ora_uscita=Utente.input();

        if(nomeCaselloUscita.equals("")||numeroCaselloUscita.equals("")||data_ora_uscita.equals(""))
        query="CALL registra_tragitti("+dispositivoIndividuato+",'"+nomeCaselloIngresso+"',"+
        numeroCaselloIngresso+",'"+data_ora_ingresso+"',null,null,null)";

        else 
        query="CALL registra_tragitti("+dispositivoIndividuato+",'"+nomeCaselloIngresso+"',"+
        numeroCaselloIngresso+",'"+data_ora_ingresso+"','"+nomeCaselloUscita+"',"+numeroCaselloUscita+
        ",'"+data_ora_uscita+"')";

        return query;
    }

    @Override
    public String update(String attrModificato, String newValue, String conditions) {
        System.err.println("operazione non consentita");
        return null;
    }
    


}
