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
public class clinics {
    private String name;
    private int id;
    private String openTime;
    private String closeTime;
    private double ticketPrise;
    private String department;

    public clinics(String name, int id, String openTime, String closeTime, double ticketPrise, String department) {
        this.name = name;
        this.id = id;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.ticketPrise = ticketPrise;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public double getTicketPrise() {
        return ticketPrise;
    }

    public void setTicketPrise(double ticketPrise) {
        this.ticketPrise = ticketPrise;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
}
