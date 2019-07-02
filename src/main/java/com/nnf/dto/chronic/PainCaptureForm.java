package com.nnf.dto.chronic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class PainCaptureForm implements Comparable<PainCaptureForm> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 4, message = "yyyy")
    @JsonProperty("year")
    private String year;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2, message = "MM")
    @JsonProperty("month")
    private String month;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2, message = "dd")
    @JsonProperty("day")
    private String day;

    @NotNull
    @NotEmpty(message = "Mandatory")
    @JsonProperty("position")
    private String position;

    @NotNull
    @JsonProperty("occurrence")
    private String occurrence;

    @NotNull
    @JsonProperty("type")
    private String type;

    @NotNull
    @JsonProperty("temperature")
    private String temperature;

    @NotNull
    @JsonProperty("possible_cause")
    private String possibleCause;

    @NotNull
    @JsonProperty("remedy")
    private String remedy;

    @NotNull
    @NotEmpty(message = "Mandatory")
    @JsonProperty("duration")
    private String duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPossibleCause() {
        return possibleCause;
    }

    public void setPossibleCause(String possibleCause) {
        this.possibleCause = possibleCause;
    }

    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "PainCaptureForm{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", position='" + position + '\'' +
                ", occurrence='" + occurrence + '\'' +
                ", type='" + type + '\'' +
                ", temperature='" + temperature + '\'' +
                ", possibleCause='" + possibleCause + '\'' +
                ", remedy='" + remedy + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    @Override
    public int compareTo(PainCaptureForm other) {
        return this.toString().compareTo(other.toString());
    }
}
