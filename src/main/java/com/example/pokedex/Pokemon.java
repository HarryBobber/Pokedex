package com.example.pokedex;

public class Pokemon {

    private String name;
    private int number;
    private String type;
    private String description;
    private int drawableId;

    public Pokemon(String name, int number, String type, String description, int drawableId){
        this.name = name;
        this.number = number;
        this.type = type;
        this.description = description;
        this.drawableId = drawableId;
    }

    public String getName(){
        return name;
    }
    public int getNumber(){
        return number;
    }
    public String getType(){
        return type;
    }
    public String getDescription(){
        return description;
    }
    public int getDrawableId(){
        return drawableId;
    }
}
