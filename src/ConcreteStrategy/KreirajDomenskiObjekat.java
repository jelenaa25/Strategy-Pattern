package ConcreteStrategy;

import AbstractProductB.BrokerBazePodataka;
import AbstractProductC.Kontroler;
import DomainClasses.DKPorudzbina;
import Strategy.SistemskaOperacija;
import java.util.concurrent.atomic.AtomicInteger;

 public class KreirajDomenskiObjekat implements  SistemskaOperacija{ // Receiver
    
    BrokerBazePodataka bbp;
    Kontroler kon;
     
    public KreirajDomenskiObjekat(BrokerBazePodataka bbp1,Kontroler kon1){bbp=bbp1;kon=kon1;} 
     
    @Override
    public boolean izvrsiSO(){
    boolean signal;
    DKPorudzbina ip= new DKPorudzbina(); // Promenljivo!!!
    AtomicInteger counter = new AtomicInteger(0);
    
    bbp.makeConnection();
    if (!bbp.getCounter(ip,counter)) return false;
    if (!bbp.increaseCounter(ip,counter)) return false;
          
    ip.setSifraPorudzbine(counter.get()); // Promenljivo!!!
    signal = bbp.insertRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          kon.napuniGrafickiObjekatIzDomenskogObjekta(ip);
          kon.setPoruka("Систем је креирао нову поруџбину."); // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
        kon.isprazniGrafickiObjekat();
         kon.setPoruka("Систем не може да креира нову поруџбину."); // Promenljivo!!!
        }
    kon.prikaziPoruku();
    bbp.closeConnection();
    return signal;
}
    
}
