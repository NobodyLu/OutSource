package org.loccs.outsource.pairing;

import java.util.List;
import java.util.Vector;
import org.loccs.outsource.StepInformation;

public class Ren201504PairingOutsource extends SymmetricPrimeOrderPairingOutsource {
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
//begin of modifiable zone(JavaCode)......C/f4721aa1-b063-4596-94a7-d73ca35b3d9d

//end of modifiable zone(JavaCode)........E/f4721aa1-b063-4596-94a7-d73ca35b3d9d
//begin of modifiable zone................T/c4b2533b-9797-4fac-aae6-2386953c32e8
        return null;
//end of modifiable zone..................E/c4b2533b-9797-4fac-aae6-2386953c32e8
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
