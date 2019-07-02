package com.nnf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RemedyCategory implements Comparable<RemedyCategory>{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String remedyDescription;

    public RemedyCategory() {}

    public RemedyCategory(String remedyDescription) {
        this.remedyDescription = remedyDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemedyDescription() {
        return remedyDescription;
    }

    public void setRemedyDescription(String remedyDescription) {
        this.remedyDescription = remedyDescription;
    }

    @Override
    public String toString() {
        return "RemedyCategory{" +
                "id=" + id +
                ", remedyDescription='" + remedyDescription + '\'' +
                '}';
    }

    @Override
    public int compareTo(RemedyCategory other) {
        return this.remedyDescription.compareTo(other.remedyDescription);
    }
}
