package com.example.shubhraj.maps;

public class Users
{
    private String firstName, lastName, password, loginPassword, email, description, price, location;
    private int category, availability;
    private String latitude, longitude;

    Users()
    {}

    public Users(String firstName, String lastName, String password, String loginPassword, String email, String description,
                 String price, String location, int category, int availability, String latitude, String longitude)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.loginPassword = loginPassword;
        this.email = email;
        this.description = description;
        this.price = price;
        this.location = location;
        this.category = category;
        this.availability = availability;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public int getCategory() {
        return category;
    }

    public int getAvailability() {
        return availability;
    }

    public double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public String getLongitude() {
        return longitude;
    }
}
