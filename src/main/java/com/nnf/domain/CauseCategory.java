package com.nnf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CauseCategory implements Comparable<CauseCategory> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String causeDescription;

    public CauseCategory() {
    }

    public CauseCategory(String causeDescription) {
        this.causeDescription = causeDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCauseDescription() {
        return causeDescription;
    }

    public void setCauseDescription(String causeDescription) {
        this.causeDescription = causeDescription;
    }

    @Override
    public String toString() {
        return "CauseCategory{" +
                "id=" + id +
                ", causeDescription='" + causeDescription + '\'' +
                '}';
    }

    @Override
    public int compareTo(CauseCategory otherCause) {
        return this.causeDescription.compareTo(otherCause.causeDescription);
    }
}
