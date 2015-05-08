package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import org.loccs.outsource.StepInformation;

public class Ren201504PairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected BigInteger[] a = new BigInteger[4];

    protected BigInteger[] b = new BigInteger[4];

    public Ren201504PairingOutsource(int rbits, int qbits) {
//begin of modifiable zone................T/a2ef639a-c487-4ce7-9cf0-a92284d5ce10
super(rbits, qbits);

//end of modifiable zone..................E/a2ef639a-c487-4ce7-9cf0-a92284d5ce10
//begin of modifiable zone(JavaCode)......C/30159e1b-d0ff-4254-895d-fe4607b81128

//end of modifiable zone(JavaCode)........E/30159e1b-d0ff-4254-895d-fe4607b81128
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/b64d6bd4-4402-459d-ac6d-c2566bea95c4
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/b64d6bd4-4402-459d-ac6d-c2566bea95c4
//begin of modifiable zone................T/2d324fae-7da0-4996-b276-43bf65be0a15
        return entities;
//end of modifiable zone..................E/2d324fae-7da0-4996-b276-43bf65be0a15
    }

    protected StepInformation step() {
//begin of modifiable zone................T/4a8d107b-c407-411b-82ed-85c58f6db806
        if (nextStep.equals(""))
            return rand();
//end of modifiable zone..................E/4a8d107b-c407-411b-82ed-85c58f6db806
//begin of modifiable zone................T/c4b2533b-9797-4fac-aae6-2386953c32e8
        return null;
//end of modifiable zone..................E/c4b2533b-9797-4fac-aae6-2386953c32e8
    }

    protected StepInformation rand() {
//begin of modifiable zone(JavaCode)......C/73c62e2f-c528-4f20-a3b4-e8f4c783443d
    	for (int i = 0; i < 4; i++) {
    		a[i] = randomNumber(order);
    		b[i] = randomNumber(order);
    	}
//end of modifiable zone(JavaCode)........E/73c62e2f-c528-4f20-a3b4-e8f4c783443d
//begin of modifiable zone................T/ea4cddbf-1907-4fec-b1e1-e38985f8f787
        return new StepInformation("TP", "", false);
//end of modifiable zone..................E/ea4cddbf-1907-4fec-b1e1-e38985f8f787
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/2a614142-2ae8-4a73-a264-ca14463172a7
        Ren201504PairingOutsource outsource = new Ren201504PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        outsource.direct();
//end of modifiable zone..................E/2a614142-2ae8-4a73-a264-ca14463172a7
    }

}
