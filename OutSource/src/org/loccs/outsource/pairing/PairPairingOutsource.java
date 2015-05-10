package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import org.loccs.outsource.StepInformation;

public class PairPairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected RandTuple x;

    protected RandTuple y;

    protected RandTuple v;

    protected Element inputA_v1V1;

    protected Element inputB_v2V2;

    protected Element v1V1_v2V1;

    protected Element inputA_V1;

    protected Element inputB_V2;

    protected Element[] alpha = new Element[4];

    protected Element lambdaX1;

    protected Element lambdaY1;

    protected Element lambdaX2;

    protected Element lambdaY2;

    public PairPairingOutsource(int rbits, int qbits) {
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
        x = new RandTuple(pairing);
        y = new RandTuple(pairing);
        v = new RandTuple(pairing);
        return new StepInformation("TP", "TQuery", false, false);
    }

    protected StepInformation tQuery() {
        inputA_v1V1 = inputA.duplicate().add(v.gete1E1());
        inputB_v2V2 = inputB.duplicate().add(v.gete2E2());
        v1V1_v2V1 = v.gete1E1().duplicate().add(v.gete2E1());
        inputA_V1 = inputA.duplicate().add(v.getE1());
        inputB_V2 = inputB.duplicate().add(v.getE2());
        return new StepInformation("T", "U1Response", false, true);
    }

    protected StepInformation u1Response() {
        alpha[1] = pairing.pairing(inputA_v1V1, inputB_v2V2);
        alpha[0] = pairing.pairing(v1V1_v2V1, v.getE2());
        lambdaX1 = pairing.pairing(x.gete1E1(), x.gete2E2());
        lambdaY1 = pairing.pairing(y.gete1E1(), y.gete2E2());
        return new StepInformation("U1", "U2Response", false, true);
    }

    protected StepInformation u2Response() {
        alpha[2] = pairing.pairing(inputA_V1, v.gete2E2());
        alpha[3] = pairing.pairing(v.gete1E1(), inputB_V2);
        lambdaX2 = pairing.pairing(x.gete1E1(), x.gete2E2());
        lambdaY2 = pairing.pairing(y.gete1E1(), y.gete2E2());
        return new StepInformation("U2", "TVerify", false, true);
    }

    protected StepInformation tVerify() {
        Element outsourceResult = alpha[1].duplicate().mul(alpha[2].duplicate().invert())
                .mul(alpha[3].duplicate().invert()).mul(v.getPair().duplicate().invert())
                .mul(alpha[0]);
        
        if (!outsourceResult.isEqual(result))
            System.out.println("Incorrect outsource result.");
        return new StepInformation("T", "", true, true);
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        PairPairingOutsource outsource = new PairPairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
    }

    public class RandTuple {
        protected Element[] elements = new Element[6];

        public RandTuple(Pairing pairing) {


            elements[0] = pairing.getG1().newRandomElement();
            elements[1] = pairing.getG2().newRandomElement();
            BigInteger e1 = randomNumber(order);
            BigInteger e2 = randomNumber(order);
            elements[2] = elements[0].duplicate().mul(e1);
            elements[3] = elements[0].duplicate().mul(e2);
            elements[4] = elements[1].duplicate().mul(e2);
            elements[5] = pairing.pairing(elements[2], elements[4]);
        }

        public Element getE1() {
            return elements[0];
        }

        public Element getE2() {
            return elements[1];
        }

        public Element gete1E1() {
            return elements[2];
        }

        public Element gete2E1() {
            return elements[3];
        }

        public Element gete2E2() {
            return elements[4];
        }

        public Element getPair() {
            return elements[5];
        }

    }

}
