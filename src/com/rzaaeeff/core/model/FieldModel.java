package com.rzaaeeff.core.model;

import com.rzaaeeff.core.Internals;

import java.util.List;

/**
 * Created by Rzaaeeff on 3/7/2017.
 */
public class FieldModel<T extends Object> {

    private String name;
    private Internals.Type type;
    private T value;
    private List<Internals.Modifier> modifiers;

    public FieldModel() {}

    public FieldModel(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Internals.Type getType() {
        return type;
    }

    public void setType(Internals.Type type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
