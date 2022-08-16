package ru.javarush.golf.krivko.islandmodel.entities.animals.mammals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;
import ru.javarush.golf.krivko.islandmodel.utility.Randomizer;

import java.util.Iterator;
import java.util.Set;

public class Wolf extends Animal {

    public static final double SATIATION = Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[3];

    public Wolf() {
        this.clazz = Wolf.class;
        this.sex = Randomizer.getRandom();
        this.currentWeight = Randomizer.getRandom(Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0] / 2, Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
        this.isAte = false;
    }

    @Override
    public void eat(Location location) {
        location.getLock().lock();
        try {
            Set<Animal> rabbits = location.getAnimals().get(Rabbit.class);
            Iterator<Animal> rabbitsIterator = rabbits.iterator();
            while (rabbitsIterator.hasNext() || !isAte) {
                double startingWeightWolf = currentWeight;
                Animal rabbit = rabbitsIterator.next();

                currentWeight = Math.min(currentWeight + rabbit.getCurrentWeight(), Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]);
                if (currentWeight >= startingWeightWolf + SATIATION || currentWeight == Configuration.CONFIGURATIONS_ANIMALS.get(Wolf.class)[0]) {
                    isAte = true;
                }

//                location.removeAnimalFromLocation(rabbit);
                rabbitsIterator.remove();

//                Animal rabbit = rabbits.stream().findFirst().get();
//                this.currentWeight = Math.min(this.currentWeight + (rabbit.getCurrentWeight() / 1.5), Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
//                rabbits.remove(rabbit);
//            this.isAte = false;
            }
            this.isAte = false;

        } finally {
            location.getLock().unlock();
        }
    }
}