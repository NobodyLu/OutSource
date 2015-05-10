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
//begin of modifiable zone................T/e33da130-f39c-4bdc-8667-17532b10ea30
super(rbits, qbits);

//end of modifiable zone..................E/e33da130-f39c-4bdc-8667-17532b10ea30
//begin of modifiable zone(JavaCode)......C/0ff88eb1-a16e-4dd7-a5b3-1e90b2bd4d92

//end of modifiable zone(JavaCode)........E/0ff88eb1-a16e-4dd7-a5b3-1e90b2bd4d92
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/ececfed2-9959-49a9-b34b-a16ab7e65679
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/ececfed2-9959-49a9-b34b-a16ab7e65679
//begin of modifiable zone................T/c3fa9ef2-1c71-4a52-b791-96094cc3fa13
        return entities;
//end of modifiable zone..................E/c3fa9ef2-1c71-4a52-b791-96094cc3fa13
    }

    protected StepInformation step() {
//begin of modifiable zone................T/e50e2837-577d-44c6-a10f-14a66aec7480
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
//end of modifiable zone..................E/e50e2837-577d-44c6-a10f-14a66aec7480
//begin of modifiable zone................T/49e1b035-d240-4aec-8acf-24dfc2cb4e6e
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/49e1b035-d240-4aec-8acf-24dfc2cb4e6e
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/a7ad2696-2e04-418c-9b35-c398d69571c6
        x = new RandTuple(pairing);
        y = new RandTuple(pairing);
        v = new RandTuple(pairing);
//end of modifiable zone..................E/a7ad2696-2e04-418c-9b35-c398d69571c6
//begin of modifiable zone................T/b9f0f661-ef35-470c-acff-890183b6767d
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/b9f0f661-ef35-470c-acff-890183b6767d
    }

    protected StepInformation tQuery() {
//begin of modifiable zone................T/6f1b04b0-5009-408c-a4e9-9fcc67c48fe3
        inputA_v1V1 = inputA.duplicate().add(v.gete1E1());
        inputB_v2V2 = inputB.duplicate().add(v.gete2E2());
        v1V1_v2V1 = v.gete1E1().duplicate().add(v.gete2E1());
        inputA_V1 = inputA.duplicate().add(v.getE1());
        inputB_V2 = inputB.duplicate().add(v.getE2());
//end of modifiable zone..................E/6f1b04b0-5009-408c-a4e9-9fcc67c48fe3
//begin of modifiable zone................T/91248aa3-271b-4468-82ee-84dc31c66df3
        return new StepInformation("T", "U1Response", false, true);
//end of modifiable zone..................E/91248aa3-271b-4468-82ee-84dc31c66df3
    }

    protected StepInformation u1Response() {
//begin of modifiable zone................T/8ef5439a-bba1-44c3-af87-8096bde747a0
        alpha[1] = pairing.pairing(inputA_v1V1, inputB_v2V2);
        alpha[0] = pairing.pairing(v1V1_v2V1, v.getE2());
        lambdaX1 = pairing.pairing(x.gete1E1(), x.gete2E2());
        lambdaY1 = pairing.pairing(y.gete1E1(), y.gete2E2());
//end of modifiable zone..................E/8ef5439a-bba1-44c3-af87-8096bde747a0
//begin of modifiable zone................T/b353bd04-2017-4cc4-92a0-7f771ceb1498
        return new StepInformation("U1", "U2Response", false, true);
//end of modifiable zone..................E/b353bd04-2017-4cc4-92a0-7f771ceb1498
    }

    protected StepInformation u2Response() {
//begin of modifiable zone................T/216b3f83-e56d-4d10-85bc-6ff0343db810
        alpha[2] = pairing.pairing(inputA_V1, v.gete2E2());
        alpha[3] = pairing.pairing(v.gete1E1(), inputB_V2);
        lambdaX2 = pairing.pairing(x.gete1E1(), x.gete2E2());
        lambdaY2 = pairing.pairing(y.gete1E1(), y.gete2E2());
//end of modifiable zone..................E/216b3f83-e56d-4d10-85bc-6ff0343db810
//begin of modifiable zone................T/234f29cf-4fbf-4660-8429-75899ba0c053
        return new StepInformation("U2", "TVerify", false, true);
//end of modifiable zone..................E/234f29cf-4fbf-4660-8429-75899ba0c053
    }

    protected StepInformation tVerify() {
//begin of modifiable zone................T/eae926b2-3851-4f4d-90f7-92b66a916d10
        Element outsourceResult = alpha[1].duplicate().mul(alpha[2].duplicate().invert())
                .mul(alpha[3].duplicate().invert()).mul(v.getPair().duplicate().invert())
                .mul(alpha[0]);
        
        if (!outsourceResult.isEqual(result))
            System.out.println("Incorrect outsource result.");
//end of modifiable zone..................E/eae926b2-3851-4f4d-90f7-92b66a916d10
//begin of modifiable zone................T/42ac0ced-8db9-4be0-be11-59be628edb17
        return new StepInformation("T", "", true, true);
//end of modifiable zone..................E/42ac0ced-8db9-4be0-be11-59be628edb17
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/391161a9-9371-4493-8329-dec9d99d2285
        PairPairingOutsource outsource = new PairPairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/391161a9-9371-4493-8329-dec9d99d2285
    }

    public class RandTuple {
        protected Element[] elements = new Element[6];

        public RandTuple(Pairing pairing) {
//begin of modifiable zone................T/f508625a-679e-4436-b1dd-372ad95f6b9d


//end of modifiable zone..................E/f508625a-679e-4436-b1dd-372ad95f6b9d
//begin of modifiable zone................T/2bb30b85-1b4b-45a8-afd7-10417bcb4b50
            elements[0] = pairing.getG1().newRandomElement();
            elements[1] = pairing.getG2().newRandomElement();
            BigInteger e1 = randomNumber(order);
            BigInteger e2 = randomNumber(order);
            elements[2] = elements[0].duplicate().mul(e1);
            elements[3] = elements[0].duplicate().mul(e2);
            elements[4] = elements[1].duplicate().mul(e2);
            elements[5] = pairing.pairing(elements[2], elements[4]);
//end of modifiable zone..................E/2bb30b85-1b4b-45a8-afd7-10417bcb4b50
        }

        public Element getE1() {
//begin of modifiable zone(JavaCode)......C/20a8c216-7f84-4893-8b84-2f39f258e4a2

//end of modifiable zone(JavaCode)........E/20a8c216-7f84-4893-8b84-2f39f258e4a2
//begin of modifiable zone................T/8e300ee7-1e66-4079-b060-c96c1a8b1632
            return elements[0];
//end of modifiable zone..................E/8e300ee7-1e66-4079-b060-c96c1a8b1632
        }

        public Element getE2() {
//begin of modifiable zone(JavaCode)......C/30b9ebf3-72fa-4514-bd6b-633984a1c7ed

//end of modifiable zone(JavaCode)........E/30b9ebf3-72fa-4514-bd6b-633984a1c7ed
//begin of modifiable zone................T/28507c29-4523-4da6-a1db-3ef3db8d243d
            return elements[1];
//end of modifiable zone..................E/28507c29-4523-4da6-a1db-3ef3db8d243d
        }

        public Element gete1E1() {
//begin of modifiable zone(JavaCode)......C/d402ee90-58aa-4355-9fa0-76e72cd5d886

//end of modifiable zone(JavaCode)........E/d402ee90-58aa-4355-9fa0-76e72cd5d886
//begin of modifiable zone................T/fcbb1577-3bc4-42e1-98d6-06849fd0729d
            return elements[2];
//end of modifiable zone..................E/fcbb1577-3bc4-42e1-98d6-06849fd0729d
        }

        public Element gete2E1() {
//begin of modifiable zone(JavaCode)......C/1fc4b26f-7f34-4555-bc14-042463e4e484

//end of modifiable zone(JavaCode)........E/1fc4b26f-7f34-4555-bc14-042463e4e484
//begin of modifiable zone................T/d80d76ee-4d56-45a1-a19a-2a98692059b6
            return elements[3];
//end of modifiable zone..................E/d80d76ee-4d56-45a1-a19a-2a98692059b6
        }

        public Element gete2E2() {
//begin of modifiable zone(JavaCode)......C/9f8fd3b2-3463-4c6a-9c73-c3ba2ead6048

//end of modifiable zone(JavaCode)........E/9f8fd3b2-3463-4c6a-9c73-c3ba2ead6048
//begin of modifiable zone................T/02eaf567-6bd5-4bb4-b6b3-2314db9df62d
            return elements[4];
//end of modifiable zone..................E/02eaf567-6bd5-4bb4-b6b3-2314db9df62d
        }

        public Element getPair() {
//begin of modifiable zone(JavaCode)......C/c3542633-8532-4150-832a-755f276bee01

//end of modifiable zone(JavaCode)........E/c3542633-8532-4150-832a-755f276bee01
//begin of modifiable zone................T/1992cc1a-96fa-4efd-bac5-4ea836c66142
            return elements[5];
//end of modifiable zone..................E/1992cc1a-96fa-4efd-bac5-4ea836c66142
        }

    }

}
