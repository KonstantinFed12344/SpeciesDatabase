/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Konstantin
 */
public class Assign3c {

    public static void main(String[] args) {
        Database data = new Database();
        FileReader reader = null;
        Date start = new Date(System.currentTimeMillis());

        try {
            reader = new FileReader("assign3-urls.txt");
            BufferedReader bReader = new BufferedReader(reader);

            String url;

            while ((url = bReader.readLine()) != null) {
                try {
                    data.addRecords(url);
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            
            try {
                PrintStream prnt = new PrintStream("assign3-summary.txt");
                prnt.println(start);
                prnt.println("The number of species in the database is: " + data.getNumSpecies());
                prnt.println("The list of species are");
                for (Object obj : data.getAllSpecies()) {
                    SpeciesAtRisk specie = (SpeciesAtRisk) obj;
                    ArrayList<Habitat> habitats = data.getHabitats(specie.getId());
                    prnt.print(specie.getScientificName() + "(" + specie.getCommonName() + ")  ID:" + specie.getId() + "  Number of Habitats:");
                    prnt.println(data.getNumHabitats(specie.getId()) + "   Total Area: " + data.getTotalArea(specie.getId()));
                }
                prnt.println(new Date(System.currentTimeMillis()));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException g) {
            System.out.println("IOException");
        }
    }
}
