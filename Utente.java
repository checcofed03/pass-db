package interfaccia_utente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Utente {
    
    public static String input(){
        InputStreamReader in=new InputStreamReader(System.in);
        BufferedReader keyboard= new BufferedReader(in);
        
        try {
            String prova=keyboard.readLine();
            return prova;
        } catch (IOException e) {
            return "";
        }
    }
    public static Integer inputInt(){
        boolean errore=true;
        Integer ris=null;
        while(errore){
            errore=false;
            try{
                ris=Integer.valueOf(input());
            }
            catch(NumberFormatException e){
                System.err.println("Errore, devi inserire un intero");
                errore=true;
            }
        }
        return ris;
    }
}