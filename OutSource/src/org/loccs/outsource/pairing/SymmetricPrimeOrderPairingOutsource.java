package org.loccs.outsource.pairing;

import java.math.BigInteger;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeAPairing;

public abstract class SymmetricPrimeOrderPairingOutsource extends PairingOutsource {
    protected int rbits;

    protected int qbits;

    protected BigInteger order;

    protected Element inputA;

    protected Element inputB;

    public SymmetricPrimeOrderPairingOutsource(int rbits, int qbits) {
//begin of modifiable zone(JavaSuper).....C/64c3d012-97a0-4de1-90e4-22677f6e8789

//end of modifiable zone(JavaSuper).......E/64c3d012-97a0-4de1-90e4-22677f6e8789
//begin of modifiable zone................T/4417e65d-7103-49ac-868c-1a2baa6c3e8f
        this.rbits = rbits;
        this.qbits = qbits;
//end of modifiable zone..................E/4417e65d-7103-49ac-868c-1a2baa6c3e8f
    }

    public Pairing initializePairing() {
//begin of modifiable zone................T/396cff36-4276-42b5-a123-4b53f7e91f2c
        PairingParametersGenerator<?> parametersGenerator = new TypeACurveGenerator(rbits, qbits);
        PairingParameters parameters = parametersGenerator.generate();
        Pairing pairing = new TypeAPairing(random, parameters);
        order = pairing.getG1().getOrder();
//end of modifiable zone..................E/396cff36-4276-42b5-a123-4b53f7e91f2c
//begin of modifiable zone................T/1178b660-94db-441b-b81b-cdb95c1d1700
        return pairing;
//end of modifiable zone..................E/1178b660-94db-441b-b81b-cdb95c1d1700
    }

    protected void randomInput() {
//begin of modifiable zone................T/96e82459-6229-4958-ab6b-8799bc0cdd38
        inputA = pairing.getG1().newRandomElement();
        inputB = pairing.getG2().newRandomElement();
//end of modifiable zone..................E/96e82459-6229-4958-ab6b-8799bc0cdd38
    }

}
