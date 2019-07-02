package com.nnf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PainPosition implements Comparable<PainPosition> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String positionName;

    public PainPosition() {}

    public PainPosition(String positionName) {
        this.positionName = positionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "PainPosition{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                '}';
    }

    @Override
    public int compareTo(PainPosition other) {
        return this.positionName.compareTo(other.positionName);
    }
}
