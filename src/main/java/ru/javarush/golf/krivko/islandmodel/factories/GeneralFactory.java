package ru.javarush.golf.krivko.islandmodel.factories;

import ru.javarush.golf.krivko.islandmodel.entities.Entity;
import ru.javarush.golf.krivko.islandmodel.entities.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GeneralFactory {
    private static volatile GeneralFactory factories;
    private final Map<EntityType, EntityFactory> factoriesMap;

    private GeneralFactory() {
        factoriesMap = new HashMap<>();
//        factoriesMap.put("Bear", new BearFactory());
//        factoriesMap.put("Boa", new BoaFactory());
//        factoriesMap.put("Boar", new BoarFactory());
//        factoriesMap.put("Buffalo", new BuffaloFactory());
//        factoriesMap.put("Deer", new DeerFactory());
//        factoriesMap.put("Fox", new FoxFactory());
//        factoriesMap.put("Goat", new GoatFactory());
//        factoriesMap.put("Horse", new HorseFactory());
//        factoriesMap.put("Mouse", new MouseFactory());
        factoriesMap.put(EntityType.RABBIT, new RabbitFactory());
//        factoriesMap.put("Sheep", new SheepFactory());
        factoriesMap.put(EntityType.WOLF, new WolfFactory());
//        factoriesMap.put("Duck", new DuckFactory());
//        factoriesMap.put("Eagle", new EagleFactory());
//        factoriesMap.put("Caterpillar", new CaterpillarFactory());
        factoriesMap.put(EntityType.GRASS, new GrassFactory());
    }

    private static Map<EntityType, EntityFactory> getFactoriesMap() {
        GeneralFactory factories = GeneralFactory.factories;
        if (Objects.isNull(factories)) {
            synchronized (GeneralFactory.class) {
                if (Objects.isNull(factories = GeneralFactory.factories)) {
                    factories = GeneralFactory.factories = new GeneralFactory();
                }
            }
        }

        return factories.factoriesMap;
    }

    public static Entity createEntityByType(EntityType type) {
        return getFactoriesMap().get(type).createEntity();
    }
}
