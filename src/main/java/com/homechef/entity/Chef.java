package com.homechef.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chef")
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chefId;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String speciality;

    public Chef() {
    }

    public Chef(String name, String email,
                String password, String phone, String speciality) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.speciality = speciality;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}