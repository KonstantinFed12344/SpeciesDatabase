/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Konstantin
 */
public class Database {

    private HashMap<SpeciesAtRisk, ArrayList<Habitat>> data;

    public Database() {
        data = new HashMap();
    }

    public void addRecord(SpeciesAtRisk specie, Habitat habitat) {

        if (!data.containsKey(specie)) {
            data.put(specie, new ArrayList<Habitat>());
        }

        data.get(specie).add(habitat);
    }

    public void addRecords(String urlJSON) throws MalformedURLException, IOException {
        try {
            // Build Connection
            URL api_url = new URL(urlJSON);

            URLConnection api = api_url.openConnection();

            // Set HTTP Headers
            api.setRequestProperty("Accept", "application/json");
            api.setRequestProperty("Accept-Language", "en");

            // Get Response
            JSONTokener tokener = new JSONTokener(api.getInputStream());
            JSONObject jsondata = new JSONObject(tokener);

            // JSON Metadata: There are two top-level fields: type and features
            JSONArray fields = jsondata.names();
            JSONArray features = jsondata.getJSONArray("features");

            int numFeatures = features.length();

            // JSON Metadata: Under features, there are three fields: geometry, type and properties
            Set elements = ((JSONObject) features.get(0)).keySet();

            SpeciesAtRisk specie;
            Habitat habitat;

            for (int i = 0; i < numFeatures; i++) {
                JSONObject feature = (JSONObject) features.get(i);
                JSONObject properties = feature.getJSONObject("properties");

                String sciName = properties.get("SciName").toString();
                int utmZone = new Integer(properties.get("UTMZone").toString()).intValue();
                double shapeArea = new Double(properties.get("Shape_Area").toString()).doubleValue();
                String commonName = properties.get("CommName_E").toString();
                int id = new Integer(properties.get("SpeciesID").toString()).intValue();
                String provinceTerritory = properties.get("ProvTerr").toString();
                double latitude = new Double(properties.get("Latitude").toString()).doubleValue();
                double longitude = new Double(properties.get("Longitude").toString()).doubleValue();

                this.addRecord(new SpeciesAtRisk(sciName, commonName, id), new Habitat(provinceTerritory, utmZone, latitude, longitude, shapeArea));
            }

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } catch (IOException e) {
            System.out.println("IO Error");
        }

    }

    public int getNumSpecies() {
        ArrayList<Integer> idTracker = new ArrayList<Integer>();
        int j = 0;
        for (SpeciesAtRisk specie : data.keySet()) {
            if (!idTracker.contains((Integer) specie.getId())) {
                j++;
                idTracker.add((Integer) specie.getId());
            }
        }

        return j;
    }

    public int getNumHabitats(int speciesID) {
        for (SpeciesAtRisk specie : data.keySet()) {
            if (specie.getId() == speciesID) {
                return data.get(specie).size();
            }
        }
        return 0;
    }

    public double getTotalArea(int speciesID) {
        double totalArea = 0;
        for (SpeciesAtRisk specie : data.keySet()) {
            if (specie.getId() == speciesID) {
                for (Habitat habitat : data.get(specie)) {
                    totalArea += habitat.getArea();
                }
            }
        }
        return totalArea;
    }

    public ArrayList<Habitat> getHabitats(int speciesID) {
        for (SpeciesAtRisk specie : data.keySet()) {
            if (specie.getId() == speciesID) {
                return data.get(specie);
            }
        }
        return null;
    }

    public Set getAllSpecies() {
        return data.keySet();
    }
}
