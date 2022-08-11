package ru.javarush.golf.krivko.islandmodel.entities.gamefield;

import ru.javarush.golf.krivko.islandmodel.constants.Configuration;
import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.EntityType;
import ru.javarush.golf.krivko.islandmodel.entities.animals.Animal;
import ru.javarush.golf.krivko.islandmodel.factories.GeneralFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    private final int xPosition;
    private final int yPosition;

    public double getGrass() {
        return grass;
    }

    public void setGrass(double grass) {
        this.grass = grass;
    }

    private double grass;

    private final Map<EntityType, Set<Entity>> entities = new ConcurrentHashMap<>();//HashMap<>();//-

    public Map<EntityType, Set<Entity>> getEntities() {
        return entities;
    }//++

    public Location(int y, int x) {
        this.yPosition = y;
        this.xPosition = x;
        initializeEntitiesSet();
        generationEntities();
    }

    public int getPositionX() {
        return xPosition;
    }

    public int getPositionY() {
        return yPosition;
    }

    public void doAction() {
        for (Map.Entry<EntityType, Set<Entity>> pair : entities.entrySet()) {
            Set<Entity> value = pair.getValue();
            for (Entity entity : value) {
//                if (entity.getWeight() > (Configuration.CONFIGURATIONS_ANIMALS.get(pair.getKey())[0] / 1.2)) {
                    if (entity instanceof Animal animal) {
                        animal = (Animal) entity;
                        animal.move(this);
                    }
//                } else {
//                    if (animal instanceof Rabbit) {
//                        ((Rabbit) animal).eat(this);
//                    }
//                    if (animal instanceof Wolf) {
//                        ((Wolf) animal).eat(this);
//                    }
//                }
//                if (animal.getWeight() < Configuration.CONFIGURATIONS_ANIMALS.get(animal.getClass())[0] / 2.5) {
//                    this.removeAnimalFromLocation(animal);
//                }
            }
        }
//        grassGrowth();
    }

    private void grassGrowth() {                  //растет трава, методу тут не место!!
        if (this.grass < Configuration.GRASS_WEIGHT) {
            this.grass += 0.1;
        }
    }

    public void removeAnimalFromLocation(Animal animal) {   //++???
        //реализация удаления животного
        entities.get(EntityType.valueOf(animal.getType().toUpperCase())).remove(animal);
    }

    public void addAnimalToLocation(Animal animal) { // Проверить работу метода !!!
        //реализация добавления животного
        EntityType entityType = EntityType.valueOf(animal.getType().toUpperCase());
//        if (entities.get(entityType) != null) {
            entities.get(entityType).add(animal);
//        } else {
//            entities.put(entityType, new HashSet<>()); // не перезаписываю ли я тут сет ???
//            entities.get(entityType).add(animal);
//        }
    }

    @Override
    public String toString() {
        return "[" + "\uD83D\uDC3A" + entities.get(EntityType.WOLF).size()
                + ":\uD83D\uDC07" + entities.get(EntityType.RABBIT).size()
                + ":\uD83C\uDF3F" + String.format("%.2f", grass) + "]";
    }

    private void generationEntities() { // ++
        for (EntityType entityType : EntityType.values()) {
            if (isCreateEntityType()) {
                int numberOfEntityType = ThreadLocalRandom.current().nextInt(0, (int) Configuration.CONFIGURATIONS_ANIMALS.get(entityType)[1]);
                for (int i = 0; i < numberOfEntityType; i++) {
                    Entity entityByType = GeneralFactory.createEntityByType(entityType);
                    entities.get(entityType).add(entityByType);
                }
            }
        }
    }

    private boolean isCreateEntityType() {
        return ThreadLocalRandom.current().nextBoolean();
    }


    private void initializeEntitiesSet() {            //++ Снова вернулся к Конкаренту :(
        for (EntityType entityType : EntityType.values()) {
            //HashSet<Entity> set = new HashSet<>();
            entities.put(entityType, Collections.newSetFromMap(new ConcurrentHashMap<>()));
//            System.out.println(set);
        }
    }
}
