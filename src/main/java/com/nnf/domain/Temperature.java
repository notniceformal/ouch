package com.nnf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Temperature implements Comparable<Temperature>{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String temperatureDescription;

    public Temperature() {}

    public Temperature(String temperatureDescription) {
        this.temperatureDescription = temperatureDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemperatureDescription() {
        return temperatureDescription;
    }

    public void setTemperatureDescription(String temperatureDescription) {
        this.temperatureDescription = temperatureDescription;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", temperatureDescription='" + temperatureDescription + '\'' +
                '}';
    }

    @Override
    public int compareTo(Temperature other) {
        return this.temperatureDescription.compareTo(other.temperatureDescription);
    }
}
