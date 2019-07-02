package com.nnf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OccurrenceTime implements Comparable<OccurrenceTime> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String timeDescription;

    public OccurrenceTime() {}

    public OccurrenceTime(String timeDescription) {
        this.timeDescription = timeDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeDescription() {
        return timeDescription;
    }

    public void setTimeDescription(String timeDescription) {
        this.timeDescription = timeDescription;
    }

    @Override
    public String toString() {
        return "OccurrenceTime{" +
                "id=" + id +
                ", timeDescription='" + timeDescription + '\'' +
                '}';
    }

    @Override
    public int compareTo(OccurrenceTime other) {
        return this.timeDescription.compareTo(other.timeDescription);
    }
}
