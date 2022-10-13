package com.rovio.plushmarket.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Plush {
    @JsonProperty("plush")
    private String plush;

    @Override
    public String toString() {
        return "Plush [plush=" + plush + "]";
    }
}
