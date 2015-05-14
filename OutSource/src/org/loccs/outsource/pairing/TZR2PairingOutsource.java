package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class TZR2PairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected Element[] P = new Element[3];

    protected RandTuple x;

    protected RandTuple x_prime;

    protected BigInteger t = new BigInteger("3");

    protected Element inputA_x1P1;

    protected Element inputB_inv_x1_x2_P2;

    protected Element tA_prime_x1_inv_x2_x3_P1;

    protected Element neg_prime_inv_x1_x2_P2;

    protected Element neg_prime_x1P1;

    protected Element inputB_prime_inv_x1_x4_P2;

    protected Element tA_prime_x1P1;

    protected Element inputB_prime_inv_x1_x2_P2;

    protected Element inputA_x1_inv_x2_x3_P1;

    protected Element neg_inv_x1_x2_P2;

    protected Element neg_x1P1;

    protected Element inputB_inv_x1_x4_P2;

    protected Element[] alpha = new Element[4];

    protected Element[] alpha_prime = new Element[4];

    public TZR2PairingOutsource(int rbits, int qbits) {
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
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
        x = new RandTuple(P[1], P[2]);
        x_prime = new RandTuple(P[1], P[2]);
        return new StepInformation("TP", "TQuery", false, false);
    }

    protected StepInformation tQuery() {
        inputA_x1P1 = inputA.duplicate().add(x.getX1P1());
        inputB_inv_x1_x2_P2 = inputB.duplicate().add(x.getInvX1X2P2());
        tA_prime_x1_inv_x2_x3_P1 = inputA.duplicate().mul(t).add(x_prime.getX1InvX2X3P1());
        neg_prime_inv_x1_x2_P2 = x_prime.getInvX1X2P2().duplicate().negate();
        neg_prime_x1P1 = x_prime.getX1P1().duplicate().negate();
        inputB_prime_inv_x1_x4_P2 = inputB.duplicate().add(x_prime.getInvX1X4P2());
        
        tA_prime_x1P1 = inputA.duplicate().mul(t).add(x_prime.getX1P1());
        inputB_prime_inv_x1_x2_P2 = inputB.duplicate().add(x_prime.getInvX1X2P2());
        inputA_x1_inv_x2_x3_P1 = inputA.duplicate().add(x.getX1InvX2X3P1());
        neg_inv_x1_x2_P2 = x.getInvX1X2P2().duplicate().negate();
        neg_x1P1 = x.getX1P1().duplicate().negate();
        inputB_inv_x1_x4_P2 = inputB.duplicate().add(x.getInvX1X4P2());
        return new StepInformation("T", "U1Response", false, true);
    }

    protected StepInformation u1Response() {
        alpha[1] = pairing.pairing(inputA_x1P1, inputB_inv_x1_x2_P2);
        alpha[2] = pairing.pairing(tA_prime_x1_inv_x2_x3_P1, neg_prime_inv_x1_x2_P2);
        alpha[3] = pairing.pairing(neg_prime_x1P1, inputB_prime_inv_x1_x4_P2);
        return new StepInformation("U1", "U2Response", false, true);
    }

    protected StepInformation u2Response() {
        alpha_prime[1] = pairing.pairing(tA_prime_x1P1, inputB_prime_inv_x1_x2_P2);
        alpha_prime[2] = pairing.pairing(inputA_x1_inv_x2_x3_P1, neg_inv_x1_x2_P2);
        alpha_prime[3] = pairing.pairing(neg_x1P1, inputB_inv_x1_x4_P2);
        return new StepInformation("U2", "TVerify", false, true);
    }

    protected StepInformation tVerify() {
        Element o = alpha[1].duplicate().mul(alpha_prime[2]).mul(alpha_prime[3])
                .mul(x.getPair());
        Element o_prime = alpha_prime[1].duplicate().mul(alpha[2]).mul(alpha[3])
                .mul(x_prime.getPair());
        
        if (!o.duplicate().pow(t).equals(o_prime))
            System.out.println("fail to verify.");
        
        if (!o.isEqual(result))
            System.out.println("Incorrect outsource result.");
        return new StepInformation("T", "", true, true);
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        TZR2PairingOutsource outsource = new TZR2PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
    }

    public class RandTuple {
        protected BigInteger[] x = new BigInteger[5];

        protected Element[] e = new Element[5];

        public RandTuple(Element P1, Element P2) {
            for (int i = 1; i <= 4; i++) 
                x[i] = randomNumber(order);
            
            e[0] = P1.duplicate().mul(x[1]);
            e[1] = P1.duplicate().mul(x[1]).mul(x[2].modInverse(order)).mul(x[3]);
            e[2] = P2.duplicate().mul(x[1].modInverse(order)).mul(x[2]);
            e[3] = P2.duplicate().mul(x[1].modInverse(order)).mul(x[4]);
            e[4] = pairing.pairing(P1, P2).pow(x[3].add(x[4]).subtract(x[2]));
        }

        public Element getX1P1() {
            return e[0];
        }

        public Element getX1InvX2X3P1() {
            return e[1];
        }

        public Element getInvX1X2P2() {
            return e[2];
        }

        public Element getInvX1X4P2() {
            return e[3];
        }

        public Element getPair() {
            return e[4];
        }

    }

}
