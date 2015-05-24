package org.loccs.outsource.pairing;

import java.math.BigInteger;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeAPairing;

public abstract class MultipleSymmetricPrimeOrderPairingOutsource extends PairingOutsource {
    protected int rbits;

    protected int qbits;

    protected BigInteger order;

    protected Element result;

    protected int pairingCount;

    protected Element[] inputA;

    protected Element[] inputB;

    public MultipleSymmetricPrimeOrderPairingOutsource(int rbits, int qbits, int count) {
//begin of modifiable zone(JavaSuper).....C/10458665-34af-4049-a07d-48d15946beb3

//end of modifiable zone(JavaSuper).......E/10458665-34af-4049-a07d-48d15946beb3
//begin of modifiable zone................T/854461c9-c045-49e3-9671-f9e9bb7f77a8
        this.rbits = rbits;
        this.qbits = qbits;
        this.pairingCount = count;
        inputA = new Element[count];
        inputB = new Element[count];
//end of modifiable zone..................E/854461c9-c045-49e3-9671-f9e9bb7f77a8
    }

    public Pairing initializePairing() {
//begin of modifiable zone................T/c2293009-c102-45b7-962e-2f9111f5d7ec
        PairingParametersGenerator<?> parametersGenerator = new TypeACurveGenerator(rbits, qbits);
        PairingParameters parameters = parametersGenerator.generate();
        Pairing pairing = new TypeAPairing(random, parameters);
        order = pairing.getG1().getOrder();
//end of modifiable zone..................E/c2293009-c102-45b7-962e-2f9111f5d7ec
//begin of modifiable zone................T/1885c4be-fc07-4fb7-8281-9fbf3bda3f5a
        return pairing;
//end of modifiable zone..................E/1885c4be-fc07-4fb7-8281-9fbf3bda3f5a
    }

    protected void randomInput() {
//begin of modifiable zone................T/8925c653-98fa-4554-abdb-a39f206b7e8e
    	for (int i = 0; i < pairingCount; i++) {
    		inputA[i] = pairing.getG1().newRandomElement();
    		inputB[i] = pairing.getG2().newRandomElement();
    	}
//end of modifiable zone..................E/8925c653-98fa-4554-abdb-a39f206b7e8e
    }

    protected void compute() {
//begin of modifiable zone................T/ac334763-a125-4fee-9c38-5ada84d01868
    	result = pairing.pairing(inputA, inputB);
//end of modifiable zone..................E/ac334763-a125-4fee-9c38-5ada84d01868
    }

}
