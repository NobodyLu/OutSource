package org.loccs.outsource;

import java.util.Hashtable;
import java.util.Map;

public abstract class Outsource {
    private Map<String, EntityRuntime> entityRuntime = new Hashtable<String, EntityRuntime> ();

    public Outsource() {
//begin of modifiable zone(JavaSuper).....C/a484ad5f-5452-4c82-b329-d521ba0da984

//end of modifiable zone(JavaSuper).......E/a484ad5f-5452-4c82-b329-d521ba0da984
//begin of modifiable zone(JavaCode)......C/a484ad5f-5452-4c82-b329-d521ba0da984

//end of modifiable zone(JavaCode)........E/a484ad5f-5452-4c82-b329-d521ba0da984
    }

    public void addEntity(String name) {
//begin of modifiable zone(JavaCode)......C/ad3376b1-1155-471e-b7fd-bd89dea99950

//end of modifiable zone(JavaCode)........E/ad3376b1-1155-471e-b7fd-bd89dea99950
    }

    public void evaluate() {
//begin of modifiable zone(JavaCode)......C/7ebd909f-8272-4d69-ab2e-28b0a28af2ee

//end of modifiable zone(JavaCode)........E/7ebd909f-8272-4d69-ab2e-28b0a28af2ee
    }

    protected abstract void initialize();

    protected abstract StepInformation step();

}
