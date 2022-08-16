package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

public class AnimalTask {
    private final Animal animal;
    private final Location location;

    public AnimalTask(Animal animal, Location location) {
        this.animal = animal;
        this.location = location;
    }

    public void doAnAction() {         //прописать свою логику поведения животных!!
//        if (animal != null) {
            try {
                animal.eat(location);
            } catch (Exception e) {
                //ничего
            }
            animal.reproduction(location);

            animal.weightLoss(location);
            animal.timeToDie(location);
        try {
            animal.move(location);
        } catch (Exception e) {
            //
        }
//        }
    }
}
