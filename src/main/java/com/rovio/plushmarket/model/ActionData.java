package com.rovio.plushmarket.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionData implements Serializable {
    @JsonProperty("actions")
    private final List<Action> actions;

    @Override
    public String toString() {
        return "ActionData [actions=" + actions + "]";
    }
}
