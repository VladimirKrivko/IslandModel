package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Carnivorous;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Herbivorous;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

public class Boar extends Animal implements Herbivorous, Carnivorous {
    public Boar() {
        this.clazz = Wolf.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
    }

    @Override
    public void eat(Location location) {
        Herbivorous.super.eat(location);
        Carnivorous.super.eat(location);
    }
}
