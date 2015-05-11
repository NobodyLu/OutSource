package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class TZR2PairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected Element[] P = new Element[3];

    public TZR2PairingOutsource(int rbits, int qbits) {
//begin of modifiable zone................T/88e38269-f123-49ab-970f-cc33038fe2cc
super(rbits, qbits);

//end of modifiable zone..................E/88e38269-f123-49ab-970f-cc33038fe2cc
//begin of modifiable zone(JavaCode)......C/c9f44001-c78a-4f6a-92ca-1febca5536aa

//end of modifiable zone(JavaCode)........E/c9f44001-c78a-4f6a-92ca-1febca5536aa
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/2d6a6c72-eda8-4ec8-b5c2-97f4ee90839d
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/2d6a6c72-eda8-4ec8-b5c2-97f4ee90839d
//begin of modifiable zone................T/a64edc45-558f-420a-8035-dea281ce4638
        return entities;
//end of modifiable zone..................E/a64edc45-558f-420a-8035-dea281ce4638
    }

    protected StepInformation step() {
//begin of modifiable zone................T/f0a2b8a8-bb44-43bc-b86a-d1e79afbb78a
        if (nextStep.equals(""))
            return rand();
//end of modifiable zone..................E/f0a2b8a8-bb44-43bc-b86a-d1e79afbb78a
//begin of modifiable zone................T/ff013592-eb61-4e15-bd4a-b5ebcee37194
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/ff013592-eb61-4e15-bd4a-b5ebcee37194
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/d0c69955-b019-4694-a602-fc3e45b4749a
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
//end of modifiable zone..................E/d0c69955-b019-4694-a602-fc3e45b4749a
//begin of modifiable zone................T/c8b0eb9f-2dc5-40cb-98a4-4e86b93e6336
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/c8b0eb9f-2dc5-40cb-98a4-4e86b93e6336
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/b95b79b6-2620-472c-b391-c760aa2483f2
        TZR2PairingOutsource outsource = new TZR2PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/b95b79b6-2620-472c-b391-c760aa2483f2
    }

    public class RandTuple {
        protected BigInteger[] x = new BigInteger[5];

        protected Element[] e = new Element[5];

        public RandTuple(Element P1, Element P2) {
//begin of modifiable zone(JavaSuper).....C/a5cdce40-4dbb-406d-a5ba-3eb4eaa839a6

//end of modifiable zone(JavaSuper).......E/a5cdce40-4dbb-406d-a5ba-3eb4eaa839a6
//begin of modifiable zone(JavaCode)......C/a5cdce40-4dbb-406d-a5ba-3eb4eaa839a6

//end of modifiable zone(JavaCode)........E/a5cdce40-4dbb-406d-a5ba-3eb4eaa839a6
        }

        public Element getX1P1() {
//begin of modifiable zone(JavaCode)......C/0a56f6c4-43e5-4225-8664-96a398f9106d

//end of modifiable zone(JavaCode)........E/0a56f6c4-43e5-4225-8664-96a398f9106d
//begin of modifiable zone(JavaReturned)..C/0a56f6c4-43e5-4225-8664-96a398f9106d
        	return e[0];
//end of modifiable zone(JavaReturned)....E/0a56f6c4-43e5-4225-8664-96a398f9106d
        }

        public Element getX1InvX2X3P1() {
//begin of modifiable zone(JavaCode)......C/7725fcae-bc06-4acb-b763-5ec79965fd77

//end of modifiable zone(JavaCode)........E/7725fcae-bc06-4acb-b763-5ec79965fd77
//begin of modifiable zone(JavaReturned)..C/7725fcae-bc06-4acb-b763-5ec79965fd77
        	return e[1];
//end of modifiable zone(JavaReturned)....E/7725fcae-bc06-4acb-b763-5ec79965fd77
        }

        public Element getInvX1X2P2() {
//begin of modifiable zone(JavaCode)......C/2a8e9b63-24f2-410b-bf1d-742299756688

//end of modifiable zone(JavaCode)........E/2a8e9b63-24f2-410b-bf1d-742299756688
//begin of modifiable zone(JavaReturned)..C/2a8e9b63-24f2-410b-bf1d-742299756688
        	return e[2];
//end of modifiable zone(JavaReturned)....E/2a8e9b63-24f2-410b-bf1d-742299756688
        }

        public Element getInvX1X4P2() {
//begin of modifiable zone(JavaCode)......C/22212cf7-f506-44c2-9a83-fb4e842e84db

//end of modifiable zone(JavaCode)........E/22212cf7-f506-44c2-9a83-fb4e842e84db
//begin of modifiable zone(JavaReturned)..C/22212cf7-f506-44c2-9a83-fb4e842e84db
        	return e[3];
//end of modifiable zone(JavaReturned)....E/22212cf7-f506-44c2-9a83-fb4e842e84db
        }

        public Element getPair() {
//begin of modifiable zone(JavaCode)......C/1a0e9920-7bc3-4c45-b577-0b58def8ccac

//end of modifiable zone(JavaCode)........E/1a0e9920-7bc3-4c45-b577-0b58def8ccac
//begin of modifiable zone(JavaReturned)..C/1a0e9920-7bc3-4c45-b577-0b58def8ccac
        	return e[4];
//end of modifiable zone(JavaReturned)....E/1a0e9920-7bc3-4c45-b577-0b58def8ccac
        }

    }

}
