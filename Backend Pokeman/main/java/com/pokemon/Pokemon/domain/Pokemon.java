package com.pokemon.Pokemon.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Pokemon {
    private String name;
    @Id
    private String id;
    private int baseExperience;
    private int height;
    private int order;
    private int weight;

    public Pokemon() {
    }

    public Pokemon(String name, String id, int baseExperience, int height, int order, int weight) {
        this.name = name;
        this.id = id;
        this.baseExperience = baseExperience;
        this.height = height;
        this.order = order;
        this.weight = weight;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}