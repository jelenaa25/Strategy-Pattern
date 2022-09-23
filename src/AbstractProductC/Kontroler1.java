

package AbstractProductC;


import AbstractProductA.*;
import AbstractProductB.*;
import ConcreteStrategy.KreirajDomenskiObjekat;
import ConcreteStrategy.NadjiDomenskiObjekat;
import ConcreteStrategy.ObrisiDomenskiObjekat;
import ConcreteStrategy.PromeniDomenskiObjekat;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import DomainClasses.DKPorudzbina;  // Promenljivo
import DomainClasses.GeneralDObject;
import Context.Dugme;
import Context.TekstPolje;



public final class Kontroler1 extends Kontroler{ // Client
   
    
   
    
    public Kontroler1(EkranskaForma ef1,BrokerBazePodataka bbp1){ef=ef1;bbp=bbp1; Povezi();}
    
    void Povezi()
    {
      KreirajDomenskiObjekat kdo = new KreirajDomenskiObjekat(bbp,this); 
      Dugme dk1 = new Dugme(kdo,ef.getPanel().getKreiraj()); 
      
      PromeniDomenskiObjekat pdo = new PromeniDomenskiObjekat(bbp,this);
      Dugme dk2 = new Dugme(pdo,ef.getPanel().getPromeni());
      
      ObrisiDomenskiObjekat odo = new ObrisiDomenskiObjekat(bbp,this);
      Dugme dk3 = new Dugme(odo,ef.getPanel().getObrisi());
      
      NadjiDomenskiObjekat ndo = new NadjiDomenskiObjekat(bbp,this);
      Dugme dk4 = new Dugme(ndo,ef.getPanel().getNadji());
      
      
      TekstPolje tp = new TekstPolje(ndo,ef.getPanel().getSifraPorudzbine1());
         
     
    }
    
// Promenljivo!!!  
    @Override
    public void napuniDomenskiObjekatIzGrafickogObjekta()   {
       ip= new DKPorudzbina();
       ip.setSifraPorudzbine(getInteger(ef.getPanel().getSifraPorudzbine()));
       ip.setPalacinka(ef.getPanel().getPalacinka());
       ip.setPreliv(ef.getPanel().getPreliv());
       ip.setVoce(ef.getPanel().getVoce());
    
    }

// Promenljivo!!!
    @Override
    public void napuniGrafickiObjekatIzDomenskogObjekta(GeneralDObject gdo){
       DKPorudzbina ip = (DKPorudzbina)gdo;
       ef.getPanel().setSifraPorudzbine(Integer.toString(ip.getSifraPorudzbine()));
       ef.getPanel().setPalacinka(ip.getPalacinka());
       ef.getPanel().setPreliv(ip.getPreliv());
       ef.getPanel().setVoce(ip.getVoce());
      
    }

   
// Promenljivo!!!
    @Override
    public void isprazniGrafickiObjekat(){
 ef.getPanel().setSifraPorudzbine("");
 ef.getPanel().setPalacinka("nista");
 ef.getPanel().setPreliv("nista");
 ef.getPanel().setVoce("nista");
}

    @Override 
    public void prikaziPoruku(){ 
  ef.getPanel().setPoruka(poruka);
      
  Timer timer = new Timer();
  
  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    ef.getPanel().setPoruka(""); 
  }
}, 3000);
  
}

 public int getInteger(String s) {
    int broj = 0;
    try
        {
            if(s != null)
                broj = Integer.parseInt(s);
        }
            catch (NumberFormatException e)
            { broj = 0;}
   
    return broj;
}


}    







