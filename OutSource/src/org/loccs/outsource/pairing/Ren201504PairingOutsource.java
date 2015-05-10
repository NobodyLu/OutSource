package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class Ren201504PairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected BigInteger[] a = new BigInteger[4];

    protected BigInteger[] b = new BigInteger[4];

    protected BigInteger[] inv_a = new BigInteger[2];

    protected BigInteger[] inv_b = new BigInteger[2];

    protected Element P1;

    protected Element P2;

    protected Element[] aP1 = new Element[4];

    protected Element[] bP2 = new Element[4];

    protected Element pair_a0P1_b0P2;

    protected Element inv_pair_a2P1_b0P2;

    protected Element inv_pair_a0P1_b2P2;

    protected Element pair_a1P1_b1P2;

    protected Element inv_pair_a3P1_b1P2;

    protected Element inv_pair_a1P1_b3P2;

    protected Element[] inputA_aP1 = new Element[4];

    protected Element[] inputB_bP2 = new Element[4];

    protected Element[][] alpha = new Element[3][6];

    protected Element[] pair_inputA_a0P1_bP2 = new Element[2];

    protected Element[] pair_aP1_inputB_b0P2 = new Element[2];

    protected BigInteger[] t = new BigInteger[3];

    protected BigInteger[] t1_inv_b = new BigInteger[2];

    protected BigInteger[] t2_inv_a = new BigInteger[2];

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
        
        if (nextStep.equals("TQuery"))
            return tQuery();
        
        if (nextStep.equals("U1Response"))
            return u1Response();
        
        if (nextStep.equals("U2Response"))
            return u2Response();
        
        if (nextStep.equals("TQuery2"))
            return tQuery2();
        
        if (nextStep.equals("U1Response2"))
            return u1Response2();
        
        if (nextStep.equals("U2Response2"))
            return u2Response2();
        
        if (nextStep.equals("TVerify"))
        	return tVerify();
//end of modifiable zone..................E/4a8d107b-c407-411b-82ed-85c58f6db806
//begin of modifiable zone................T/c4b2533b-9797-4fac-aae6-2386953c32e8
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/c4b2533b-9797-4fac-aae6-2386953c32e8
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/691fb0d6-5f8e-4ec2-b970-9f5b4f2c862d
        for (int i = 0; i < 4; i++) {
            a[i] = randomNumber(order);
            b[i] = randomNumber(order);
        }
        for (int i = 0; i < 2; i++) {
            inv_a[i] = a[i].modInverse(order);
            inv_b[i] = b[i].modInverse(order);
        }
        P1 = pairing.getG1().newRandomElement();
        P2 = pairing.getG2().newRandomElement();

        for (int i = 0; i < 4; i++) {
            aP1[i] = P1.duplicate().mul(a[i]);
            bP2[i] = P2.duplicate().mul(b[i]);
        }
        pair_a0P1_b0P2 = pairing.pairing(aP1[0], bP2[0]);
        inv_pair_a2P1_b0P2 = pairing.pairing(aP1[2], bP2[0]);
        inv_pair_a2P1_b0P2 = inv_pair_a2P1_b0P2.invert();
        inv_pair_a0P1_b2P2 = pairing.pairing(aP1[0], bP2[2]);
        inv_pair_a0P1_b2P2 = inv_pair_a0P1_b2P2.invert();
        pair_a1P1_b1P2 = pairing.pairing(aP1[1], bP2[1]);
        inv_pair_a3P1_b1P2 = pairing.pairing(aP1[3], bP2[1]);
        inv_pair_a3P1_b1P2 = inv_pair_a3P1_b1P2.invert();
        inv_pair_a1P1_b3P2 = pairing.pairing(aP1[1], bP2[3]);
        inv_pair_a1P1_b3P2 = inv_pair_a1P1_b3P2.invert();
//end of modifiable zone..................E/691fb0d6-5f8e-4ec2-b970-9f5b4f2c862d
//begin of modifiable zone................T/ea4cddbf-1907-4fec-b1e1-e38985f8f787
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/ea4cddbf-1907-4fec-b1e1-e38985f8f787
    }

    protected StepInformation tQuery() {
//begin of modifiable zone................T/1b081990-1112-4559-ab49-92be22afbbfb
        inputA_aP1[0] = inputA.duplicate().sub(aP1[0]);
        inputB_bP2[0] = inputB.duplicate().sub(bP2[0]);
        for (int i = 1; i < 4; i++) {
            inputA_aP1[i] = inputA_aP1[0].duplicate().add(aP1[i]);
            inputB_bP2[i] = inputB_bP2[0].duplicate().add(bP2[i]);
        }
//end of modifiable zone..................E/1b081990-1112-4559-ab49-92be22afbbfb
//begin of modifiable zone................T/7a28a0c1-67a5-406c-add6-b86bc26ac723
        return new StepInformation("T", "U1Response", false, true);
//end of modifiable zone..................E/7a28a0c1-67a5-406c-add6-b86bc26ac723
    }

    protected StepInformation u1Response() {
//begin of modifiable zone................T/87126018-5cb2-4c00-8e20-2ea1015bd24d
        alpha[1][1] = pairing.pairing(inputA_aP1[0], inputB_bP2[0]);
        alpha[1][2] = pairing.pairing(inputA_aP1[3], bP2[1]);
        alpha[1][3] = pairing.pairing(aP1[1], inputB_bP2[3]);
//end of modifiable zone..................E/87126018-5cb2-4c00-8e20-2ea1015bd24d
//begin of modifiable zone................T/2ef482a3-d9d2-4526-96b9-276816ac90e1
        return new StepInformation("U1", "U2Response", false, true);
//end of modifiable zone..................E/2ef482a3-d9d2-4526-96b9-276816ac90e1
    }

    protected StepInformation u2Response() {
//begin of modifiable zone................T/6faf3c1c-e3e5-475b-be7e-6162c75c7d5c
        alpha[2][1] = pairing.pairing(inputA_aP1[1], inputB_bP2[1]);
        alpha[2][2] = pairing.pairing(inputA_aP1[2], bP2[0]);
        alpha[2][3] = pairing.pairing(aP1[0], inputB_bP2[2]);
//end of modifiable zone..................E/6faf3c1c-e3e5-475b-be7e-6162c75c7d5c
//begin of modifiable zone................T/198f76f1-7927-4e0e-9a70-0cfbaa4894d5
        return new StepInformation("U2", "TQuery2", false, true);
//end of modifiable zone..................E/198f76f1-7927-4e0e-9a70-0cfbaa4894d5
    }

    protected StepInformation tQuery2() {
//begin of modifiable zone................T/634b85e5-c985-4a17-a0fc-89e21560829a
        pair_inputA_a0P1_bP2[0] = alpha[2][2].duplicate().mul(inv_pair_a2P1_b0P2);
        pair_inputA_a0P1_bP2[1] = alpha[1][2].duplicate().mul(inv_pair_a3P1_b1P2);
        pair_aP1_inputB_b0P2[0] = alpha[2][3].duplicate().mul(inv_pair_a0P1_b2P2);
        pair_aP1_inputB_b0P2[1] = alpha[1][3].duplicate().mul(inv_pair_a1P1_b3P2);
        for (int i = 1; i <= 2; i++)
            t[i] = randomNumber(order);
        t1_inv_b[0] = t[1].multiply(inv_b[0]);
        t2_inv_a[0] = t[2].multiply(inv_a[0]);
        t1_inv_b[1] = t[1].multiply(inv_b[1]);
        t2_inv_a[1] = t[2].multiply(inv_a[1]);
//end of modifiable zone..................E/634b85e5-c985-4a17-a0fc-89e21560829a
//begin of modifiable zone................T/764bbc7f-4bd1-4fed-8373-caae6ec85136
        return new StepInformation("T", "U1Response2", false, true);
//end of modifiable zone..................E/764bbc7f-4bd1-4fed-8373-caae6ec85136
    }

    protected StepInformation u1Response2() {
//begin of modifiable zone................T/1eb0d3d0-82e1-45d5-a8ce-9c6b1f0c851d
        alpha[1][4] = pair_inputA_a0P1_bP2[0].duplicate().pow(t1_inv_b[0]);
        alpha[1][5] = pair_aP1_inputB_b0P2[0].duplicate().pow(t2_inv_a[0]);
//end of modifiable zone..................E/1eb0d3d0-82e1-45d5-a8ce-9c6b1f0c851d
//begin of modifiable zone................T/4ae80228-0b35-46ee-9d82-dfd037554469
        return new StepInformation("U1", "U2Response2", false, true);
//end of modifiable zone..................E/4ae80228-0b35-46ee-9d82-dfd037554469
    }

    protected StepInformation u2Response2() {
//begin of modifiable zone................T/d5475988-f370-4f56-ad45-77fec2566c20
        alpha[2][4] = pair_inputA_a0P1_bP2[1].duplicate().pow(t1_inv_b[1]);
        alpha[2][5] = pair_aP1_inputB_b0P2[1].duplicate().pow(t2_inv_a[1]);
//end of modifiable zone..................E/d5475988-f370-4f56-ad45-77fec2566c20
//begin of modifiable zone................T/58ecf630-abd5-4c29-8372-56320a21988f
        return new StepInformation("U2", "TVerify", false, true);
//end of modifiable zone..................E/58ecf630-abd5-4c29-8372-56320a21988f
    }

    protected StepInformation tVerify() {
//begin of modifiable zone(JavaCode)......C/169721e8-f072-4c33-8ced-125da591a911
    	if (!alpha[1][4].isEqual(alpha[2][4]))
    		System.out.println("fail to verify.");
    	
    	if (!alpha[1][5].isEqual(alpha[2][5]))
    		System.out.println("fail to verify.");
    	
    	if (!alpha[2][1].isEqual(alpha[1][1].duplicate().mul(pair_inputA_a0P1_bP2[1])
    			.mul(pair_aP1_inputB_b0P2[1]).mul(pair_a1P1_b1P2)))
    		System.out.println("fail to verify.");
    	
    	Element outsourceResult = alpha[1][1].duplicate().mul(pair_inputA_a0P1_bP2[0])
    			.mul(pair_aP1_inputB_b0P2[0]).mul(pair_a0P1_b0P2);
    	
    	if (!outsourceResult.isEqual(result))
    		System.out.println("Incorrect outsource result.");
    	
    	
//end of modifiable zone(JavaCode)........E/169721e8-f072-4c33-8ced-125da591a911
//begin of modifiable zone(JavaReturned)..C/169721e8-f072-4c33-8ced-125da591a911
    	return new StepInformation("T", "", true, true);
//end of modifiable zone(JavaReturned)....E/169721e8-f072-4c33-8ced-125da591a911
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/2a614142-2ae8-4a73-a264-ca14463172a7
        Ren201504PairingOutsource outsource = new Ren201504PairingOutsource(160, 512);
        
        outsource.setRepeat(200);

        outsource.evaluate();
//end of modifiable zone..................E/2a614142-2ae8-4a73-a264-ca14463172a7
    }

}
