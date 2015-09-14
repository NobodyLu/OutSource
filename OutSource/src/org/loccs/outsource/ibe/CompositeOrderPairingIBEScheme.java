package org.loccs.outsource.ibe;

import java.math.BigInteger;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1CurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;

public abstract class CompositeOrderPairingIBEScheme extends PairingIBEScheme {
    protected int bitsize;

    protected Element[] gGp = null;

    protected BigInteger[] p = null;

    protected int primeCount;

    public CompositeOrderPairingIBEScheme(int primeCount, int bitsize) {
//begin of modifiable zone................T/075ed148-7ffe-42f0-b59a-216191d7fb30


//end of modifiable zone..................E/075ed148-7ffe-42f0-b59a-216191d7fb30
//begin of modifiable zone................T/a89a8860-3aea-47c0-a87e-4a82eb627d49
        this.primeCount = primeCount;
        this.bitsize = bitsize;
        p = new BigInteger[primeCount + 1];
        gGp = new Element[primeCount + 1];
//end of modifiable zone..................E/a89a8860-3aea-47c0-a87e-4a82eb627d49
    }

    protected Element getG1Generator() {
//begin of modifiable zone(JavaCode)......C/ae55e50f-9e5b-46ec-a2f5-1b17c8107274

//end of modifiable zone(JavaCode)........E/ae55e50f-9e5b-46ec-a2f5-1b17c8107274
//begin of modifiable zone................T/e1f92669-ed2f-4d73-811f-1cb7862b7ccc
        return pairing.getG1().newRandomElement();
//end of modifiable zone..................E/e1f92669-ed2f-4d73-811f-1cb7862b7ccc
    }

    protected Element getGpGenerator(int index) {
//begin of modifiable zone................T/e0015479-3137-430b-bb3b-510850734ddc
        Element gGp = g.duplicate();
        for (int i = 1; i <= primeCount; i++) {
            if (i != index)
                gGp.pow(p[i]);
        }
//end of modifiable zone..................E/e0015479-3137-430b-bb3b-510850734ddc
//begin of modifiable zone................T/13e0986f-135b-4e09-9e9a-e5f12cd4e142
        return gGp;
//end of modifiable zone..................E/13e0986f-135b-4e09-9e9a-e5f12cd4e142
    }

    protected Pairing initializePairing() {
//begin of modifiable zone................T/ec6b2d17-cd92-4c8b-9d67-2778de28fada
        PairingParametersGenerator<?> parametersGenerator = new TypeA1CurveGenerator(primeCount, bitsize);
        PairingParameters parameters = parametersGenerator.generate();
        Pairing pairing = new TypeA1Pairing(random, parameters);
        for (int i = 0; i < primeCount; i++)
            p[i + 1] = parameters.getBigInteger("n" + i);
        
//end of modifiable zone..................E/ec6b2d17-cd92-4c8b-9d67-2778de28fada
//begin of modifiable zone................T/263c4486-da72-4941-9dd7-e9cefcb5d6e9
        return pairing;
//end of modifiable zone..................E/263c4486-da72-4941-9dd7-e9cefcb5d6e9
    }

    protected Element newRandomGpElement(int index) {
//begin of modifiable zone................T/3f458dee-fe38-4a88-8b90-755008d9509c
        Element e = gGp[index].duplicate();
        BigInteger t = randomNumber(p[index]);
        e.pow(t);
//end of modifiable zone..................E/3f458dee-fe38-4a88-8b90-755008d9509c
//begin of modifiable zone................T/cf41c4d0-b671-4b63-9d03-6a69b1b8cb01
        return e;
//end of modifiable zone..................E/cf41c4d0-b671-4b63-9d03-6a69b1b8cb01
    }

}
