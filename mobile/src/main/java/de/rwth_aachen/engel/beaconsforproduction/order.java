package de.rwth_aachen.engel.beaconsforproduction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Domino on 27.05.2016.
 */
public class order implements Serializable{
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    private int ID;
    private int temperatur;
    private String name;
    private String beacon;
    private String description;
    private Date dueDate;
    private machine databaseMachine;
    private machine scanMachine;
    public order(){
        ID=temperatur=0;
        name=beacon=description="";
        dueDate=new Date();
        databaseMachine=scanMachine=null;
    }
    public order(int ID,int temperatur,String name,String beacon,String description,Date dueDate,machine databaseMachine,machine scanMachine){
        this.ID=ID;
        this.temperatur = temperatur;
        this.name = name;
        this.beacon = beacon;
        this.description = description;
        this.dueDate = dueDate;
        this.databaseMachine = databaseMachine;
        this.scanMachine = scanMachine;
    }
    public order(JSONObject json){
        try {
            this.ID=json.getInt("ID");
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

}
