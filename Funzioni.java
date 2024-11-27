package interfaccia_utente;

import entities.EntityInterface;
import pass_dbException.PassException;
import users.Amministratore;
import users.DBA;
import users.MainUser;

public class Funzioni {
    public static String sceltaTabella(String tipo){
        if(tipo.equals("select"))
        System.out.println("Scegli una tabella su cui operare:\ndispositivi\ncaselli\ntragitti\nautomobili\nclienti\ncarte\nconti\nstatistica");
        
        else if(tipo.equals("insert")){
            System.out.println("Scegli una tabella su cui operare:\ndispositivi\ntragitti\nautomobili\nclienti");
            System.out.println("NOTA: dati su carte e conti vengono inseriti inserendo un nuovo cliente; non è possibile inserire nuovi caselli");    
        }
        else if(tipo.equals("update")){
            System.out.println("Scegli una tabella su cui operare:\ndispositivi\nautomobili\nclienti\ncarte\nconti");
            System.out.println("NOTA: dati su caselli e tragitti non possono essere modificati");    
        }  
        else if(tipo.equals("delete")){
            System.out.println("Scegli una tabella su cui operare:\ndispositivi\nautomobili\nclienti\ncarte\nconti");
            System.out.println("NOTA: dati su caselli e tragitti non possono essere modificati");    
        }


        String tabella=Utente.input();
        return tabella.toLowerCase();        
    }

    public static MainUser accesso(){
        System.out.println("Benvenuto! Innanzitutto, devi identificarti. Inserisci username e password");
        
        System.out.println("username:");
        String username=Utente.input();
        
        System.out.println("password: ");
        String password=Utente.input();
        
        MainUser myEntity=new MainUser(username, password);

        boolean valido=false;
        try {
            valido = myEntity.verify();
        } catch (PassException e) {
            System.err.println(e);
        }
        
        if(valido){
            return myEntity;
        }
        else return null;

    }
    public static MainUser registrazione(){
        System.out.println("Benvenuto! Innanzitutto, devi identificarti. Inserisci username e password");
        
        System.out.println("username:");
        String username=Utente.input();
        
        System.out.println("password: ");
        String password=Utente.input();

        DBA DBA_crea_account=new DBA("DBA_DBPASS","PASS_DBA02!");
        int ris=-1;
        try {
            ris = DBA_crea_account.create_user(username,password);
        } catch (PassException e) {
            System.err.println(e);
        }
        
        if(ris==0){
            return new MainUser(username, password);
        }
        else{
            return null;
        }
        
    }
    
    public static void insert(MainUser myUser){
        System.out.println("inserire password da amministratore:");
        String password=Utente.input();

        Amministratore amm=new Amministratore(password);
        if(amm.autorizza()){
            String tabella=sceltaTabella("insert");
            try{
                int rowsInserted=myUser.create(amm,tabella);
                if(rowsInserted<0)System.out.println("operazione fallita");
                else System.out.println("new row created");
            }
            catch(PassException e){
                System.err.println(e);
            }
           
        }
        else{
            System.out.println("password amministratore errata!");
        }
        
    }
    
    public static void update(MainUser myUser){
        System.out.println("inserire password da amministratore:");
        String password=Utente.input();

        Amministratore amm=new Amministratore(password);
        if(amm.autorizza()){
            String tabella=sceltaTabella("update");
            
            System.out.println("Inserisci attributo da modificare");
            String attr=Utente.input();

            System.out.println("Inserisci nuovo valore/nuovi valori");
            String modifiche=Utente.input();

            System.out.println("Inserire condizioni");
            String condizioni=Utente.input();

            try{
                int rowsUpdated=myUser.update(amm,tabella,attr,modifiche,condizioni);
                if(rowsUpdated>=0)System.out.println(rowsUpdated+" row(s) updated");
                else System.err.println("operazione fallita");
            }
            catch(PassException e){
                System.err.println(e);
            }
        }
        else{
            System.out.println("password amministratore errata!");
        }
        
    }

    public static void select(MainUser myUser){
        String tabella=sceltaTabella("select");
        String attributi=null;
        String condizioni=null;
        
        if(!tabella.equals("statistica")&&!tabella.equals("dati statistici")){
            System.out.println("Inserire gli attributi che si desidera visualizzare");
            attributi=Utente.input();
            if(attributi.equals(""))attributi=null;

            System.out.println("Inserire condizioni");
            condizioni=Utente.input();

            if(condizioni.equals(""))condizioni=null;
        }
        

        EntityInterface[] res=null;
        try {
            res = myUser.read(tabella, attributi, condizioni);
        } catch (PassException e) {
            System.err.println(e);
        }
        if(res!=null){
            for(int i=0;i<res.length;i++){
                System.out.println(res[i]);
            }
        }
        else System.out.println("no data found");
        
        
    }

    public static void delete(MainUser myUser){
        System.out.println("inserire password da amministratore:");
        String password=Utente.input();

        Amministratore amm=new Amministratore(password);
        if(amm.autorizza()){
            String tabella=sceltaTabella("delete");
            
            System.out.println("Inserire condizioni di eliminazione");
            String condizioni=Utente.input();

            try{
                int rowsDeleted=myUser.delete(amm,tabella,condizioni);
                if(rowsDeleted>=0)System.out.println(rowsDeleted+" row(s) deleted");
                else System.err.println("Operazione fallita");
            }
            catch(PassException e){
                System.err.println(e);
            }
        }
        else{
            System.out.println("password amministratore errata!");
        }
    }
}
