package ru.javarush.golf.krivko.islandmodel.entities.animals;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.gamefield.Location;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Movable, Cloneable {
    protected Class<? extends Animal> clazz;
    protected double weight;

    protected boolean sex; // если true, то самец!
    public boolean isAte = false; //?



    public double getWeight() {
        return weight;
    }

    public void weightLoss() {
        this.weight -= this.weight/10;
    }

    public void timeToDie(Location location) {
        if (this.weight < Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5) {
            location.removeAnimalFromLocation(this);
        }
    }

    public void reproduction(Location location) {
        //this получает список животных
        Set<Animal> animals = location.getAnimals().get(clazz);
        //TODO: сделать разные полы животных!
        boolean femalePresent = animals.stream().anyMatch(o -> !o.sex);
        if (sex && femalePresent && weight == Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0] && animals.size() > 1) {
//            if (this.weight == Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0] && animals.size() > 1) {
                Animal clone = this.clone();
                location.addAnimalToLocation(clone);
                //потеря веса после спаривания ? пол?
//            }
        }
    }

//    public void eat(Location location) {        //не работает!
//        Map<Class<?>, Integer> probabilityVictimsMap = Configuration.PROBABILITY_FOR_EATERS.get(this.clazz);
//        Iterator<Map.Entry<Class<?>, Integer>> probabilityVictimsMapIterator = probabilityVictimsMap.entrySet().iterator();
//        while(!this.isAte || probabilityVictimsMapIterator.hasNext()) {
//            Map.Entry<Class<?>, Integer> probabilityForEater = probabilityVictimsMapIterator.next();
//            Class<?> victimClass = probabilityForEater.getKey();
//            Integer probabilityToEat = probabilityForEater.getValue();
//            Set<Animal> victims = location.getAnimals().get(victimClass);
//            if(ThreadLocalRandom.current().nextDouble(0, 100) <= probabilityToEat && victims!=null && !victims.isEmpty()) {
//                Iterator<Animal> victimsIterator = victims.iterator();
//                if (victimsIterator.hasNext()) {
//                    Animal victim = victims.stream().findFirst().get();
//                    this.weight = Math.min(this.weight + (victim.weight / 1.5), Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
//                    victimsIterator.remove();
//                    // после того как животное поест this.didTheAnimalEat = true;
//                    this.isAte = true;
//                }
//            }
//        }
//        this.isAte = false;
//    }

    @Override
    public void move(Location location){
        Location newLocation = choiceOfAvailableLocation(location);
        if (newLocation.isThereEnoughSpace(this.clazz)) {
            location.removeAnimalFromLocation(this);
            newLocation.addAnimalToLocation(this);
        }
    }

    private int getMaxNumberOfStepsAnimal() {
        return (int) Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[2];
    }

    private Location choiceOfAvailableLocation(Location location){
        int steps = getMaxNumberOfStepsAnimal();
        for(int i = steps; i >= 0; i--) {
            location = location.getNeighboringLocations().get(ThreadLocalRandom.current().nextInt(0, location.getNeighboringLocations().size()));
        }
        return location;
    }

    @Override
    public Animal clone() {
        try {
            Animal clone = (Animal) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.weight = ThreadLocalRandom.current().nextDouble(Configuration.CONFIGURATIONS_ANIMALS.get(clazz)[0] / 2.5, Configuration.CONFIGURATIONS_ANIMALS.get(this.clazz)[0]);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
