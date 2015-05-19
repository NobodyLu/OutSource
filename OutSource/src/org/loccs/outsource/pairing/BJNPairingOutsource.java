package org.loccs.outsource.pairing;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class BJNPairingOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected Element[] P = new Element[3];

    protected Element pair_P1_P2;

    protected BigInteger[] g = new BigInteger[3];

    protected Element inputA_g1P1;

    protected Element inputB_g2P2;

    protected Element[] alpha = new Element[4];

    protected Element pairing_A_B;

    public BJNPairingOutsource(int rbits, int qbits) {
//begin of modifiable zone................T/7393fc04-5c94-456a-92c5-802033edf26d
super(rbits, qbits);

//end of modifiable zone..................E/7393fc04-5c94-456a-92c5-802033edf26d
//begin of modifiable zone(JavaCode)......C/e7fd935a-d16c-4e38-927b-996299f0ed16

//end of modifiable zone(JavaCode)........E/e7fd935a-d16c-4e38-927b-996299f0ed16
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/06288cbf-e6cb-413b-aebc-5e7ee9f78060
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U");
//end of modifiable zone..................E/06288cbf-e6cb-413b-aebc-5e7ee9f78060
//begin of modifiable zone................T/0a07e8be-9590-4229-84af-fa96ce3707b0
        return entities;
//end of modifiable zone..................E/0a07e8be-9590-4229-84af-fa96ce3707b0
    }

    protected StepInformation step() {
//begin of modifiable zone................T/d2e30feb-9c69-43cc-8369-a688e3d2fa51
        if (nextStep.equals(""))
            return rand();
        
        if (nextStep.equals("TQuery"))
            return tQuery();
        
        if (nextStep.equals("UResponse"))
            return uResponse();
        
        if (nextStep.equals("TVerify"))
            return tVerify();
        
//end of modifiable zone..................E/d2e30feb-9c69-43cc-8369-a688e3d2fa51
//begin of modifiable zone................T/9bba7abc-eacb-49d6-a064-6e6c6b4845d5
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/9bba7abc-eacb-49d6-a064-6e6c6b4845d5
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/80625033-a066-4914-bc4c-b62e8f96b6b3
        P[1] = pairing.getG1().newRandomElement();
        P[2] = pairing.getG2().newRandomElement();
        pair_P1_P2 = pairing.pairing(P[1], P[2]);
//end of modifiable zone..................E/80625033-a066-4914-bc4c-b62e8f96b6b3
//begin of modifiable zone................T/82aab11a-b12a-4072-9552-e6906f3c2530
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/82aab11a-b12a-4072-9552-e6906f3c2530
    }

    protected StepInformation tQuery() {
//begin of modifiable zone................T/d90dd158-b1a4-457e-9cdc-e6e32461109b
        for (int i = 1; i <= 2; i++)
            g[i] = randomNumber(order);
        
        inputA_g1P1 = P[1].duplicate().mul(g[1]);
        inputA_g1P1 = inputA.duplicate().add(P[1].duplicate().mul(g[1]));
        inputB_g2P2 = inputB.duplicate().add(P[2].duplicate().mul(g[2]));
//end of modifiable zone..................E/d90dd158-b1a4-457e-9cdc-e6e32461109b
//begin of modifiable zone................T/bd92b6d3-fdec-4870-b8f0-e00371d40118
        return new StepInformation("T", "UResponse", false, true);
//end of modifiable zone..................E/bd92b6d3-fdec-4870-b8f0-e00371d40118
    }

    protected StepInformation uResponse() {
//begin of modifiable zone................T/5b8f28c9-32f6-4ca0-9800-fc7b26abf1ad
        alpha[1] = pairing.pairing(inputA_g1P1, P[2]);
        alpha[2] = pairing.pairing(P[1], inputB_g2P2);
        alpha[3] = pairing.pairing(inputA_g1P1, inputB_g2P2);
//end of modifiable zone..................E/5b8f28c9-32f6-4ca0-9800-fc7b26abf1ad
//begin of modifiable zone................T/ff784b18-4626-4e0a-ae06-f7fd3e57e0bc
        return new StepInformation("U", "TVerify", false, true);
//end of modifiable zone..................E/ff784b18-4626-4e0a-ae06-f7fd3e57e0bc
    }

    protected StepInformation tVerify() {
//begin of modifiable zone................T/eba5afb6-320c-4f71-bb6d-a128feed27fb

//end of modifiable zone..................E/eba5afb6-320c-4f71-bb6d-a128feed27fb
//begin of modifiable zone................T/3e06a8c5-41bd-47dd-9b26-bbeada669fea
        return new StepInformation("T", "UResponse2", false, true);
//end of modifiable zone..................E/3e06a8c5-41bd-47dd-9b26-bbeada669fea
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/6e3e3d12-7516-46f7-bb2a-9b538f898770
        BJNPairingOutsource outsource = new BJNPairingOutsource(160, 512);
        
        outsource.setRepeat(1);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/6e3e3d12-7516-46f7-bb2a-9b538f898770
    }

}
