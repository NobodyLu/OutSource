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
    	super(rbits, qbits);

    }

    protected List<String> getAllEntityNames() {
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
        return entities;
    }

    protected StepInformation step() {
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
        return new StepInformation("TP", "", true, false);
    }

    protected StepInformation rand() {
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
        return new StepInformation("TP", "TQuery", false, false);
    }

    protected StepInformation tQuery() {
        inputA_aP1[0] = inputA.duplicate().sub(aP1[0]);
        inputB_bP2[0] = inputB.duplicate().sub(bP2[0]);
        for (int i = 1; i < 4; i++) {
            inputA_aP1[i] = inputA_aP1[0].duplicate().add(aP1[i]);
            inputB_bP2[i] = inputB_bP2[0].duplicate().add(bP2[i]);
        }
        return new StepInformation("T", "U1Response", false, true);
    }

    protected StepInformation u1Response() {
        alpha[1][1] = pairing.pairing(inputA_aP1[0], inputB_bP2[0]);
        alpha[1][2] = pairing.pairing(inputA_aP1[3], bP2[1]);
        alpha[1][3] = pairing.pairing(aP1[1], inputB_bP2[3]);
        return new StepInformation("U1", "U2Response", false, true);
    }

    protected StepInformation u2Response() {
        alpha[2][1] = pairing.pairing(inputA_aP1[1], inputB_bP2[1]);
        alpha[2][2] = pairing.pairing(inputA_aP1[2], bP2[0]);
        alpha[2][3] = pairing.pairing(aP1[0], inputB_bP2[2]);
        return new StepInformation("U2", "TQuery2", false, true);
    }

    protected StepInformation tQuery2() {
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
        return new StepInformation("T", "U1Response2", false, true);
    }

    protected StepInformation u1Response2() {
        alpha[1][4] = pair_inputA_a0P1_bP2[0].duplicate().pow(t1_inv_b[0]);
        alpha[1][5] = pair_aP1_inputB_b0P2[0].duplicate().pow(t2_inv_a[0]);
        return new StepInformation("U1", "U2Response2", false, true);
    }

    protected StepInformation u2Response2() {
        alpha[2][4] = pair_inputA_a0P1_bP2[1].duplicate().pow(t1_inv_b[1]);
        alpha[2][5] = pair_aP1_inputB_b0P2[1].duplicate().pow(t2_inv_a[1]);
        return new StepInformation("U2", "TVerify", false, true);
    }

    protected StepInformation tVerify() {
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
        return new StepInformation("T", "", true, true);
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        Ren201504PairingOutsource outsource = new Ren201504PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
    }

}
