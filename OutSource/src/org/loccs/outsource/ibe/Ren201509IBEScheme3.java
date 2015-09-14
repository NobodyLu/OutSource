package org.loccs.outsource.ibe;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Map;
import it.unisa.dia.gas.jpbc.Element;

public class Ren201509IBEScheme3 extends CompositeOrderPairingIBEScheme {
    protected Element[] C = new Element[3];

    protected Element ETi;

    protected Map<String, BigInteger> H = new Hashtable<String, BigInteger> ();

    protected Element[] K = new Element[3];

    protected Element[] KTi = new Element[3];

    protected Element[][] R = new Element[3][3];

    protected BigInteger Ti;

    protected BigInteger[] alpha = new BigInteger[3];

    protected Element e_g1_g1_alpha;

    protected Element g1;

    protected Element[] h = new Element[3];

    protected BigInteger rID;

    protected BigInteger rTi;

    protected BigInteger s;

    protected Element[] u = new Element[3];

    public Ren201509IBEScheme3(int bitsize) {
//begin of modifiable zone................T/70bb637d-cd0e-4e64-9963-645f4428693a
    	super(3, bitsize);
//end of modifiable zone..................E/70bb637d-cd0e-4e64-9963-645f4428693a
//begin of modifiable zone................T/5288f45f-32a5-463c-a62d-976f4639367e
        
//end of modifiable zone..................E/5288f45f-32a5-463c-a62d-976f4639367e
    }

    protected BigInteger Hash(BigInteger key1, BigInteger key2) {
//begin of modifiable zone................T/1f7f4fa4-88af-4374-92e8-1f715a35d06f
        String key = key1.toString() + "||" + key2.toString();
        
        if (H.containsKey(key))
            return H.get(key);
        
        BigInteger i = randomNumber(order);
        H.put(key, i);
//end of modifiable zone..................E/1f7f4fa4-88af-4374-92e8-1f715a35d06f
//begin of modifiable zone................T/43a547b9-67b2-447c-a64b-53ea8d0780fd
        return i;
//end of modifiable zone..................E/43a547b9-67b2-447c-a64b-53ea8d0780fd
    }

    protected void decrypt() {
//begin of modifiable zone................T/18a13623-f22d-4e7a-baf5-66fa1448a11d
        Element t1, t2, t3;
        t1 = pairing.pairing(K[1], C[1]);
        t2 = pairing.pairing(KTi[1], ETi);
        t1.mul(t2);
        t1.mul(C[0]);
        
        t2 = pairing.pairing(C[2], K[2]);
        t3 = pairing.pairing(C[2], KTi[2]);
        t2.mul(t3);
        
        t1.div(t2);
        
        if (!t1.isEqual(M))
            System.out.println("decryption incorrect.");
//end of modifiable zone..................E/18a13623-f22d-4e7a-baf5-66fa1448a11d
    }

    protected void encrypt() {
//begin of modifiable zone................T/34677d93-03ee-430d-aa18-1c11c7e71d78
        s = randomNumber(order);
        
        C[0] = e_g1_g1_alpha.duplicate();
        C[0].pow(s);
        C[0].mul(M);
        C[1] = u[1].duplicate();
        C[1].pow(ID);
        C[1].mul(h[1]);
        C[1].pow(s);    
        C[2] = g1.duplicate();
        C[2].pow(s);
        
        ETi = u[2].duplicate();
        ETi.pow(Hash(ID, Ti));
        ETi.mul(h[2]);
        ETi.pow(s);
//end of modifiable zone..................E/34677d93-03ee-430d-aa18-1c11c7e71d78
    }

    protected void keygen() {
//begin of modifiable zone................T/4822b148-c6d8-447b-9aa5-e0c490e8e376
        alpha[1] = randomNumber(order);
        rID = randomNumber(order);
        alpha[2] = alpha[0].subtract(alpha[1]);
        alpha[2] = alpha[2].mod(order);
        
        R[1][1] = newRandomGpElement(3);
        R[1][2] = newRandomGpElement(3);
        
        K[1] = g1.duplicate();
        K[1].pow(rID);
        K[1].mul(R[1][1]); 
        
        K[2] = g1.duplicate();
        K[2].pow(alpha[1]);
        Element t1 = u[1].duplicate();
        t1.pow(ID);
        t1.mul(h[1]);
        t1.pow(rID);
        K[2].mul(t1);
        K[2].mul(R[1][2]);    
        
        R[2][1] = newRandomGpElement(3);
        R[2][2] = newRandomGpElement(3);
        
        rTi = randomNumber(order);
        KTi[1] = g1.duplicate();
        KTi[1].pow(rTi);
        KTi[1].mul(R[2][1]);
        
        KTi[2] = g1.duplicate();
        KTi[2].pow(alpha[2]);
        t1 = u[2].duplicate();
        t1.pow(Hash(ID, Ti));
        t1.mul(h[2]);
        t1.pow(rTi);
        KTi[2].mul(t1);
        KTi[2].mul(R[2][2]);
//end of modifiable zone..................E/4822b148-c6d8-447b-9aa5-e0c490e8e376
    }

    protected void keyupdate() {
//begin of modifiable zone................T/c6c2f250-e942-4b1f-8a24-63510008f9bd
        Ti = Ti.add(BigInteger.ONE);
        rTi = randomNumber(order);
        
        KTi[1] = g1.duplicate();
        KTi[1].pow(rTi);
        KTi[1].mul(R[2][1]);
        
        KTi[2] = g1.duplicate();
        KTi[2].pow(alpha[2]);
        Element t1 = u[2].duplicate();
        t1.pow(Hash(ID, Ti));
        t1.mul(h[2]);
        t1.pow(rTi);
        KTi[2].mul(t1);
        KTi[2].mul(R[2][2]);
//end of modifiable zone..................E/c6c2f250-e942-4b1f-8a24-63510008f9bd
    }

    protected void setupAfterPairingInitialized() {
//begin of modifiable zone................T/df146d08-b374-4358-abda-28e9b9a1f676
        gGp[1] = getGpGenerator(1);
        gGp[3] = getGpGenerator(3);
        
        g1 = newRandomGpElement(1);
        u[1] = newRandomGpElement(1);
        u[2] = newRandomGpElement(1);
        h[1] = newRandomGpElement(1);
        h[2] = newRandomGpElement(1);
        
        alpha[0] = randomNumber(order);
        e_g1_g1_alpha = pairing.pairing(g1, g1);
        e_g1_g1_alpha.pow(alpha[0]);
        
        Ti = new BigInteger("1");
//end of modifiable zone..................E/df146d08-b374-4358-abda-28e9b9a1f676
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/0122f8bd-88d0-4984-b9ca-ea3f80f2fd98
        Ren201509IBEScheme3 outsource = new Ren201509IBEScheme3(512);
        
        outsource.setRepeat(1);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/0122f8bd-88d0-4984-b9ca-ea3f80f2fd98
    }

}
