package com.info.model;


import javax.validation.constraints.NotEmpty;


public class StaffUpdate {

    private Long id;

    @NotEmpty(message = "Pole nie może być puste")
    private String firstName;


    @NotEmpty(message = "Pole nie może być puste")
    private String lastName;

    @NotEmpty(message = "Pole nie może być puste")
    private String typeService;


    @NotEmpty(message = "Pole nie może być puste")
    private String diet;


    @NotEmpty(message = "Pole nie może być puste")
    private String troops;


    @NotEmpty(message = "Pole nie może być puste")
    private String ensign;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getTroops() {
        return troops;
    }

    public void setTroops(String troops) {
        this.troops = troops;
    }

    public String getEnsign() {
        return ensign;
    }

    public void setEnsign(String ensign) {
        this.ensign = ensign;
    }
}
