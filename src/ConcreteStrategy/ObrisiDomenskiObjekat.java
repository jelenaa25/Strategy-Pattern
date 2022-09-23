
package ConcreteStrategy;

import AbstractProductB.BrokerBazePodataka;
import AbstractProductC.Kontroler;
import Strategy.SistemskaOperacija;


public class ObrisiDomenskiObjekat implements  SistemskaOperacija{
    BrokerBazePodataka bbp;
    Kontroler kon;
    
    
    public ObrisiDomenskiObjekat(BrokerBazePodataka bbp1,Kontroler kon1){bbp=bbp1;kon=kon1;} 
    
    public boolean izvrsiSO(){
     kon.napuniDomenskiObjekatIzGrafickogObjekta();
     bbp.makeConnection();
     boolean signal = bbp.deleteRecord(kon.getDomenskiObjekat());
     if (signal==true) 
        { bbp.commitTransation();
          kon.setPoruka("Систем je oбрисао поруџбину."); // Promenljivo!!!
          kon.isprazniGrafickiObjekat();
        }
     else
        { bbp.rollbackTransation();
          kon.setPoruka("Систем не може да обрише поруџбину."); // Promenljivo!!!
        }
     kon.prikaziPoruku();
    bbp.closeConnection();
    return signal;   
  }   
}
