package org.loccs.outsource.ibe;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import org.loccs.outsource.Outsource;
import org.loccs.outsource.StepInformation;

public abstract class PairingIBEScheme extends Outsource {
    protected Element g;

    protected BigInteger order;

    protected Pairing pairing;

    public PairingIBEScheme() {
//begin of modifiable zone(JavaSuper).....C/675d6237-17a4-4640-95ad-cfb18b09b186

//end of modifiable zone(JavaSuper).......E/675d6237-17a4-4640-95ad-cfb18b09b186
//begin of modifiable zone(JavaCode)......C/675d6237-17a4-4640-95ad-cfb18b09b186

//end of modifiable zone(JavaCode)........E/675d6237-17a4-4640-95ad-cfb18b09b186
    }

    protected void compute() {
//begin of modifiable zone(JavaCode)......C/6851026e-9f69-45f2-94d5-153cc9bcf099

//end of modifiable zone(JavaCode)........E/6851026e-9f69-45f2-94d5-153cc9bcf099
    }

    protected abstract void decrypt();

    protected StepInformation decryptStep() {
//begin of modifiable zone................T/e296138b-53b3-453a-8e93-14940c47d921
        decrypt();
//end of modifiable zone..................E/e296138b-53b3-453a-8e93-14940c47d921
//begin of modifiable zone................T/2a6a238c-f804-47d1-8dc6-6e31ab6b8abc
        return new StepInformation("Decrypt", "KeyUpdate", false, true);
//end of modifiable zone..................E/2a6a238c-f804-47d1-8dc6-6e31ab6b8abc
    }

    protected abstract void encrypt();

    protected StepInformation encryptStep() {
//begin of modifiable zone................T/9f4b4cb2-3b58-4b70-a05d-5146c6594b73
        encrypt();
//end of modifiable zone..................E/9f4b4cb2-3b58-4b70-a05d-5146c6594b73
//begin of modifiable zone................T/6908393d-e0b5-43a7-9e42-42ee76f577f2
        return new StepInformation("Encrypt", "Decrypt", false, true);
//end of modifiable zone..................E/6908393d-e0b5-43a7-9e42-42ee76f577f2
    }

    protected List<String> getAllEntityNames() {
//begin of modifiable zone................T/98dd2cde-95b3-4921-a622-4e05f5d9fd6f
        Vector<String> entities = new Vector<String>();
        entities.add("Setup");
        entities.add("KeyGen");
        entities.add("Encrypt");
        entities.add("Decrypt");
        entities.add("KeyUpdate");
//end of modifiable zone..................E/98dd2cde-95b3-4921-a622-4e05f5d9fd6f
//begin of modifiable zone................T/614619bd-786c-49b9-998a-bf98ed8543af
        return entities;
//end of modifiable zone..................E/614619bd-786c-49b9-998a-bf98ed8543af
    }

    protected void initialize() {
//begin of modifiable zone(JavaCode)......C/118c3d46-7a4d-44cc-8241-2c4e5e00075f

//end of modifiable zone(JavaCode)........E/118c3d46-7a4d-44cc-8241-2c4e5e00075f
    }

    protected abstract Pairing initializePairing();

    protected abstract void keygen();

    protected StepInformation keygenStep() {
//begin of modifiable zone................T/d133d0af-ba50-415a-811f-c4431bd1c326
        keygen();
//end of modifiable zone..................E/d133d0af-ba50-415a-811f-c4431bd1c326
//begin of modifiable zone................T/b8bda70d-ecbc-4299-b0f7-6b79acd9f0d1
        return new StepInformation("KeyGen", "Encrypt", false, false);
//end of modifiable zone..................E/b8bda70d-ecbc-4299-b0f7-6b79acd9f0d1
    }

    protected abstract void keyupdate();

    protected StepInformation keyupdateStep() {
//begin of modifiable zone................T/ea1f4a0f-9f2f-47d5-812b-928507ff2a5f
        keyupdate();
//end of modifiable zone..................E/ea1f4a0f-9f2f-47d5-812b-928507ff2a5f
//begin of modifiable zone................T/bb0d29a1-8e5e-417a-a8fe-70dbf5b3f25f
        return new StepInformation("KeyUpdate", "", true, true);
//end of modifiable zone..................E/bb0d29a1-8e5e-417a-a8fe-70dbf5b3f25f
    }

    protected void randomInput() {
//begin of modifiable zone(JavaCode)......C/b81fb50e-e736-42a2-82a8-9a942beb3c5e

//end of modifiable zone(JavaCode)........E/b81fb50e-e736-42a2-82a8-9a942beb3c5e
    }

    protected abstract void setupAfterPairingInitialized();

    protected StepInformation setupStep() {
//begin of modifiable zone................T/c981286f-b892-443b-af24-5feba71070c9
        pairing = initializePairing();
        order = pairing.getG1().getOrder();
        setupAfterPairingInitialized();
//end of modifiable zone..................E/c981286f-b892-443b-af24-5feba71070c9
//begin of modifiable zone................T/7fcd341d-b1a2-4b10-b32b-01251bfdf141
        return new StepInformation("Setup", "KeyGen", false, false);
//end of modifiable zone..................E/7fcd341d-b1a2-4b10-b32b-01251bfdf141
    }

    protected StepInformation step() {
//begin of modifiable zone................T/4d71e1a7-3a79-4fc9-b110-ac930a39b528
        if (nextStep.equals(""))
            return setupStep();
        
        if (nextStep.equals("KeyGen"))
            return keygenStep();  
        
        if (nextStep.equals("Encrypt"))
            return encryptStep(); 
        
        if (nextStep.equals("Decrypt"))
            return decryptStep();       
        
        if (nextStep.equals("KeyUpdate"))
            return keyupdateStep();
//end of modifiable zone..................E/4d71e1a7-3a79-4fc9-b110-ac930a39b528
//begin of modifiable zone................T/310e0b56-fe6c-4eba-bc70-6baf941432a1
        return new StepInformation("Setup", "", true, false);
//end of modifiable zone..................E/310e0b56-fe6c-4eba-bc70-6baf941432a1
    }

}
