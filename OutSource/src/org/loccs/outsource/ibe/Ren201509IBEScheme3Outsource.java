package org.loccs.outsource.ibe;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class Ren201509IBEScheme3Outsource extends Ren201509IBEScheme3 {
    protected Element g_rTi1;

    protected BigInteger[] OK = new BigInteger[4];

    protected BigInteger a;

    protected Element[] g_OK1 = new Element[3];

    protected Element[] h2_OK2 = new Element[3];

    protected Element[] u2_OK3 = new Element[3];

    protected Element[] kTi_prime = new Element[3];

    public Ren201509IBEScheme3Outsource(int bitsize) {
//begin of modifiable zone................T/ccb7b546-543d-49ca-9c97-dbb0b39cca46
super(bitsize);

//end of modifiable zone..................E/ccb7b546-543d-49ca-9c97-dbb0b39cca46
//begin of modifiable zone(JavaCode)......C/e2b794b7-94f1-41d6-82c9-9870d12b9043

//end of modifiable zone(JavaCode)........E/e2b794b7-94f1-41d6-82c9-9870d12b9043
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/218ab219-634b-4c4d-9d5b-e531f239c58d
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("PKG");
        entities.add("U1");
        entities.add("U2");
//end of modifiable zone..................E/218ab219-634b-4c4d-9d5b-e531f239c58d
//begin of modifiable zone................T/2e313570-aaff-4218-851b-12734ea2d165
        return entities;
//end of modifiable zone..................E/2e313570-aaff-4218-851b-12734ea2d165
    }

    protected void initialize() {
//begin of modifiable zone................T/677721cb-6cd3-4ca9-9c8c-4360c9fc28bd
        pairing = initializePairing();
        g = getG1Generator();
        order = pairing.getG1().getOrder();
        setupAfterPairingInitialized();
        
        ID = randomNumber(order);
        keygen();
//end of modifiable zone..................E/677721cb-6cd3-4ca9-9c8c-4360c9fc28bd
    }

    protected void compute() {
//begin of modifiable zone................T/77db732b-102a-47bf-921f-e1d636872d46
        keyupdate();
//end of modifiable zone..................E/77db732b-102a-47bf-921f-e1d636872d46
    }

    protected StepInformation PKGQuery() {
//begin of modifiable zone................T/aa5548d8-dc09-4c34-8ead-b95881904880
        a = randomNumber(order);
        OK[1] = alpha[2].subtract(a);
        OK[1] = OK[1].mod(order);
        
        rand();
        OK[2] = rTi1.subtract(a);
        OK[2] = OK[2].mod(order);
        OK[3] = Hash(ID, Ti1);
        OK[3] = OK[3].multiply(rTi1);
        OK[3] = OK[3].subtract(a);
        OK[3] = OK[3].mod(order);
//end of modifiable zone..................E/aa5548d8-dc09-4c34-8ead-b95881904880
//begin of modifiable zone................T/d6d76090-36be-4c36-85b8-a01285e7f16b
        return new StepInformation("PKG", "U1Compute", false, true);
//end of modifiable zone..................E/d6d76090-36be-4c36-85b8-a01285e7f16b
    }

    protected StepInformation U1Compute() {
//begin of modifiable zone................T/06211d36-349f-425d-ad74-1173385dd8e3
        g_OK1[1] = g1.duplicate();
        g_OK1[1].pow(OK[1]);
        h2_OK2[1] = h[2].duplicate();
        h2_OK2[1].pow(OK[2]);
        u2_OK3[1] = u[2].duplicate();
        u2_OK3[1].pow(OK[3]);
//end of modifiable zone..................E/06211d36-349f-425d-ad74-1173385dd8e3
//begin of modifiable zone................T/b80357a0-715c-44fa-bdc4-9310c1e368d2
        return new StepInformation("U1", "U2Compute", false, true);
//end of modifiable zone..................E/b80357a0-715c-44fa-bdc4-9310c1e368d2
    }

    protected StepInformation U2Compute() {
//begin of modifiable zone................T/e65aca33-79c1-407c-ab9d-289e4391e393
        g_OK1[2] = g1.duplicate();
        g_OK1[2].pow(OK[1]);
        h2_OK2[2] = h[2].duplicate();
        h2_OK2[2].pow(OK[2]);
        u2_OK3[2] = u[2].duplicate();
        u2_OK3[2].pow(OK[3]);
//end of modifiable zone..................E/e65aca33-79c1-407c-ab9d-289e4391e393
//begin of modifiable zone................T/6585b6c5-b3b8-4c00-b59c-bfea286c6bcd
        return new StepInformation("U2", "PKGVerify", false, true);
//end of modifiable zone..................E/6585b6c5-b3b8-4c00-b59c-bfea286c6bcd
    }

    protected StepInformation PKGVerify() {
//begin of modifiable zone................T/91c502bb-4127-4231-b838-68dba85004cc
        kTi_prime[2] = g_OK1[1].duplicate();
        kTi_prime[2].mul(h2_OK2[1]);
        kTi_prime[2].mul(u2_OK3[1]);
        kTi_prime[2].mul(R[2][2]);
        
        kTi_prime[1] = g_rTi1.duplicate();
        kTi_prime[1].mul(R[2][1]);
        
        if (!KTi[1].equals(kTi_prime[1]) || !KTi[2].equals(kTi_prime[2]))
            System.out.println("outsource incorrect.");
//end of modifiable zone..................E/91c502bb-4127-4231-b838-68dba85004cc
//begin of modifiable zone................T/b5ac9cff-24f6-46f4-b68b-aa08881bcf8a
        return new StepInformation("PKG", "", true, true);
//end of modifiable zone..................E/b5ac9cff-24f6-46f4-b68b-aa08881bcf8a
    }

    protected StepInformation rand() {
//begin of modifiable zone................T/808acc2e-df00-47a9-a604-ba2f90c74b48
        g_rTi1 = g1.duplicate();
        g_rTi1.pow(rTi1);
//end of modifiable zone..................E/808acc2e-df00-47a9-a604-ba2f90c74b48
//begin of modifiable zone................T/949e03d9-b5b7-4696-8f7d-73faceb0fe66
        return new StepInformation("TP", "PKGQuery", false, true);
//end of modifiable zone..................E/949e03d9-b5b7-4696-8f7d-73faceb0fe66
    }

    protected StepInformation step() {
//begin of modifiable zone................T/7bdffe7b-7649-4e6a-8331-400dc6165ff1
        if (nextStep.equals(""))
            return PKGQuery();
        
        if (nextStep.equals("U1Compute"))
            return U1Compute();  
        
        if (nextStep.equals("U2Compute"))
            return U2Compute(); 
        
        if (nextStep.equals("PKGQuery"))
            return PKGQuery();
//end of modifiable zone..................E/7bdffe7b-7649-4e6a-8331-400dc6165ff1
//begin of modifiable zone................T/6ae19461-e796-4eb1-9588-409c21826d2a
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/6ae19461-e796-4eb1-9588-409c21826d2a
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/739217e3-c24e-42a9-9188-c8e85eacdd4b
    	Ren201509IBEScheme3Outsource outsource = new Ren201509IBEScheme3Outsource(512);
        
        outsource.setRepeat(10);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/739217e3-c24e-42a9-9188-c8e85eacdd4b
    }

}
