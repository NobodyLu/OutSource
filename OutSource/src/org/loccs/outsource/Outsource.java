package org.loccs.outsource;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public abstract class Outsource {
    protected List<String> entityName = new Vector<String> ();

    protected int repeat = 1;

    protected String nextStep = "";

    private Map<String, EntityRuntime> entityRuntime = new Hashtable<String, EntityRuntime> ();

    protected SecureRandom random = new SecureRandom();

    public Outsource() {
//begin of modifiable zone(JavaSuper).....C/a484ad5f-5452-4c82-b329-d521ba0da984

//end of modifiable zone(JavaSuper).......E/a484ad5f-5452-4c82-b329-d521ba0da984
//begin of modifiable zone(JavaCode)......C/a484ad5f-5452-4c82-b329-d521ba0da984

//end of modifiable zone(JavaCode)........E/a484ad5f-5452-4c82-b329-d521ba0da984
    }

    public void setRepeat(int value) {
//begin of modifiable zone................T/d2e80da4-5dbb-4610-8399-1357dcb945a6
        // Automatically generated method. Please delete this comment before entering specific code.
        this.repeat = value;
//end of modifiable zone..................E/d2e80da4-5dbb-4610-8399-1357dcb945a6
    }

    public void direct() {
//begin of modifiable zone................T/4c2d19da-f4e6-40f7-bbcf-c14bc0f21c11
        initialize();
        
        randomInput();
        
        long start = System.nanoTime();
        for (int i = 1; i < repeat; i++)
            compute();
        long end = System.nanoTime();
        
        System.out.println("Direct computer time: " + (end - start) / 1000000 + "ms.");
//end of modifiable zone..................E/4c2d19da-f4e6-40f7-bbcf-c14bc0f21c11
    }

    public void evaluate() {
//begin of modifiable zone................T/bcec36bc-3f10-41f9-a122-f9ad15f7e68f
        entityRuntime.clear();
        
        entityName = getAllEntityNames();
        for (int i = 0; i < entityName.size(); i++)
            addEntity(entityName.get(i));
        
        initialize();
        
        randomInput();
        
        long start = System.nanoTime();
        for (int i = 0; i < repeat; i++)
            compute();
        long end = System.nanoTime();
        System.out.println("Direct computer time: " + (end - start) / 1000000 + "ms.");
             
        boolean finish = false;
        while (!finish) {
            start = System.nanoTime();
            
            StepInformation information = step();
            
            if (information.isRepeat()) {
            	for (int i = 1; i < repeat; i++)
            		information = step();
            }
            
            end = System.nanoTime();
            
            EntityRuntime runtime = entityRuntime.get(information.getName());
            runtime.increateRuntime(end - start);
            
            nextStep = information.getNext();
            finish = information.isFinish();
        }
        
        for (int i = 0; i < entityName.size(); i++) {
            EntityRuntime runtime = entityRuntime.get(entityName.get(i));
            System.out.println(entityName.get(i) + ": " + (runtime.getRuntime() / 1000000) + " ms.");
        }
//end of modifiable zone..................E/bcec36bc-3f10-41f9-a122-f9ad15f7e68f
    }

    protected void addEntity(String name) {
//begin of modifiable zone................T/8132b083-ecf5-4a94-888e-946b50704956
        entityRuntime.put(name, new EntityRuntime(name));
//end of modifiable zone..................E/8132b083-ecf5-4a94-888e-946b50704956
    }

    protected abstract List<String> getAllEntityNames();

    protected abstract void initialize();

    protected abstract void randomInput();

    protected abstract StepInformation step();

    protected BigInteger randomNumber(BigInteger bound) {
//begin of modifiable zone................T/70161a10-418d-44e5-a4c1-98379e1808a5
        BigInteger number = new BigInteger(bound.bitLength() + 5, random);
        number = number.mod(bound);
//end of modifiable zone..................E/70161a10-418d-44e5-a4c1-98379e1808a5
//begin of modifiable zone................T/86122423-4a92-4f86-85a7-d3443fae5b3e
        return number;
//end of modifiable zone..................E/86122423-4a92-4f86-85a7-d3443fae5b3e
    }

    protected abstract void compute();

}
