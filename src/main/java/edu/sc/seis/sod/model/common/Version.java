/**
 * Version.java
 *
 * @author Philip Oliver-Paull
 */

package edu.sc.seis.sod.model.common;

public class Version{

    private int dbid;
    private String version;
    private boolean schemaChange;

    /** for hibernate */
    protected Version() {}
    
    public String getVersion() {
        return version;
    }

    
    protected void setVersion(String version) {
        this.version = version;
    }

    
    public boolean isSchemaChange() {
        return schemaChange;
    }

    protected void setSchemaChange(boolean schemaChange) {
        this.schemaChange = schemaChange;
    }

    public int getDbid() {return dbid;}
    public void setDbid(int dbid) {
        this.dbid = dbid;
    }
    
    public Version(String version, boolean schemaChange) {
        this.version = version;
        this.schemaChange = schemaChange;
    }

}



