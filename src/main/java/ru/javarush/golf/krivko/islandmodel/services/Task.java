package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public class Task {
    private final Animal animal;
    private final Location location;

    public Task(Animal animal, Location location) {
        this.animal = animal;
        this.location = location;
    }

    public void doAnAction() {         //прописать свою логику поведения животных!!
//        if (animal.eat(location)) {
//            animal.growUp(location);
//            animal.multiply(location);
//        }
        animal.move(location);
        animal.weightLoss();
//        animal.growUp(location);
//        animal.multiply(location);

//        animal.starve(location);
        animal.timeToDie(location);
    }
}
