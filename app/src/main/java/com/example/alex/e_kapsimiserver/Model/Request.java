package com.example.alex.e_kapsimiserver.Model;


import java.util.List;

public class Request {

    private String phone;
    private String name;
    private String surname;  //sto tutorial exei address
    private String total;
    private String status;
    private String comment;
    private List<Order> foods;  //list of food order

    public Request() {
    }

    public Request(String phone, String name, String surname, String total, String comment, List<Order> foods) {
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.total = total;
        this.foods = foods;
        this.comment=comment;
        this.status="0"; //0 is default,0:placed,1:shipping,2:shipped
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
