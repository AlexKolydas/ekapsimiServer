package com.example.alex.e_kapsimiserver.Model;

public class Food {

    private String name;
    private String image;
    private String price;
    private String menuId;
    private String description;
    private String discount;

    public Food() {
    }

    public Food(String name, String image, String price, String menuId) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.menuId = menuId;
        this.description=description;
        this.discount =discount;
    }


    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
