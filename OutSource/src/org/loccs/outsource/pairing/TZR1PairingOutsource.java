package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class TZR1PairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected BigInteger[] x = new BigInteger[9];

    protected Element[] P = new Element[3];

    protected Element x1P1;

    protected Element x3P1;

    protected Element x1_inv_x2_x5_P1;

    protected Element x7P1;

    protected Element inv_x1_x2_P2;

    protected Element x4P2;

    protected Element inv_x1_x6_P2;

    protected Element x8P2;

    protected Element pair_P1_P2_x7x8;

    protected Element pair_P1_P2_x5_x6_x2;

    protected Element inputA_x1P1;

    protected Element inputB_inv_x1_x2_P2;

    protected Element[] alpha = new Element[3];

    protected Element inputA_x1_inv_x2_x5_P1;

    protected Element neg_inv_x1_x2_P2;

    protected Element neg_x1P1;

    protected Element inputB_inv_x1_x6_P2;

    protected Element[] alpha_prime = new Element[5];

    public TZR1PairingOutsource(int rbits, int qbits) {
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
        
        if (nextStep.equals("TVerify"))
            return tVerify();
        return new StepInformation("TP", "", true, false);
    }

    protected StepInformation rand() {
        for (int i = 1; i <= 8; i++)
            x[i] = randomNumber(order);
        
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
        
        x1P1 = P[1].duplicate().mul(x[1]);
        x3P1 = P[1].duplicate().mul(x[3]);
        x1_inv_x2_x5_P1 = P[1].duplicate().mul(x[1]).mul(x[2].modInverse(order))
                .mul(x[5]);
        x7P1 = P[1].duplicate().mul(x[7]);
        inv_x1_x2_P2 = P[2].duplicate().mul(x[1].modInverse(order)).mul(x[2]);
        x4P2 = P[2].duplicate().mul(x[4]);
        inv_x1_x6_P2 = P[2].duplicate().mul(x[1].modInverse(order)).mul(x[6]);
        x8P2 = P[2].duplicate().mul(x[8]);
        Element pair = pairing.pairing(P[1], P[2]);
        pair_P1_P2_x7x8 = pair.duplicate().pow(x[7].multiply(x[8]));
        pair_P1_P2_x5_x6_x2 = pair.duplicate().pow(x[5].add(x[6]).subtract(x[2]));
        return new StepInformation("TP", "TQuery", false, false);
    }

    protected StepInformation tQuery() {
        inputA_x1P1 = inputA.duplicate().add(x1P1);
        inputB_inv_x1_x2_P2 = inputB.duplicate().add(inv_x1_x2_P2);
        
        inputA_x1_inv_x2_x5_P1 = inputA.duplicate().add(x1_inv_x2_x5_P1);
        neg_inv_x1_x2_P2 = inv_x1_x2_P2.duplicate().negate();
        neg_x1P1 = x1P1.duplicate().negate();
        inputB_inv_x1_x6_P2 = inputB.duplicate().add(inv_x1_x6_P2);
        return new StepInformation("T", "U1Response", false, true);
    }

    protected StepInformation u1Response() {
        alpha[1] = pairing.pairing(inputA_x1P1, inputB_inv_x1_x2_P2);
        alpha[2] = pairing.pairing(x3P1, x4P2);
        return new StepInformation("U1", "U2Response", false, true);
    }

    protected StepInformation u2Response() {
        alpha_prime[1] = pairing.pairing(inputA_x1_inv_x2_x5_P1, neg_inv_x1_x2_P2);
        alpha_prime[2] = pairing.pairing(neg_x1P1, inputB_inv_x1_x6_P2);
        alpha_prime[3] = pairing.pairing(x3P1, x4P2);
        alpha_prime[4] = pairing.pairing(x7P1, x8P2);
        return new StepInformation("U2", "TVerify", false, true);
    }

    protected StepInformation tVerify() {
        if (!alpha[2].isEqual(alpha_prime[3]))
            System.out.println("fail to verify.");
        
        if (!pair_P1_P2_x7x8.equals(alpha_prime[4]))
            System.out.println("fail to verify.");
        
        Element outsourceResult =  alpha[1].duplicate()
                .mul(alpha_prime[1]).mul(alpha_prime[2])               
                .mul(pair_P1_P2_x5_x6_x2);
        
        if (!outsourceResult.isEqual(result))
            System.out.println("Incorrect outsource result.");
        return new StepInformation("T", "", true, true);
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        TZR1PairingOutsource outsource = new TZR1PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
    }

}
