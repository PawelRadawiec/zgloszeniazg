package com.info.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team_member")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "pole nie moze byc puste")
    @Column(name = "fiest_name")
    private String firstName;

    @NotEmpty(message = "pole nie moze byc puste")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Wpisz miasto")
    @Column(name = "city")
    private String homeCity;

    @NotEmpty(message = "Wpisz ulice")
    @Column(name = "street")
    private String street;

    @NotNull(message = "Wpisz numer telefonu")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Wpisz numer telefonu")
    @Column(name = "team_leader_phone")
    private String teamLeaderPhone;

    @NotEmpty(message = "Wybierz")
    @Column(name = "meal_category")
    private String mealCategory;

    @Column(name = "leader_name")
    private String leaderName;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_leader_email")
    private String teamLeaderEmail;


    public TeamMember(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTeamLeaderPhone() {
        return teamLeaderPhone;
    }

    public void setTeamLeaderPhone(String teamLeaderPhone) {
        this.teamLeaderPhone = teamLeaderPhone;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeaderEmail() { return teamLeaderEmail; }

    public void setTeamLeaderEmail(String teamLeaderEmail) { this.teamLeaderEmail = teamLeaderEmail; }
}
