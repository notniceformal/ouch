package com.nnf.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SomethingForm {

    @NotNull()

    @Size(min=4, max=4, message = "Hint: 4 characters")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Something: " + this.name ;
    }
}
