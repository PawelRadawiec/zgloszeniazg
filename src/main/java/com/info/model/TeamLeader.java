package com.info.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "team_leader")
public class TeamLeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fiest_name")
    @NotEmpty(message = "Pole nie może być puste")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Pole nie może być puste")
    private String lastName;

    @Column(name = "email", unique = true)
    @Email(message = "Wprowadź poprawny email")
    @NotEmpty(message = "Pole nie może być puste")
    private String email;

    @Column(name = "team_name", unique = true)
    @NotEmpty(message = "Pole nie może być puste")
    private String teamName;

    @Column(name = "phone_number")
    @NotEmpty(message = "Pole nie może być puste")
    private String phonenumber;

    @Column(name = "troops")
    @NotEmpty(message = "Pole nie może być puste")
    private String troops;

    @Column(name = "password")
    @Length(min = 5, message = "haslo za krotkie")
    @NotEmpty(message = "Pole nie może być puste")
    private String password;

    @Column(name = "leader_role")
    private String role;

    @Column(name = "active")
    private int active;


    public TeamLeader(){}


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


    public String getTeamName() { return teamName; }

    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getPhonenumber() { return phonenumber; }

    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public String getTroops() { return troops; }

    public void setTroops(String troops) { this.troops = troops; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public int getActive() { return active; }

    public void setActive(int active) { this.active = active; }
}
