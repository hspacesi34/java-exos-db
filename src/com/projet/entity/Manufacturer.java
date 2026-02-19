package com.projet.entity;

public class Manufacturer extends AbstractEntity {
    private int id;
    private String name;

    public Manufacturer() {
    }
    public Manufacturer(String name) {
        this.name = name;
    }
    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
