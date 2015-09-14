package org.loccs.outsource.ibe;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeAPairing;

public abstract class PrimeOrderPairingIBEScheme extends PairingIBEScheme {
    protected int bitsize;

    public PrimeOrderPairingIBEScheme(int bitsize) {
//begin of modifiable zone(JavaSuper).....C/cc979563-53b7-42bb-8924-1ba2b5f69941

//end of modifiable zone(JavaSuper).......E/cc979563-53b7-42bb-8924-1ba2b5f69941
//begin of modifiable zone................T/91de79c7-2f88-4ed2-b3e3-7ec288a3a366
        this.bitsize = bitsize;
//end of modifiable zone..................E/91de79c7-2f88-4ed2-b3e3-7ec288a3a366
    }

    protected Element getG1Generator() {
//begin of modifiable zone(JavaCode)......C/c4fdb794-4e54-460c-9789-851de711a48b

//end of modifiable zone(JavaCode)........E/c4fdb794-4e54-460c-9789-851de711a48b
//begin of modifiable zone................T/d08fa413-c620-4cae-a1a6-361a76a3ca5b
        return pairing.getG1().newRandomElement();
//end of modifiable zone..................E/d08fa413-c620-4cae-a1a6-361a76a3ca5b
    }

    protected Pairing initializePairing() {
//begin of modifiable zone................T/64bd71b4-a603-4140-b751-e63b010ec4db
        PairingParametersGenerator<?> parametersGenerator = 
        		new TypeACurveGenerator(bitsize, 512);
        PairingParameters parameters = parametersGenerator.generate();
        pairing = new TypeAPairing(random, parameters);
//end of modifiable zone..................E/64bd71b4-a603-4140-b751-e63b010ec4db
//begin of modifiable zone................T/9ee2db0e-4493-46f3-97c5-0c0b7a1a6062
        return pairing;
//end of modifiable zone..................E/9ee2db0e-4493-46f3-97c5-0c0b7a1a6062
    }

}
