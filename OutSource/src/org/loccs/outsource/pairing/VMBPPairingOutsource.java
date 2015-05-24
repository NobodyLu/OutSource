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
//begin of modifiable zone................T/4ea3b688-0cbb-4f72-846a-e0b1f19d030a
super(rbits, qbits, count);

//end of modifiable zone..................E/4ea3b688-0cbb-4f72-846a-e0b1f19d030a
//begin of modifiable zone(JavaCode)......C/61b77099-7fef-451b-a5cb-8dfde0d9acf9

//end of modifiable zone(JavaCode)........E/61b77099-7fef-451b-a5cb-8dfde0d9acf9
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/81dc4c11-5935-4fc5-b1b7-20e0e137f0dc
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/81dc4c11-5935-4fc5-b1b7-20e0e137f0dc
//begin of modifiable zone................T/a5811c58-dc23-497c-a43d-1cafbb95a10e
        return entities;
//end of modifiable zone..................E/a5811c58-dc23-497c-a43d-1cafbb95a10e
    }

    protected StepInformation step() {
//begin of modifiable zone................T/dd778a06-602c-41a2-9285-eb7050c9914d
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
        
//end of modifiable zone..................E/dd778a06-602c-41a2-9285-eb7050c9914d
//begin of modifiable zone................T/3ad687fb-b7ea-452b-952b-e5b26a38f6ec
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/3ad687fb-b7ea-452b-952b-e5b26a38f6ec
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/5e062a9a-7271-46c0-b2cd-ba7212578b3a
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
        a02 = new RandTuple(P[1], P[2]);
        a13 = new RandTuple(P[1], P[2]);
//end of modifiable zone..................E/5e062a9a-7271-46c0-b2cd-ba7212578b3a
//begin of modifiable zone................T/e11a81ba-f724-45d8-919d-f1d57138345e
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/e11a81ba-f724-45d8-919d-f1d57138345e
    }

    protected StepInformation tQuery() {
//begin of modifiable zone................T/85208faa-da3e-4756-9de5-764cb5c12098
        inputA_a0P1 = new Element[pairingCount];
        inputB_b0P2 = new Element[pairingCount];
        inputA_a0P1_a1P1 = new Element[pairingCount];
        inputB_b0P2_b1P2 = new Element[pairingCount];
        
        sigma_inputA_a0P1_a3P1 = a13.getA2P1().duplicate();
        sigma_inputB_b0P2_b3P2 = a13.getB2P2().duplicate();
        sigma_inputA_a0P1_a2P1 = a02.getA2P1().duplicate();
        sigma_inputB_b0P2_b2P2 = a02.getB2P2().duplicate();
        
        Element temp1 = a13.getA0P1().duplicate().sub(a02.getA0P1());
        Element temp2 = a13.getB0P2().duplicate().sub(a02.getB0P2());
        
        for (int i = 0; i < pairingCount; i++) {
            inputA_a0P1[i] = inputA[i].duplicate().sub(a02.getA0P1());
            sigma_inputA_a0P1_a3P1.add(inputA_a0P1[i]);
            
            inputB_b0P2[i] = inputB[i].duplicate().sub(a02.getB0P2());
            sigma_inputB_b0P2_b3P2.add(inputB_b0P2[i]);
            
            sigma_inputA_a0P1_a2P1.add(inputA_a0P1[i]);
            sigma_inputB_b0P2_b2P2.add(inputB_b0P2[i]);
            
            inputA_a0P1_a1P1[i] = inputA[i].duplicate().sub(a02.getA0P1()).add(a13.getA0P1());
            inputB_b0P2_b1P2[i] = inputB[i].duplicate().sub(a02.getB0P2()).add(a13.getB0P2());
        }
//end of modifiable zone..................E/85208faa-da3e-4756-9de5-764cb5c12098
//begin of modifiable zone................T/cf8791df-73e1-4eb9-9049-d3adeb3aaa58
        return new StepInformation("T", "U1Response", false, true);
//end of modifiable zone..................E/cf8791df-73e1-4eb9-9049-d3adeb3aaa58
    }

    protected StepInformation u1Response() {
//begin of modifiable zone................T/8a878cc2-6a18-4f1b-9bb5-b0ab1afe0043
        alpha1[1] = new Element[pairingCount + 1];
        for (int i = 1; i <= pairingCount; i++) 
            alpha1[1][i] = pairing.pairing(inputA_a0P1[i - 1], inputB_b0P2[i - 1]);
        
        alpha[1] = new Element[6];
        alpha[1][2] = pairing.pairing(sigma_inputA_a0P1_a3P1, a13.getB0P2());
        alpha[1][3] = pairing.pairing(a13.getA0P1(), sigma_inputB_b0P2_b3P2);
//end of modifiable zone..................E/8a878cc2-6a18-4f1b-9bb5-b0ab1afe0043
//begin of modifiable zone................T/801ea7b4-3e43-450c-addc-083032635a19
        return new StepInformation("U1", "U2Response", false, true);
//end of modifiable zone..................E/801ea7b4-3e43-450c-addc-083032635a19
    }

    protected StepInformation u2Response() {
//begin of modifiable zone................T/f1c4424b-7271-4d7f-8aaf-79293d2258b2
        alpha1[2] = new Element[pairingCount + 1];
        for (int i = 1; i <= pairingCount; i++)
            alpha1[2][i] = pairing.pairing(inputA_a0P1_a1P1[i - 1], inputB_b0P2_b1P2[i - 1]);
        
        alpha[2] = new Element[6];
        alpha[2][2] = pairing.pairing(sigma_inputA_a0P1_a2P1, a02.getB0P2());
        alpha[2][3] = pairing.pairing(a02.getA0P1(), sigma_inputB_b0P2_b2P2);
//end of modifiable zone..................E/f1c4424b-7271-4d7f-8aaf-79293d2258b2
//begin of modifiable zone................T/10aa4e48-b598-4e49-9d3c-b8b2a88f4a9d
        return new StepInformation("U2", "TQuery2", false, true);
//end of modifiable zone..................E/10aa4e48-b598-4e49-9d3c-b8b2a88f4a9d
    }

    protected StepInformation tQuery2() {
//begin of modifiable zone................T/29c89f08-e8fa-4dc7-9dbf-ddfa9bc853fd
        alpha[1][1] = alpha1[1][1].duplicate();
        alpha[2][1] = alpha1[2][1].duplicate();
        
        for (int i = 2; i <= pairingCount; i++) {
            alpha[1][1].mul(alpha1[1][i]);
            alpha[2][1].mul(alpha1[2][i]);
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
//end of modifiable zone..................E/29c89f08-e8fa-4dc7-9dbf-ddfa9bc853fd
//begin of modifiable zone................T/b58f7653-0fb2-4fb1-a114-4d9c1212d4d1
        return new StepInformation("T", "U1Response2", false, true);
//end of modifiable zone..................E/b58f7653-0fb2-4fb1-a114-4d9c1212d4d1
    }

    protected StepInformation u1Response2() {
//begin of modifiable zone................T/c9124610-65fd-4497-9818-3a90d14100fd
        alpha[1][4] = alpha22_pairing.duplicate().pow(t1_inv_b[0]);
        alpha[1][5] = alpha23_pairing.duplicate().pow(t2_inv_a[0]);
//end of modifiable zone..................E/c9124610-65fd-4497-9818-3a90d14100fd
//begin of modifiable zone................T/7e4a42fd-83ec-4fd6-b0c5-0f12e96ef98f
        return new StepInformation("U1", "U2Response2", false, true);
//end of modifiable zone..................E/7e4a42fd-83ec-4fd6-b0c5-0f12e96ef98f
    }

    protected StepInformation u2Response2() {
//begin of modifiable zone................T/45ea8cbb-c948-4885-aa19-c3d366866723
        alpha[2][4] = alpha12_pairing.duplicate().pow(t1_inv_b[1]);
        alpha[2][5] = alpha13_pairing.duplicate().pow(t2_inv_a[1]);
//end of modifiable zone..................E/45ea8cbb-c948-4885-aa19-c3d366866723
//begin of modifiable zone................T/d9aeac7b-2575-4c7f-b810-113feb74270b
        return new StepInformation("U2", "TVerify", false, true);
//end of modifiable zone..................E/d9aeac7b-2575-4c7f-b810-113feb74270b
    }

    protected StepInformation tVerify() {
//begin of modifiable zone................T/39c4ad72-e8c9-4856-b79a-d01f7c35dfdc
        if (!alpha[1][4].isEqual(alpha[2][4]))
            System.out.println("fail to verify.");
        
        if (!alpha[1][5].isEqual(alpha[2][5]))
            System.out.println("fail to verify.");
        
        if (!alpha[2][1].isEqual(alpha[1][1].duplicate()
        		.mul(alpha[1][2]).mul(alpha[1][3]).mul(a13.getPairingA0P1B0P2n())        		
        		))
        	System.out.println("fail to verify.");

        Element outsourceResult = alpha[1][1].duplicate().mul(alpha[2][2])
                .mul(alpha[2][3]).mul(a02.getPairingA0P1B0P2n());
        
        if (!outsourceResult.isEqual(result))
            System.out.println("Incorrect outsource result.");        
        
//end of modifiable zone..................E/39c4ad72-e8c9-4856-b79a-d01f7c35dfdc
//begin of modifiable zone................T/84979a04-e290-44aa-bff4-57748b247717
        return new StepInformation("T", "", true, true);
//end of modifiable zone..................E/84979a04-e290-44aa-bff4-57748b247717
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/b7a8bba1-6461-4d92-b64b-f6db73a13d70
        VMBPPairingOutsource outsource = new VMBPPairingOutsource(160, 512, 5);
        
        outsource.setRepeat(1);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/b7a8bba1-6461-4d92-b64b-f6db73a13d70
    }

    public class RandTuple {
        protected BigInteger[] a = new BigInteger[2];

        protected BigInteger[] b = new BigInteger[2];

        protected BigInteger[] n = new BigInteger[2];

        protected Element[] e = new Element[7];

        public RandTuple(Element P1, Element P2) {
//begin of modifiable zone(JavaSuper).....C/f01294b4-01fc-4a06-a207-d7f1edc31f71

//end of modifiable zone(JavaSuper).......E/f01294b4-01fc-4a06-a207-d7f1edc31f71
//begin of modifiable zone................T/a335d520-986c-4499-b5ed-043024e06b72
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
//end of modifiable zone..................E/a335d520-986c-4499-b5ed-043024e06b72
        }

        public BigInteger getInvA0() {
//begin of modifiable zone(JavaCode)......C/66081cce-2d48-489d-b803-7e23c3b4f7f7

//end of modifiable zone(JavaCode)........E/66081cce-2d48-489d-b803-7e23c3b4f7f7
//begin of modifiable zone................T/b47410ed-81ad-43df-ab73-bb7a4cd63b34
            return n[0];
//end of modifiable zone..................E/b47410ed-81ad-43df-ab73-bb7a4cd63b34
        }

        public BigInteger getInvB0() {
//begin of modifiable zone(JavaCode)......C/bf93e25b-98b3-496b-8ce5-d144d142b592

//end of modifiable zone(JavaCode)........E/bf93e25b-98b3-496b-8ce5-d144d142b592
//begin of modifiable zone................T/c27e9d30-1b63-46cc-9082-4e45b7939862
            return n[1];
//end of modifiable zone..................E/c27e9d30-1b63-46cc-9082-4e45b7939862
        }

        public Element getA0P1() {
//begin of modifiable zone(JavaCode)......C/92ec9732-c52e-4387-bb86-792662526edb

//end of modifiable zone(JavaCode)........E/92ec9732-c52e-4387-bb86-792662526edb
//begin of modifiable zone................T/525f0c67-7d2e-4c8a-80d8-c4205d69e7a3
            return e[0];
//end of modifiable zone..................E/525f0c67-7d2e-4c8a-80d8-c4205d69e7a3
        }

        public Element getB0P2() {
//begin of modifiable zone(JavaCode)......C/6a0f3b02-41cd-4d69-8684-4d8eaf9cd55f

//end of modifiable zone(JavaCode)........E/6a0f3b02-41cd-4d69-8684-4d8eaf9cd55f
//begin of modifiable zone................T/22d4b057-7892-4d25-ae06-89c33ffbe239
            return e[1];
//end of modifiable zone..................E/22d4b057-7892-4d25-ae06-89c33ffbe239
        }

        public Element getA2P1() {
//begin of modifiable zone(JavaCode)......C/c9b99157-57fa-4bbc-be49-b9cd55b7796d

//end of modifiable zone(JavaCode)........E/c9b99157-57fa-4bbc-be49-b9cd55b7796d
//begin of modifiable zone................T/5ff315dd-1fc3-44ee-9c10-a3cabbe9b592
            return e[2];
//end of modifiable zone..................E/5ff315dd-1fc3-44ee-9c10-a3cabbe9b592
        }

        public Element getB2P2() {
//begin of modifiable zone(JavaCode)......C/4d57d9ad-8b66-4394-ab2e-f3e5e9b17bc8

//end of modifiable zone(JavaCode)........E/4d57d9ad-8b66-4394-ab2e-f3e5e9b17bc8
//begin of modifiable zone................T/cfac57f5-92fd-4562-9e5a-5debb0ab7e98
            return e[3];
//end of modifiable zone..................E/cfac57f5-92fd-4562-9e5a-5debb0ab7e98
        }

        public Element getPairingA0P1B0P2n() {
//begin of modifiable zone(JavaCode)......C/f43a45ca-133a-46f6-8d24-05d87f625209

//end of modifiable zone(JavaCode)........E/f43a45ca-133a-46f6-8d24-05d87f625209
//begin of modifiable zone................T/484363a8-3259-4620-8dd4-70c09a408cce
            return e[4];
//end of modifiable zone..................E/484363a8-3259-4620-8dd4-70c09a408cce
        }

        public Element getInvPairingA2P1B0P2() {
//begin of modifiable zone(JavaCode)......C/06ac9b13-c9fc-445b-8ce7-8d8e57759627

//end of modifiable zone(JavaCode)........E/06ac9b13-c9fc-445b-8ce7-8d8e57759627
//begin of modifiable zone................T/b1d2e05f-b1fd-4558-a7b7-b3f4b106faa9
            return e[5];
//end of modifiable zone..................E/b1d2e05f-b1fd-4558-a7b7-b3f4b106faa9
        }

        public Element getInvPairingA0P1B2P2() {
//begin of modifiable zone(JavaCode)......C/daf839ea-b876-466d-9705-132b041ccf61

//end of modifiable zone(JavaCode)........E/daf839ea-b876-466d-9705-132b041ccf61
//begin of modifiable zone................T/aaee2436-66a5-42c6-a12d-73e17cb5d469
            return e[6];
//end of modifiable zone..................E/aaee2436-66a5-42c6-a12d-73e17cb5d469
        }

    }

}
