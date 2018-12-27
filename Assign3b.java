/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author Konstantin
 */
public class Assign3b {
    
    public static void main( String[] args )
    {
        
        Database data = new Database();
        
        String url = "http://donnees.ec.gc.ca/data/species/developplans/critical-habitat-for-species-at-risk-british-columbia/critical-habitat-for-species-at-risk-british-columbia-rocky-mountain-tailed-frog-ascaphus-montanus/CH_632_Ascaphus_montanus.json";
        
        try{
        data.addRecords(url);        
        }catch(IOException e){
            System.out.println("IOException");
        }
        
        System.out.println("The number of species in the database is: " + data.getNumSpecies());
        ArrayList<Habitat> habitats = data.getHabitats(632);
        System.out.println("\nHabitats of species ID: 632");
        for(Habitat habitat: habitats){

            System.out.println(habitat.toString());
        }
        System.out.println("\nThe number of Habitats is: " + data.getNumHabitats(632));
        System.out.println(data.getTotalArea(632) + "\n");
    }
    
}
