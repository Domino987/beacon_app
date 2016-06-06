package de.rwth_aachen.engel.beaconsforproduction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Domino on 27.05.2016.
 */
public class Order implements Serializable{
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(int temperatur) {
        this.temperatur = temperatur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeacon() {
        return beacon;
    }

    public void setBeacon(String beacon) {
        this.beacon = beacon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Machine getDatabaseMachine() {
        return databaseMachine;
    }

    public void setDatabaseMachine(Machine databaseMachine) {
        this.databaseMachine = databaseMachine;
    }

    public Machine getScanMachine() {
        return scanMachine;
    }

    public void setScanMachine(Machine scanMachine) {
        this.scanMachine = scanMachine;
    }

    private int temperatur;
    private String name;
    private String ID;
    private String beacon;
    private String description;
    private Date dueDate;
    private Machine databaseMachine;
    private Machine scanMachine;
    public Order(){
        temperatur=0;
        name=beacon=description=ID="";
        dueDate=new Date();
        databaseMachine=scanMachine=null;
    }
    public Order(String ID, int temperatur, String name, String beacon, String description, Date dueDate, Machine databaseMachine, Machine scanMachine){
        this.ID=ID;
        this.temperatur = temperatur;
        this.name = name;
        this.beacon = beacon;
        this.description = description;
        this.dueDate = dueDate;
        this.databaseMachine = databaseMachine;
        this.scanMachine = scanMachine;
    }
    public Order(JSONObject json){
        try {
            this.ID=json.getString("ID");
            this.temperatur=json.getInt("temperatur");
            this.name=json.getString("name");
            this.beacon=json.getString("beacon");
            this.description=json.getString("description");
            this.dueDate=new Date(json.getString("dueDate"));
            this.databaseMachine=new Machine(json.getString("databaseMachine"));
            this.scanMachine=new Machine(json.getString("scanMachine"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String toString(){
        String output = "id:"+ID+",beacon:"+beacon+",name:"+name+",descr:"+description+",databaseMachine:"+databaseMachine;
        return output;
    }

}
