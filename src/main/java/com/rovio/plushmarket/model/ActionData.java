package com.rovio.plushmarket.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ActionData implements Serializable {
    private List<Action> actions;
}
