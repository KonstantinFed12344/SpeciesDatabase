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
public class Habitat extends Database {

    private String provinceTerritory;
    private int utmZone;
    private double latitude;
    private double longitude;
    private double area;

    public Habitat(String provTerr, int utmZone, double latitude, double longitude, double area) {
        provinceTerritory = provTerr;
        this.utmZone = utmZone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getProvinceTerritory() {
        return provinceTerritory;
    }

    public int getUtmZone() {
        return utmZone;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Habitat hab = (Habitat) obj;

        return this.provinceTerritory.equals(hab.provinceTerritory)
                && this.utmZone == hab.utmZone
                && this.latitude == hab.latitude
                && this.longitude == hab.longitude
                && this.area == hab.area;
    }

    public String toString() {
        return "Province Territory: " + provinceTerritory + ", UTMZone: " + utmZone + ", Latitude: " + latitude + ", Longitude: " + longitude + ", HabitatArea: " + area;
    }

}
