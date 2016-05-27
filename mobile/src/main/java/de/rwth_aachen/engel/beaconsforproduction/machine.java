package de.rwth_aachen.engel.beaconsforproduction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Domino on 27.05.2016.
 */
public class machine {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBeacon() {
        return beacon;
    }

    public void setBeacon(String beacon) {
        this.beacon = beacon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<order> getDatabaseOrders() {
        return databaseOrders;
    }

    public void setDatabaseOrders(List<order> databaseOrders) {
        this.databaseOrders = databaseOrders;
    }

    public List<order> getScanOrders() {
        return scanOrders;
    }

    public void setScanOrders(List<order> scanOrders) {
        this.scanOrders = scanOrders;
    }

    public List<order> getOtherOrders() {
        return otherOrders;
    }

    public void setOtherOrders(List<order> otherOrders) {
        this.otherOrders = otherOrders;
    }
    private int ID;
    private String beacon,name,description;
    private List<order> databaseOrders,scanOrders,otherOrders;

    public machine(int ID, String beacon, String name, String description, List<order> databaseOrders, List<order> scanOrders, List<order> otherOrders) {
        this.ID = ID;
        this.beacon = beacon;
        this.name = name;
        this.description = description;
        this.databaseOrders = databaseOrders;
        this.scanOrders = scanOrders;
        this.otherOrders = otherOrders;
    }
    public machine(String machine){
        try {
            JSONObject json=new JSONObject(machine);
            this.beacon=json.getString("beacon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public machine(){

    }
}
