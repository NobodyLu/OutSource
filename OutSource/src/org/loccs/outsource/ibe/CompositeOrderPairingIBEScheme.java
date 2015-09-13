package org.loccs.outsource.ibe;

import java.math.BigInteger;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1CurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;

public abstract class CompositeOrderPairingIBEScheme extends PairingIBEScheme {
    protected int primeCount;

    protected int bitsize;

    protected BigInteger[] p = null;

    public CompositeOrderPairingIBEScheme(int primeCount, int bitsize) {
//begin of modifiable zone................T/075ed148-7ffe-42f0-b59a-216191d7fb30


//end of modifiable zone..................E/075ed148-7ffe-42f0-b59a-216191d7fb30
//begin of modifiable zone................T/a89a8860-3aea-47c0-a87e-4a82eb627d49
        this.primeCount = primeCount;
        this.bitsize = bitsize;
        p = new BigInteger[primeCount];
//end of modifiable zone..................E/a89a8860-3aea-47c0-a87e-4a82eb627d49
    }

    protected Pairing initializePairing() {
//begin of modifiable zone................T/ec6b2d17-cd92-4c8b-9d67-2778de28fada
        PairingParametersGenerator<?> parametersGenerator = new TypeA1CurveGenerator(primeCount, bitsize);
        PairingParameters parameters = parametersGenerator.generate();
        Pairing pairing = new TypeA1Pairing(random, parameters);
        g = pairing.getG1().newRandomElement();
		for (int i = 0; i < primeCount; i++)
			p[i] = parameters.getBigInteger("n" + i);
//end of modifiable zone..................E/ec6b2d17-cd92-4c8b-9d67-2778de28fada
//begin of modifiable zone................T/263c4486-da72-4941-9dd7-e9cefcb5d6e9
        return pairing;
//end of modifiable zone..................E/263c4486-da72-4941-9dd7-e9cefcb5d6e9
    }

}
