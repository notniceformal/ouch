package com.nnf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PainType implements Comparable<PainType> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String typeDescription;

    public PainType() {}

    public PainType(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    @Override
    public String toString() {
        return "PainType{" +
                "id=" + id +
                ", typeDescription='" + typeDescription + '\'' +
                '}';
    }

    @Override
    public int compareTo(PainType other) {
        return this.typeDescription.compareTo(other.typeDescription);
    }
}
