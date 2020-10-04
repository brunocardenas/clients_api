package com.clients.api.dto.name;

import java.util.List;

public class PersonalNamesGender {

    private List<NameGender> personalNames;

    public List<NameGender> getPersonalNames() {
        return personalNames;
    }

    public PersonalNamesGender setPersonalNames(List<NameGender> personalNames) {
        this.personalNames = personalNames;
        return this;
    }
}
