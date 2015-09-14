package org.loccs.outsource.ibe;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Map;
import it.unisa.dia.gas.jpbc.Element;

public class Ren201509IBEScheme2 extends PrimeOrderPairingIBEScheme {
    protected Element[] C = new Element[3];

    protected Element ETi;

    protected Map<BigInteger, Element> H1 = new Hashtable<BigInteger, Element> ();

    protected Map<BigInteger, Element> H2 = new Hashtable<BigInteger, Element> ();

    protected Element[] K = new Element[3];

    protected Element[] KTi = new Element[3];

    protected Element g1;

    protected Element g2;

    protected BigInteger rTi;

    protected BigInteger s;

    protected BigInteger Ti;

    protected BigInteger[] alpha = new BigInteger[3];

    protected BigInteger rID;

    public Ren201509IBEScheme2(int bitsize) {
//begin of modifiable zone................T/56bca620-b1f4-4240-86ed-fbfd43103e52
super(bitsize);

//end of modifiable zone..................E/56bca620-b1f4-4240-86ed-fbfd43103e52
//begin of modifiable zone................T/984fa7d3-83b8-48b8-9210-c6d495b8808e
        
//end of modifiable zone..................E/984fa7d3-83b8-48b8-9210-c6d495b8808e
    }

    protected Element Hash(Map<BigInteger, Element> map, BigInteger key) {
//begin of modifiable zone................T/1e0d35fc-8885-474f-8c2a-0554f6f7ec0a
        Element e;
        
        if (map.containsKey(key)) {
            e = map.get(key);
        }else {
            e = pairing.getG1().newRandomElement();
            map.put(key, e);
        }
//end of modifiable zone..................E/1e0d35fc-8885-474f-8c2a-0554f6f7ec0a
//begin of modifiable zone................T/ca77d046-5e23-4e27-a553-39776002793e
        return e.duplicate();
//end of modifiable zone..................E/ca77d046-5e23-4e27-a553-39776002793e
    }

    protected void decrypt() {
//begin of modifiable zone................T/dc183911-a1db-4008-9e41-da152113e0b8
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
//end of modifiable zone..................E/dc183911-a1db-4008-9e41-da152113e0b8
    }

    protected void encrypt() {
//begin of modifiable zone................T/1933659f-3f1b-4b18-afe1-446d27f71ad4
        s = randomNumber(order);
        C[0] = pairing.pairing(g1, g2);
        C[0].pow(s);
        C[0].mul(M);
        
        C[1] = Hash(H1, ID);
        C[1].pow(s);
        
        C[2] = g.duplicate();
        C[2].pow(s);
        
        ETi = Hash(H2, Ti);
        ETi.pow(s);
//end of modifiable zone..................E/1933659f-3f1b-4b18-afe1-446d27f71ad4
    }

    protected void keygen() {
//begin of modifiable zone................T/55c8aa0f-bf8c-40d6-b701-21508b2b0e0f
        alpha[1] = randomNumber(order);
        rID = randomNumber(order);
        alpha[2] = alpha[0].subtract(alpha[1]);
        alpha[2] = alpha[2].mod(order);
        
        K[1] = g.duplicate();
        K[1].pow(rID);
        
        K[2] = g2.duplicate();
        K[2].pow(alpha[1]);
        Element t1 = Hash(H1, ID);
        t1.pow(rID);
        K[2].mul(t1);
        
        rTi = randomNumber(order);
        KTi[1] = g.duplicate();
        KTi[1].pow(rTi);
        
        KTi[2] = g2.duplicate();
        KTi[2].pow(alpha[2]);
        t1 = Hash(H2, Ti);
        t1.pow(rTi);
        KTi[2].mul(t1);
//end of modifiable zone..................E/55c8aa0f-bf8c-40d6-b701-21508b2b0e0f
    }

    protected void keyupdate() {
//begin of modifiable zone(JavaCode)......C/27b73fa8-2d7c-4277-ad1e-7928fb2f925b
    	Ti = Ti.add(BigInteger.ONE);
    	rTi = randomNumber(order);

		KTi[1] = g.duplicate();
		KTi[1].pow(rTi);
		
		KTi[2] = g2.duplicate();
		KTi[2].pow(alpha[2]);
		Element t1 = Hash(H2, Ti);
		t1.pow(rTi);
		KTi[2].mul(t1);		
//end of modifiable zone(JavaCode)........E/27b73fa8-2d7c-4277-ad1e-7928fb2f925b
    }

    protected void setupAfterPairingInitialized() {
//begin of modifiable zone................T/b4662a05-48fc-4697-8a06-c76bf4a85f9a
        g2 = pairing.getG1().newRandomElement();
        alpha[0] = randomNumber(order);
        g1 = g.duplicate();
        g1.pow(alpha[0]);
        Ti = new BigInteger("1");
//end of modifiable zone..................E/b4662a05-48fc-4697-8a06-c76bf4a85f9a
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/49f3fe9c-d0a3-4a46-864d-856804d02dcd
        Ren201509IBEScheme2 outsource = new Ren201509IBEScheme2(512);
        
        outsource.setRepeat(1);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/49f3fe9c-d0a3-4a46-864d-856804d02dcd
    }

}
