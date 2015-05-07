package org.loccs.outsource.pairing;

import it.unisa.dia.gas.jpbc.Pairing;
import org.loccs.outsource.Outsource;

public abstract class PairingOutsource extends Outsource {
    protected Pairing pairing;

    public PairingOutsource() {
//begin of modifiable zone(JavaSuper).....C/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb

//end of modifiable zone(JavaSuper).......E/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb
//begin of modifiable zone(JavaCode)......C/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb

//end of modifiable zone(JavaCode)........E/3cd3ebb7-4088-4a27-9371-d89f3ab74dfb
    }

    public abstract Pairing initializePairing();

    protected void initialize() {
//begin of modifiable zone................T/b55e2705-db98-48fd-bd97-d82b4cb8b593
        pairing = initializePairing();
//end of modifiable zone..................E/b55e2705-db98-48fd-bd97-d82b4cb8b593
    }

}
