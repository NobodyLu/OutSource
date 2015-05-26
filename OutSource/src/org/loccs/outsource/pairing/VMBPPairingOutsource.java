package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class VMBPPairingOutsource extends MultipleSymmetricPrimeOrderPairingOutsource {
    protected Element[] P = new Element[3];

    protected RandTuple a02;

    protected RandTuple a13;

    protected Element[] inputA_a0P1 = new Element[1];

    protected Element[] inputB_b0P2 = new Element[1];

    protected Element sigma_inputA_a0P1_a3P1;

    protected Element sigma_inputB_b0P2_b3P2;

    protected Element[] inputA_a0P1_a1P1 = new Element[1];

    protected Element[] inputB_b0P2_b1P2 = new Element[1];

    protected Element sigma_inputA_a0P1_a2P1;

    protected Element sigma_inputB_b0P2_b2P2;

    protected Element[][] alpha1 = new Element[3][];

    protected Element[][] alpha = new Element[3][];

    protected Element alpha12_pairing;

    protected Element alpha13_pairing;

    protected Element alpha22_pairing;

    protected Element alpha23_pairing;

    protected BigInteger[] t = new BigInteger[3];

    protected BigInteger[] t1_inv_b = new BigInteger[2];

    protected BigInteger[] t2_inv_a = new BigInteger[2];

    public VMBPPairingOutsource(int rbits, int qbits, int count) {
    	super(rbits, qbits, count);

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
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
        a02 = new RandTuple(P[1], P[2]);
        a13 = new RandTuple(P[1], P[2]);
        return new StepInformation("TP", "TQuery", false, false);
    }

    protected StepInformation tQuery() {
        inputA_a0P1 = new Element[pairingCount];
        inputB_b0P2 = new Element[pairingCount];
        inputA_a0P1_a1P1 = new Element[pairingCount];
        inputB_b0P2_b1P2 = new Element[pairingCount];
        
        Element temp1 = a13.getA0P1().duplicate().sub(a02.getA0P1());
        Element temp2 = a13.getB0P2().duplicate().sub(a02.getB0P2());
        
        Element sum1 = pairing.getG1().newZeroElement();
        Element sum2 = pairing.getG1().newZeroElement();
        
        for (int i = 0; i < pairingCount; i++) {
            inputA_a0P1[i] = inputA[i].duplicate().sub(a02.getA0P1());
            sum1.add(inputA_a0P1[i]);
            
            inputB_b0P2[i] = inputB[i].duplicate().sub(a02.getB0P2());
            sum2.add(inputB_b0P2[i]);

            inputA_a0P1_a1P1[i] = inputA[i].duplicate().add(temp1);
            inputB_b0P2_b1P2[i] = inputB[i].duplicate().add(temp2);
        }
        
        sigma_inputA_a0P1_a3P1 = sum1.duplicate().add(a13.getA2P1());
        sigma_inputB_b0P2_b3P2 = sum2.duplicate().add(a13.getB2P2());
        sigma_inputA_a0P1_a2P1 = sum1.duplicate().add(a02.getA2P1());
        sigma_inputB_b0P2_b2P2 = sum2.duplicate().add(a02.getB2P2());
        
        return new StepInformation("T", "U1Response", false, true);
    }

    protected StepInformation u1Response() {
        alpha1[1] = new Element[pairingCount + 1];
        for (int i = 1; i <= pairingCount; i++) 
            alpha1[1][i] = pairing.pairing(inputA_a0P1[i - 1], inputB_b0P2[i - 1]);
        
        alpha[1] = new Element[6];
        alpha[1][2] = pairing.pairing(sigma_inputA_a0P1_a3P1, a13.getB0P2());
        alpha[1][3] = pairing.pairing(a13.getA0P1(), sigma_inputB_b0P2_b3P2);
        return new StepInformation("U1", "U2Response", false, true);
    }

    protected StepInformation u2Response() {
        alpha1[2] = new Element[pairingCount + 1];
        for (int i = 1; i <= pairingCount; i++)
            alpha1[2][i] = pairing.pairing(inputA_a0P1_a1P1[i - 1], inputB_b0P2_b1P2[i - 1]);
        
        alpha[2] = new Element[6];
        alpha[2][2] = pairing.pairing(sigma_inputA_a0P1_a2P1, a02.getB0P2());
        alpha[2][3] = pairing.pairing(a02.getA0P1(), sigma_inputB_b0P2_b2P2);
        return new StepInformation("U2", "TQuery2", false, true);
    }

    protected StepInformation tQuery2() {
        alpha[1][1] = alpha1[1][1].duplicate();
        alpha[2][1] = alpha1[2][1].duplicate();
        
        for (int i = 2; i <= pairingCount; i++) {
            alpha[1][1] = alpha[1][1].mul(alpha1[1][i]);
            alpha[2][1] = alpha[2][1].mul(alpha1[2][i]);
        }
        
        alpha12_pairing = alpha[1][2].duplicate().mul(a13.getInvPairingA2P1B0P2());
        alpha13_pairing = alpha[1][3].duplicate().mul(a13.getInvPairingA0P1B2P2());
        alpha22_pairing = alpha[2][2].duplicate().mul(a02.getInvPairingA2P1B0P2());
        alpha23_pairing = alpha[2][3].duplicate().mul(a02.getInvPairingA0P1B2P2());
        
        for (int i = 1; i <= 2; i++)
            t[i] = randomNumber(order);
        
        t1_inv_b[0] = t[1].multiply(a02.getInvB0());
        t2_inv_a[0] = t[2].multiply(a02.getInvA0());
        t1_inv_b[1] = t[1].multiply(a13.getInvB0());
        t2_inv_a[1] = t[2].multiply(a13.getInvA0());
        return new StepInformation("T", "U1Response2", false, true);
    }

    protected StepInformation u1Response2() {
        alpha[1][4] = alpha22_pairing.duplicate().pow(t1_inv_b[0]);
        alpha[1][5] = alpha23_pairing.duplicate().pow(t2_inv_a[0]);
        return new StepInformation("U1", "U2Response2", false, true);
    }

    protected StepInformation u2Response2() {
        alpha[2][4] = alpha12_pairing.duplicate().pow(t1_inv_b[1]);
        alpha[2][5] = alpha13_pairing.duplicate().pow(t2_inv_a[1]);
        return new StepInformation("U2", "TVerify", false, true);
    }

    protected StepInformation tVerify() {
        if (!alpha[1][4].isEqual(alpha[2][4]))
            System.out.println("fail to verify.");
        
        if (!alpha[1][5].isEqual(alpha[2][5]))
            System.out.println("fail to verify.");
        
        if (!alpha[2][1].isEqual(alpha[1][1].duplicate()
                .mul(alpha12_pairing).mul(alpha13_pairing).mul(a13.getPairingA0P1B0P2n())                
                ))
            System.out.println("fail to verify.");
        
        Element outsourceResult = alpha[1][1].duplicate().mul(alpha22_pairing)
                .mul(alpha23_pairing).mul(a02.getPairingA0P1B0P2n());
        
        if (!outsourceResult.isEqual(result))
            System.out.println("Incorrect outsource result.");
        return new StepInformation("T", "", true, true);
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        VMBPPairingOutsource outsource = new VMBPPairingOutsource(160, 512, 5);
        
        outsource.setRepeat(100);
        
        //outsource.direct();
        
        outsource.evaluate();
    }

    public class RandTuple {
        protected BigInteger[] a = new BigInteger[2];

        protected BigInteger[] b = new BigInteger[2];

        protected BigInteger[] n = new BigInteger[2];

        protected Element[] e = new Element[7];

        public RandTuple(Element P1, Element P2) {
            for (int i = 0; i < 2; i++) {
                a[i] = randomNumber(order);
                b[i] = randomNumber(order);
            }
            n[0] = a[0].modInverse(order);
            n[1] = b[0].modInverse(order);
            e[0] = P[1].duplicate().mul(a[0]);
            e[1] = P[2].duplicate().mul(b[0]);
            e[2] = P[1].duplicate().mul(a[1]);
            e[3] = P[2].duplicate().mul(b[1]);
            e[4] = pairing.pairing(e[0], e[1]).pow(BigInteger.valueOf(pairingCount));
            e[5] = pairing.pairing(e[2], e[1]).invert();
            e[6] = pairing.pairing(e[0], e[3]).invert();
        }

        public BigInteger getInvA0() {
            return n[0];
        }

        public BigInteger getInvB0() {
            return n[1];
        }

        public Element getA0P1() {
            return e[0];
        }

        public Element getB0P2() {
            return e[1];
        }

        public Element getA2P1() {
            return e[2];
        }

        public Element getB2P2() {
            return e[3];
        }

        public Element getPairingA0P1B0P2n() {
            return e[4];
        }

        public Element getInvPairingA2P1B0P2() {
            return e[5];
        }

        public Element getInvPairingA0P1B2P2() {
            return e[6];
        }

    }

}
