/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Ali Tahoon
 */
public class department {
    private String name;
    private String managerName;
    private String managerAssitName;
    private int id;
    private int numOfBeds;

    public department(String name, String managerName, String managerAssitName, int id, int numOfBeds) {
        this.name = name;
        this.managerName = managerName;
        this.managerAssitName = managerAssitName;
        this.id = id;
        this.numOfBeds = numOfBeds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerAssitName() {
        return managerAssitName;
    }

    public void setManagerAssitName(String managerAssitName) {
        this.managerAssitName = managerAssitName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }
}
