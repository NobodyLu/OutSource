package org.loccs.outsource.pairing;

import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import org.loccs.outsource.StepInformation;

public class PairingArithmeticEvaluationOutsource extends SymmetricPrimeOrderPairingOutsource {
    protected Element[] e = new Element[2];

    public PairingArithmeticEvaluationOutsource(int rbits, int qbits) {
//begin of modifiable zone................T/ff858f62-2489-4bbe-9d33-2af5a2dca495
super(rbits, qbits);

//end of modifiable zone..................E/ff858f62-2489-4bbe-9d33-2af5a2dca495
//begin of modifiable zone(JavaCode)......C/0164642e-5b45-4bbe-ba74-9b9ff9883827

//end of modifiable zone(JavaCode)........E/0164642e-5b45-4bbe-ba74-9b9ff9883827
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/94085290-4b34-43de-9a64-dadd87d1a2da
        Vector<String> entities = new Vector<String>();
        entities.add("TP");
        entities.add("A1");
        entities.add("A2");
//end of modifiable zone..................E/94085290-4b34-43de-9a64-dadd87d1a2da
//begin of modifiable zone................T/01a113af-2bbd-4e61-b89f-934ed882fc1e
        return entities;
//end of modifiable zone..................E/01a113af-2bbd-4e61-b89f-934ed882fc1e
    }

    protected StepInformation step() {
//begin of modifiable zone................T/b20d98df-0735-44dd-a608-1453ed5842f9
        if (nextStep.equals(""))
            return init();
        
        if (nextStep.equals("A1"))
            return add();
        
        if (nextStep.equals("A2"))
            return multiply();
//end of modifiable zone..................E/b20d98df-0735-44dd-a608-1453ed5842f9
//begin of modifiable zone................T/db8a3bfa-5198-4889-91d4-758d19f11259
        return new StepInformation("TP", "", true, false);
//end of modifiable zone..................E/db8a3bfa-5198-4889-91d4-758d19f11259
    }

    public StepInformation init() {
//begin of modifiable zone................T/30d28924-7c84-49c8-9331-7301c7ad0596
        for (int i = 0; i < 2; i++)
            e[i] = pairing.getG1().newRandomElement();
//end of modifiable zone..................E/30d28924-7c84-49c8-9331-7301c7ad0596
//begin of modifiable zone................T/72dc7e0a-f044-4f43-b142-1c11ee86e9fe
        return new StepInformation("TP", "A1", false, false);
//end of modifiable zone..................E/72dc7e0a-f044-4f43-b142-1c11ee86e9fe
    }

    public StepInformation add() {
//begin of modifiable zone................T/4317f700-1b44-4b35-a435-43cd734fb645
        Element temp = e[0].duplicate().add(e[1]);
//end of modifiable zone..................E/4317f700-1b44-4b35-a435-43cd734fb645
//begin of modifiable zone................T/cbdcfb2f-3968-4384-8a81-54d25ea03e5c
        return new StepInformation("A1", "A2", false, true);
//end of modifiable zone..................E/cbdcfb2f-3968-4384-8a81-54d25ea03e5c
    }

    public StepInformation multiply() {
//begin of modifiable zone................T/c6d3e015-6c7e-499a-9f81-ac293aa47e2c
        Element temp = e[0].duplicate().mul(e[1]);
//end of modifiable zone..................E/c6d3e015-6c7e-499a-9f81-ac293aa47e2c
//begin of modifiable zone................T/dd2be374-d34d-4e48-8c13-3d0a2549396d
        return new StepInformation("A2", "", true, true);
//end of modifiable zone..................E/dd2be374-d34d-4e48-8c13-3d0a2549396d
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
//begin of modifiable zone................T/0db436ca-dc60-424b-abc6-cb8a27d78ad7
    	PairingArithmeticEvaluationOutsource outsource = new PairingArithmeticEvaluationOutsource(160, 512);
        
        outsource.setRepeat(5000);
        
        //outsource.direct();
        
        outsource.evaluate();
//end of modifiable zone..................E/0db436ca-dc60-424b-abc6-cb8a27d78ad7
    }

}
