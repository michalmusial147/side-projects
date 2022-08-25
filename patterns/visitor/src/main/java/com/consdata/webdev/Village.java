package com.consdata.webdev;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Village {
    private List<Person> villagers;

    public void accept(Visitor visitor) {
        for (final Person villager : villagers) {
            villager.accept(visitor);
        }
    }
}
