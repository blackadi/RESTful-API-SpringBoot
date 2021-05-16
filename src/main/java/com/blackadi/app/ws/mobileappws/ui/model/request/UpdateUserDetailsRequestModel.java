package com.blackadi.app.ws.mobileappws.ui.model.request;

import javax.validation.constraints.NotNull;

public class UpdateUserDetailsRequestModel {
    @NotNull(message = "First name cannot be null")
    private String fistName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
