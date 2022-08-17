package ru.javarush.golf.krivko.islandmodel.services;

import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Omnivores;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class AnimalTask {
    private final Animal animal;
    private final Location location;

    public AnimalTask(Animal animal, Location location) {
        this.animal = animal;
        this.location = location;
    }

    public void doAnAction() {
        try {
            if(animal instanceof Carnivorous carnivorous) {
                carnivorous.eat(location);
            }
            if (animal instanceof Herbivorous herbivorous) {
                herbivorous.eat(location);
            }
            if (animal instanceof Omnivores omnivores) {
                omnivores.eat(location);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (Randomizer.getRandom()) {
            animal.reproduction(location);
//            System.out.println(animal.getClass().getSimpleName());
        }
        animal.weightLoss(location);
        animal.timeToDie(location);
        try {
            animal.move(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
