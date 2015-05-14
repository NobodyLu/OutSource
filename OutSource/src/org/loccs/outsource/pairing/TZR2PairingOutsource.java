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
//begin of modifiable zone................T/88e38269-f123-49ab-970f-cc33038fe2cc
    	super(rbits, qbits);

//end of modifiable zone..................E/88e38269-f123-49ab-970f-cc33038fe2cc
//begin of modifiable zone(JavaCode)......C/c9f44001-c78a-4f6a-92ca-1febca5536aa

//end of modifiable zone(JavaCode)........E/c9f44001-c78a-4f6a-92ca-1febca5536aa
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/2d6a6c72-eda8-4ec8-b5c2-97f4ee90839d
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/2d6a6c72-eda8-4ec8-b5c2-97f4ee90839d
//begin of modifiable zone................T/a64edc45-558f-420a-8035-dea281ce4638
        return entities;
//end of modifiable zone..................E/a64edc45-558f-420a-8035-dea281ce4638
    }

    protected StepInformation step() {
//begin of modifiable zone................T/f0a2b8a8-bb44-43bc-b86a-d1e79afbb78a
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
//end of modifiable zone..................E/f0a2b8a8-bb44-43bc-b86a-d1e79afbb78a
//begin of modifiable zone................T/ff013592-eb61-4e15-bd4a-b5ebcee37194
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/ff013592-eb61-4e15-bd4a-b5ebcee37194
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/d0c69955-b019-4694-a602-fc3e45b4749a
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
        x = new RandTuple(P[1], P[2]);
        x_prime = new RandTuple(P[1], P[2]);
//end of modifiable zone..................E/d0c69955-b019-4694-a602-fc3e45b4749a
//begin of modifiable zone................T/c8b0eb9f-2dc5-40cb-98a4-4e86b93e6336
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/c8b0eb9f-2dc5-40cb-98a4-4e86b93e6336
    }

    protected StepInformation tQuery() {
//begin of modifiable zone................T/9ae0eabf-70d6-475e-afef-df27412ad5e6
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
//end of modifiable zone..................E/9ae0eabf-70d6-475e-afef-df27412ad5e6
//begin of modifiable zone................T/c51578ff-51a9-4b2d-937d-efa863969120
        return new StepInformation("T", "U1Response", false, true);
//end of modifiable zone..................E/c51578ff-51a9-4b2d-937d-efa863969120
    }

    protected StepInformation u1Response() {
//begin of modifiable zone................T/f3081498-2319-4234-b331-5d861085e43e
        alpha[1] = pairing.pairing(inputA_x1P1, inputB_inv_x1_x2_P2);
        alpha[2] = pairing.pairing(tA_prime_x1_inv_x2_x3_P1, neg_prime_inv_x1_x2_P2);
        alpha[3] = pairing.pairing(neg_prime_x1P1, inputB_prime_inv_x1_x4_P2);
//end of modifiable zone..................E/f3081498-2319-4234-b331-5d861085e43e
//begin of modifiable zone................T/b3ed868d-a4da-4512-8ab4-4c0cad3a3f7d
        return new StepInformation("U1", "U2Response", false, true);
//end of modifiable zone..................E/b3ed868d-a4da-4512-8ab4-4c0cad3a3f7d
    }

    protected StepInformation u2Response() {
//begin of modifiable zone................T/2662e0ea-a9af-4fe8-a504-f86d58007adb
        alpha_prime[1] = pairing.pairing(tA_prime_x1P1, inputB_prime_inv_x1_x2_P2);
        alpha_prime[2] = pairing.pairing(inputA_x1_inv_x2_x3_P1, neg_inv_x1_x2_P2);
        alpha_prime[3] = pairing.pairing(neg_x1P1, inputB_inv_x1_x4_P2);
//end of modifiable zone..................E/2662e0ea-a9af-4fe8-a504-f86d58007adb
//begin of modifiable zone................T/13fde9c8-16f6-4086-9775-c58427293929
        return new StepInformation("U2", "TVerify", false, true);
//end of modifiable zone..................E/13fde9c8-16f6-4086-9775-c58427293929
    }

    protected StepInformation tVerify() {
//begin of modifiable zone................T/443795d9-425f-4461-ba4c-69c356b77ab7
        Element o = alpha[1].duplicate().mul(alpha_prime[2]).mul(alpha_prime[3])
                .mul(x.getPair());
        Element o_prime = alpha_prime[1].duplicate().mul(alpha[2]).mul(alpha[3])
                .mul(x_prime.getPair());
        
        if (!o.duplicate().pow(t).equals(o_prime))
            System.out.println("fail to verify.");
        
        if (!o.isEqual(result))
            System.out.println("Incorrect outsource result.");
//end of modifiable zone..................E/443795d9-425f-4461-ba4c-69c356b77ab7
//begin of modifiable zone................T/a2b24af1-9fd2-424f-9daf-c4c01b814459
        return new StepInformation("T", "", true, true);
//end of modifiable zone..................E/a2b24af1-9fd2-424f-9daf-c4c01b814459
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/b95b79b6-2620-472c-b391-c760aa2483f2
        TZR2PairingOutsource outsource = new TZR2PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/b95b79b6-2620-472c-b391-c760aa2483f2
    }

    public class RandTuple {
        protected BigInteger[] x = new BigInteger[5];

        protected Element[] e = new Element[5];

        public RandTuple(Element P1, Element P2) {
//begin of modifiable zone(JavaSuper).....C/a5cdce40-4dbb-406d-a5ba-3eb4eaa839a6

//end of modifiable zone(JavaSuper).......E/a5cdce40-4dbb-406d-a5ba-3eb4eaa839a6
//begin of modifiable zone................T/4487870f-6e11-4225-928f-7a5d28a23318
            for (int i = 1; i <= 4; i++) 
                x[i] = randomNumber(order);
            
            e[0] = P1.duplicate().mul(x[1]);
            e[1] = P1.duplicate().mul(x[1]).mul(x[2].modInverse(order)).mul(x[3]);
            e[2] = P2.duplicate().mul(x[1].modInverse(order)).mul(x[2]);
            e[3] = P2.duplicate().mul(x[1].modInverse(order)).mul(x[4]);
            e[4] = pairing.pairing(P1, P2).pow(x[3].add(x[4]).subtract(x[2]));
//end of modifiable zone..................E/4487870f-6e11-4225-928f-7a5d28a23318
        }

        public Element getX1P1() {
//begin of modifiable zone(JavaCode)......C/0a56f6c4-43e5-4225-8664-96a398f9106d

//end of modifiable zone(JavaCode)........E/0a56f6c4-43e5-4225-8664-96a398f9106d
//begin of modifiable zone................T/4181622d-b46e-4276-9d9a-cec0f17ec4c1
            return e[0];
//end of modifiable zone..................E/4181622d-b46e-4276-9d9a-cec0f17ec4c1
        }

        public Element getX1InvX2X3P1() {
//begin of modifiable zone(JavaCode)......C/7725fcae-bc06-4acb-b763-5ec79965fd77

//end of modifiable zone(JavaCode)........E/7725fcae-bc06-4acb-b763-5ec79965fd77
//begin of modifiable zone................T/d6a1d1f3-cde0-42ee-8a6c-648aa7518848
            return e[1];
//end of modifiable zone..................E/d6a1d1f3-cde0-42ee-8a6c-648aa7518848
        }

        public Element getInvX1X2P2() {
//begin of modifiable zone(JavaCode)......C/2a8e9b63-24f2-410b-bf1d-742299756688

//end of modifiable zone(JavaCode)........E/2a8e9b63-24f2-410b-bf1d-742299756688
//begin of modifiable zone................T/93cff7ec-f8fe-4942-9697-71987418c768
            return e[2];
//end of modifiable zone..................E/93cff7ec-f8fe-4942-9697-71987418c768
        }

        public Element getInvX1X4P2() {
//begin of modifiable zone(JavaCode)......C/22212cf7-f506-44c2-9a83-fb4e842e84db

//end of modifiable zone(JavaCode)........E/22212cf7-f506-44c2-9a83-fb4e842e84db
//begin of modifiable zone................T/883240d5-2d82-4bb1-9946-54b1507afd03
            return e[3];
//end of modifiable zone..................E/883240d5-2d82-4bb1-9946-54b1507afd03
        }

        public Element getPair() {
//begin of modifiable zone(JavaCode)......C/1a0e9920-7bc3-4c45-b577-0b58def8ccac

//end of modifiable zone(JavaCode)........E/1a0e9920-7bc3-4c45-b577-0b58def8ccac
//begin of modifiable zone................T/625fcfad-24d0-4b31-af23-949b25f07672
            return e[4];
//end of modifiable zone..................E/625fcfad-24d0-4b31-af23-949b25f07672
        }

    }

}
