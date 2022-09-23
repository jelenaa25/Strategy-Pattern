package ConcreteStrategy;

import AbstractProductB.BrokerBazePodataka;
import AbstractProductC.Kontroler;
import DomainClasses.DKPorudzbina;
import Strategy.SistemskaOperacija;

 public class NadjiDomenskiObjekat implements  SistemskaOperacija{ // Receiver
    
    BrokerBazePodataka bbp;
    Kontroler kon;
    
    
    public NadjiDomenskiObjekat(BrokerBazePodataka bbp1,Kontroler kon1){bbp=bbp1;kon=kon1;} 
     
    public boolean izvrsiSO(){
    kon.napuniDomenskiObjekatIzGrafickogObjekta();
     boolean signal;
    bbp.makeConnection();
    DKPorudzbina ip = (DKPorudzbina)bbp.findRecord(kon.getDomenskiObjekat()); // Promenljivo!!!
    if (ip != null) 
        { kon.napuniGrafickiObjekatIzDomenskogObjekta(ip);
          kon.setPoruka("Систем je нашао поруџбину."); // Promenljivo!!!
          signal = true;
        }
        else
        { kon.isprazniGrafickiObjekat();
          kon.setPoruka("Систем не може да нађе поруџбину."); // Promenljivo!!!
          signal = false;
        }
    kon.prikaziPoruku();
    bbp.closeConnection();
    return signal;  
}
    
}
