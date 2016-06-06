package de.rwth_aachen.engel.beaconsforproduction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by steffen on 6/6/16.
 */

public class Beacon {
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
    public int getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(int temperatur) {
        this.temperatur = temperatur;
    }

    public List<Beacon> getDatabaseOrders() {
        return databaseOrders;
    }

    public void setDatabaseOrders(List<Beacon> databaseOrders) {
        this.databaseOrders = databaseOrders;
    }

    public List<Beacon> getScanOrders() {
        return scanOrders;
    }

    public void setScanOrders(List<Beacon> scanOrders) {
        this.scanOrders = scanOrders;
    }

    public List<Beacon> getOtherOrders() {
        return otherOrders;
    }

    public void setOtherOrders(List<Beacon> otherOrders) {
        this.otherOrders = otherOrders;
    }
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public machine getDatabaseMachine() {
        return databaseMachine;
    }

    public void setDatabaseMachine(machine databaseMachine) {
        this.databaseMachine = databaseMachine;
    }

    public machine getScanMachine() {
        return scanMachine;
    }

    public void setScanMachine(machine scanMachine) {
        this.scanMachine = scanMachine;
    }
    private String beacon,name,description,ID;
    private List<Beacon> databaseOrders,scanOrders,otherOrders;
    private int temperatur;
    private Date dueDate;
    private machine databaseMachine;
    private machine scanMachine;

    public Beacon(String ID, String beacon, String name, String description, List<Beacon> databaseOrders, List<Beacon> scanOrders, List<Beacon> otherOrders) {
        this.ID = ID;
        this.beacon = beacon;
        this.name = name;
        this.description = description;
        this.databaseOrders = databaseOrders;
        this.scanOrders = scanOrders;
        this.otherOrders = otherOrders;
    }
    public Beacon(String ID,int temperatur,String name,String beacon,String description,Date dueDate,machine databaseMachine,machine scanMachine){
        this.ID=ID;
        this.temperatur = temperatur;
        this.name = name;
        this.beacon = beacon;
        this.description = description;
        this.dueDate = dueDate;
        this.databaseMachine = databaseMachine;
        this.scanMachine = scanMachine;
    }
    public Beacon(JSONObject json){
        try {
            this.ID=json.getString("ID");
            this.temperatur=json.getInt("temperatur");
            this.name=json.getString("name");
            this.beacon=json.getString("beacon");
            this.description=json.getString("description");
            this.dueDate=new Date(json.getString("dueDate"));
            this.databaseMachine=new machine(json.getString("databaseMachine"));
            this.scanMachine=new machine(json.getString("scanMachine"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Beacon(String machine){
        try {
            JSONObject json=new JSONObject(machine);
            this.beacon=json.getString("beacon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Beacon(){

    }
    @Override
    public String toString(){
        String orders = "";
        for(Beacon x : databaseOrders){
            orders += x.getName();
        }
        String output = "id:"+ID+",beacon:"+beacon+",name:"+name+",descr:"+description+",databaseOrder:"+orders;
        return output;
    }
}
