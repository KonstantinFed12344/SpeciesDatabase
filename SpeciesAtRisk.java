/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

/**
 *
 * @author Konstantin
 */
public class SpeciesAtRisk extends Database{
    
    private String scientificName;
    private String commonName;
    private int id;
    
    public SpeciesAtRisk(String sciName, String cName, int id){
        scientificName = sciName;
        commonName = cName;
        this.id = id;
    }
    
    public String getCommonName(){
        return commonName;
    }
    
    public String getScientificName(){
        return scientificName;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        SpeciesAtRisk spec = (SpeciesAtRisk)obj;
        
        return this.commonName.equals(spec.commonName)
                && this.scientificName.equals(spec.scientificName)
                && this.id == spec.getId();
    }
    
    public int hashCode(){
        return this.id + this.commonName.hashCode() + this.scientificName.hashCode();
    }
    
    public String toString(){
        return null;
    }
}
