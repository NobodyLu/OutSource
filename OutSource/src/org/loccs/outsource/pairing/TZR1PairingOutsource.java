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
//begin of modifiable zone................T/c9c071ff-ae5d-4411-8e50-dfee2e0979da
super(rbits, qbits);

//end of modifiable zone..................E/c9c071ff-ae5d-4411-8e50-dfee2e0979da
//begin of modifiable zone(JavaCode)......C/d02e2d33-0bdd-4b28-92ce-6d839814b292

//end of modifiable zone(JavaCode)........E/d02e2d33-0bdd-4b28-92ce-6d839814b292
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/72a9487d-48aa-4775-9a62-05aad015da7c
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("T");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/72a9487d-48aa-4775-9a62-05aad015da7c
//begin of modifiable zone................T/9bf9f384-4dc4-482d-b324-ad1f85575e18
        return entities;
//end of modifiable zone..................E/9bf9f384-4dc4-482d-b324-ad1f85575e18
    }

    protected StepInformation step() {
//begin of modifiable zone................T/b0c7cfb8-44b2-4bee-ad17-3e241be3de68
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
        
//end of modifiable zone..................E/b0c7cfb8-44b2-4bee-ad17-3e241be3de68
//begin of modifiable zone................T/e2cc77ce-7fef-4d23-bbd4-c6f72a554bc9
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/e2cc77ce-7fef-4d23-bbd4-c6f72a554bc9
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/75d152e1-bf53-4fe7-af3d-94fb44b62042
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
//end of modifiable zone..................E/75d152e1-bf53-4fe7-af3d-94fb44b62042
//begin of modifiable zone................T/8ae1be63-5620-4469-b33d-2d35b5a02e82
        return new StepInformation("TP", "TQuery", false, false);
//end of modifiable zone..................E/8ae1be63-5620-4469-b33d-2d35b5a02e82
    }

    protected StepInformation tQuery() {
//begin of modifiable zone................T/db9aeb9c-5ddd-4065-a9c9-2022149e4065
        inputA_x1P1 = inputA.duplicate().add(x1P1);
        inputB_inv_x1_x2_P2 = inputB.duplicate().add(inv_x1_x2_P2);
        
        inputA_x1_inv_x2_x5_P1 = inputA.duplicate().add(x1_inv_x2_x5_P1);
        neg_inv_x1_x2_P2 = inv_x1_x2_P2.duplicate().negate();
        neg_x1P1 = x1P1.duplicate().negate();
        inputB_inv_x1_x6_P2 = inputB.duplicate().add(inv_x1_x6_P2);
//end of modifiable zone..................E/db9aeb9c-5ddd-4065-a9c9-2022149e4065
//begin of modifiable zone................T/bc65717b-fb52-4fd1-94ac-044afc4717f2
        return new StepInformation("T", "U1Response", false, true);
//end of modifiable zone..................E/bc65717b-fb52-4fd1-94ac-044afc4717f2
    }

    protected StepInformation u1Response() {
//begin of modifiable zone................T/60099a2c-8b03-4137-a29e-8ac76897f8e3
        alpha[1] = pairing.pairing(inputA_x1P1, inputB_inv_x1_x2_P2);
        alpha[2] = pairing.pairing(x3P1, x4P2);
//end of modifiable zone..................E/60099a2c-8b03-4137-a29e-8ac76897f8e3
//begin of modifiable zone................T/1b247606-3368-4dd3-bbca-cfb034d68d7e
        return new StepInformation("U1", "U2Response", false, true);
//end of modifiable zone..................E/1b247606-3368-4dd3-bbca-cfb034d68d7e
    }

    protected StepInformation u2Response() {
//begin of modifiable zone................T/f3951fae-ee31-48cd-93b1-912a6729af14
        alpha_prime[1] = pairing.pairing(inputA_x1_inv_x2_x5_P1, neg_inv_x1_x2_P2);
        alpha_prime[2] = pairing.pairing(neg_x1P1, inputB_inv_x1_x6_P2);
        alpha_prime[3] = pairing.pairing(x3P1, x4P2);
        alpha_prime[4] = pairing.pairing(x7P1, x8P2);
//end of modifiable zone..................E/f3951fae-ee31-48cd-93b1-912a6729af14
//begin of modifiable zone................T/056d5693-6cf6-46ee-92c6-eefe77d7fcac
        return new StepInformation("U2", "TVerify", false, true);
//end of modifiable zone..................E/056d5693-6cf6-46ee-92c6-eefe77d7fcac
    }

    protected StepInformation tVerify() {
//begin of modifiable zone................T/251964e5-424b-4ea7-8edf-c53beee55432
        if (!alpha[2].isEqual(alpha_prime[3]))
            System.out.println("fail to verify.");
        
        if (!pair_P1_P2_x7x8.equals(alpha_prime[4]))
        	System.out.println("fail to verify.");
        
        Element outsourceResult =  alpha[1].duplicate()
        		.mul(alpha_prime[1]).mul(alpha_prime[2])       		
        		.mul(pair_P1_P2_x5_x6_x2);
        
        if (!outsourceResult.isEqual(result))
            System.out.println("Incorrect outsource result.");
        
//end of modifiable zone..................E/251964e5-424b-4ea7-8edf-c53beee55432
//begin of modifiable zone................T/9eccc19b-61d8-4f3a-9fc7-f1ec2006b7e8
        return new StepInformation("T", "", true, true);
//end of modifiable zone..................E/9eccc19b-61d8-4f3a-9fc7-f1ec2006b7e8
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/bdb17341-bc15-4099-a40d-ccd1d9cce1dd
        TZR1PairingOutsource outsource = new TZR1PairingOutsource(160, 512);
        
        outsource.setRepeat(200);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/bdb17341-bc15-4099-a40d-ccd1d9cce1dd
    }

}
