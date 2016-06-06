package de.rwth_aachen.engel.beaconsforproduction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Domino on 27.05.2016.
 */
public class Machine implements Serializable{
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    public List<Order> getDatabaseOrders() {
        return databaseOrders;
    }

    public void setDatabaseOrders(List<Order> databaseOrders) {
        this.databaseOrders = databaseOrders;
    }

    public List<Order> getScanOrders() {
        return scanOrders;
    }

    public void setScanOrders(List<Order> scanOrders) {
        this.scanOrders = scanOrders;
    }

    public List<Order> getOtherOrders() {
        return otherOrders;
    }

    public void setOtherOrders(List<Order> otherOrders) {
        this.otherOrders = otherOrders;
    }
    private String beacon,name,description,ID;
    private List<Order> databaseOrders,scanOrders,otherOrders;

    public Machine(String ID, String beacon, String name, String description, List<Order> databaseOrders, List<Order> scanOrders, List<Order> otherOrders) {
        this.ID = ID;
        this.beacon = beacon;
        this.name = name;
        this.description = description;
        this.databaseOrders = databaseOrders;
        this.scanOrders = scanOrders;
        this.otherOrders = otherOrders;
    }
    public Machine(String machine){
        try {
            JSONObject json=new JSONObject(machine);
            this.beacon=json.getString("beacon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Machine(){

    }
    @Override
    public String toString(){
        String orders = "";
        for(Order x : databaseOrders){
            orders+=x.getName();
        }
        String output = "id:"+ID+",beacon:"+beacon+",name:"+name+",descr:"+description+",databaseOrder:"+orders;
        return output;
    }
}
