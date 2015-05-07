package org.loccs.outsource;


public class EntityRuntime {
    private String name;

    private long runtime = 0;

    public EntityRuntime(String name) {
//begin of modifiable zone(JavaSuper).....C/0a89eab6-61d5-4dfb-83e4-28db2f73ac01

//end of modifiable zone(JavaSuper).......E/0a89eab6-61d5-4dfb-83e4-28db2f73ac01
//begin of modifiable zone(JavaCode)......C/0a89eab6-61d5-4dfb-83e4-28db2f73ac01
    	this.name = name;
//end of modifiable zone(JavaCode)........E/0a89eab6-61d5-4dfb-83e4-28db2f73ac01
    }

    String getName() {
//begin of modifiable zone................T/8b93dba6-56ff-4f2b-9a91-5e06521b5baa
        // Automatically generated method. Please delete this comment before entering specific code.
//end of modifiable zone..................E/8b93dba6-56ff-4f2b-9a91-5e06521b5baa
//begin of modifiable zone................T/13f42b5c-4225-46ca-873a-c91e43aeee2c
        return this.name;
//end of modifiable zone..................E/13f42b5c-4225-46ca-873a-c91e43aeee2c
    }

    long getRuntime() {
//begin of modifiable zone................T/13551af8-6424-4cda-8a23-421527e52dd4
        // Automatically generated method. Please delete this comment before entering specific code.
//end of modifiable zone..................E/13551af8-6424-4cda-8a23-421527e52dd4
//begin of modifiable zone................T/e9da6493-0fc6-412c-9b79-46b483229f05
        return this.runtime;
//end of modifiable zone..................E/e9da6493-0fc6-412c-9b79-46b483229f05
    }

    public void increateRuntime(long ns) {
//begin of modifiable zone(JavaCode)......C/e0cd3b5e-48dc-40e6-ae1f-b4e78ced4129
    	runtime += ns;
//end of modifiable zone(JavaCode)........E/e0cd3b5e-48dc-40e6-ae1f-b4e78ced4129
    }

}
