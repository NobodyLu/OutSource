package org.loccs.outsource.pairing;

import java.security.SecureRandom;
import it.unisa.dia.gas.jpbc.Pairing;

public abstract class PairingOutsource {
    private Pairing pairing;

    private SecureRandom random = new SecureRandom();

    public PairingOutsource() {
//begin of modifiable zone(JavaSuper).....C/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb

//end of modifiable zone(JavaSuper).......E/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb
//begin of modifiable zone(JavaCode)......C/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb

//end of modifiable zone(JavaCode)........E/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb
    }

    public abstract Pairing InitializePairing();

}
