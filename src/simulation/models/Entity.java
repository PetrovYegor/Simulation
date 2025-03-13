package simulation.models;

import simulation.Coordinates;

//Корневой абстрактный класс для всех существ и объектов существующих в симуляции.
public abstract class Entity {
    public Coordinates coordinates;
    public Entity (Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinate(){
        return coordinates;
    }

    public void setCoordinate(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
