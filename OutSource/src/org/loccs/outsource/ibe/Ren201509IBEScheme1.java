package org.loccs.outsource.ibe;

import java.math.BigInteger;
import it.unisa.dia.gas.jpbc.Element;

public class Ren201509IBEScheme1 extends CompositeOrderPairingIBEScheme {
    protected Element[] C = new Element[3];

    protected Element[] K = new Element[3];

    protected Element R11;

    protected Element R12;

    protected BigInteger alpha;

    protected Element e_g1_g1_alpha;

    protected Element g1;

    protected Element h;

    protected BigInteger rID;

    protected BigInteger s;

    /**
     * <Enter note text here>
     */
    protected Element u;

    public Ren201509IBEScheme1(int bitsize) {
//begin of modifiable zone................T/96bbf9de-5a66-4233-ae48-5250167cf47a
    	super(3, bitsize);
//end of modifiable zone..................E/96bbf9de-5a66-4233-ae48-5250167cf47a
//begin of modifiable zone(JavaCode)......C/5137f929-a67f-4057-a974-0f9a31c54674

//end of modifiable zone(JavaCode)........E/5137f929-a67f-4057-a974-0f9a31c54674
    }

    protected void decrypt() {
//begin of modifiable zone................T/78f3b343-914d-4301-8315-bef9ce3bcbe1
        Element t1, t2;
        t1 = pairing.pairing(K[1], C[1]);
        t2 = pairing.pairing(C[2], K[2]);
        t1.mul(C[0]);
        t1.div(t2);
        
        if (!t1.isEqual(M))
            System.out.println("decryption incorrect.");
//end of modifiable zone..................E/78f3b343-914d-4301-8315-bef9ce3bcbe1
    }

    protected void encrypt() {
//begin of modifiable zone................T/d46322b7-09d7-43f4-91aa-ec83e4c1b375
        s = randomNumber(order);
        C[0] = e_g1_g1_alpha.duplicate();
        C[0].pow(s);
        C[0].mul(M);
        C[1] = u.duplicate();
        C[1].pow(ID);
        C[1].mul(h);
        C[1].pow(s);
        C[2] = g1.duplicate();
        C[2].pow(s);
//end of modifiable zone..................E/d46322b7-09d7-43f4-91aa-ec83e4c1b375
    }

    protected void keygen() {
//begin of modifiable zone................T/26f03c8b-7164-4bc2-bdfd-28666cd9d81f
        rID = randomNumber(order);
        R11 = newRandomGpElement(3);
        R12 = newRandomGpElement(3);
        
        K[1] = g1.duplicate();
        K[1].pow(rID);
        K[1].mul(R11);
        
        K[2] = g1.duplicate();
        K[2].pow(alpha);
        Element t1 = u.duplicate();
        t1.pow(ID);
        t1.mul(h);
        t1.pow(rID);
        K[2].mul(t1);
        K[2].mul(R12);
//end of modifiable zone..................E/26f03c8b-7164-4bc2-bdfd-28666cd9d81f
    }

    protected void keyupdate() {
//begin of modifiable zone(JavaCode)......C/1808e64a-1f76-4870-ad68-3fa8f3826852

//end of modifiable zone(JavaCode)........E/1808e64a-1f76-4870-ad68-3fa8f3826852
    }

    protected void setupAfterPairingInitialized() {
//begin of modifiable zone................T/04ff9658-e4f9-4608-a297-d94d7138ca25
        gGp[1] = getGpGenerator(1);
        gGp[3] = getGpGenerator(3);
        
        g1 = newRandomGpElement(1);
        u = newRandomGpElement(1);
        h = newRandomGpElement(1);
        alpha = randomNumber(order);
        e_g1_g1_alpha = pairing.pairing(g1, g1);
        e_g1_g1_alpha.pow(alpha);
//end of modifiable zone..................E/04ff9658-e4f9-4608-a297-d94d7138ca25
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/834701bb-1dcc-46e1-b8d6-ee68fb572b05
        Ren201509IBEScheme1 outsource = new Ren201509IBEScheme1(512);
        
        outsource.setRepeat(1);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/834701bb-1dcc-46e1-b8d6-ee68fb572b05
    }

}
