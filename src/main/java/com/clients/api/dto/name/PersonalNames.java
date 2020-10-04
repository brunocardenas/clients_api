package com.clients.api.dto.name;

import java.util.List;

public class PersonalNames {

    private List<NameProcedence> personalNames;

    public List<NameProcedence> getPersonalNames() {
        return personalNames;
    }

    public PersonalNames setPersonalNames(List<NameProcedence> personalNames) {
        this.personalNames = personalNames;
        return this;
    }
}
