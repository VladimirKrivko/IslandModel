package ru.javarush.golf.krivko.islandmodel.constants;

import ru.javarush.golf.krivko.islandmodel.entities.animals.birds.Duck;
import ru.javarush.golf.krivko.islandmodel.entities.animals.birds.Eagle;
import ru.javarush.golf.krivko.islandmodel.entities.animals.insects.Caterpillar;
import ru.javarush.golf.krivko.islandmodel.entities.animals.mammals.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Configuration {
    public static final int SIZE_Y_GAME_FIELD = 5;
    public static final int SIZE_X_GAME_FIELD = 10;

//    public static final int MAX_NUMBER_OF_PLANTS_PER_LOCATION = 200;

    public static final int GRASS_WEIGHT = 200; //1

    private Configuration(){
        throw new IllegalStateException("Configuration class");
    }

    /*Для изображения животных можно использовать юникод символы: 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐, 🐺, 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛, 🌿 */

    public static final Set<Class<?>> CLASS_ANIMALS = Set.of(/*Bear.class, Boa.class, Boar.class, Buffalo.class, Deer.class,
            Fox.class, Goat.class, Horse.class, Mouse.class,*/ Rabbit.class, /* Sheep.class,*/ Wolf.class/*, Caterpillar.class,
            Duck.class, Eagle.class*/);

    public static final Map<String, Class<?>> NAME_CLASSES = new HashMap<>();

    static {
        NAME_CLASSES.put("Bear", Bear.class);
        NAME_CLASSES.put("Boa", Boa.class);
        NAME_CLASSES.put("Boar", Boar.class);
        NAME_CLASSES.put("Buffalo", Buffalo.class);
        NAME_CLASSES.put("Deer", Deer.class);
        NAME_CLASSES.put("Fox", Fox.class);
        NAME_CLASSES.put("Goat", Goat.class);
        NAME_CLASSES.put("Horse", Horse.class);
        NAME_CLASSES.put("Mouse", Mouse.class);
        NAME_CLASSES.put("Rabbit", Rabbit.class);
        NAME_CLASSES.put("Sheep", Sheep.class);
        NAME_CLASSES.put("Wolf", Wolf.class);
        NAME_CLASSES.put("Duck", Duck.class);
        NAME_CLASSES.put("Eagle", Eagle.class);
        NAME_CLASSES.put("Caterpillar", Caterpillar.class);
    }


    /* Максимальное количество вида животного в локации. */
    public static final Map<Class<?>, Integer> MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION = new HashMap<>();

    static {
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Bear.class, 5);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Boa.class, 30);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Boar.class, 50);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Buffalo.class, 10);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Deer.class, 20);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Fox.class, 30);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Goat.class, 140);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Horse.class, 20);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Mouse.class, 500);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Rabbit.class, 150);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Sheep.class, 140);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Wolf.class, 30);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Duck.class, 200);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Eagle.class, 20);
        MAX_NUMBER_TYPE_OF_ANIMAL_PER_LOCATION.put(Caterpillar.class, 1000);
    }

    /* Максимальное количество шагов животного в локации. */
    public static final Map<Class<?>, Integer> MAX_NUMBER_OF_ANIMAL_STEPS = new HashMap<>();

    static {
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Bear.class, 2);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Boa.class, 1);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Boar.class, 2);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Buffalo.class, 3);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Deer.class, 4);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Fox.class, 2);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Goat.class, 3);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Horse.class, 4);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Mouse.class, 1);
        MAX_NUMBER_OF_ANIMAL_STEPS.put(Rabbit.class, 2);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Sheep.class, 3);
        MAX_NUMBER_OF_ANIMAL_STEPS.put(Wolf.class, 3);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Duck.class, 4);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Eagle.class, 3);
//        MAX_NUMBER_OF_ANIMAL_STEPS.put(Caterpillar.class, 0);
    }

    protected static final Map<Class<?>, Map<Class<?>, Integer>> PROBABILITY_FOR_EATERS = new HashMap<>();

    private static final Map<Class<?>, Integer> PROBABILITY_FOR_WOLF = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_BOA = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_FOX = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_BEAR = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_EAGLE = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_MOUSE = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_BOAR = new HashMap<>();
    private static final Map<Class<?>, Integer> PROBABILITY_FOR_DUCK = new HashMap<>();

    static {
        /* initialized probability for Wolf */
        PROBABILITY_FOR_WOLF.put(Horse.class, 10);
        PROBABILITY_FOR_WOLF.put(Deer.class, 15);
        PROBABILITY_FOR_WOLF.put(Rabbit.class, 60);
        PROBABILITY_FOR_WOLF.put(Mouse.class, 80);
        PROBABILITY_FOR_WOLF.put(Goat.class, 60);
        PROBABILITY_FOR_WOLF.put(Sheep.class, 70);
        PROBABILITY_FOR_WOLF.put(Boar.class, 15);
        PROBABILITY_FOR_WOLF.put(Buffalo.class, 10);
        PROBABILITY_FOR_WOLF.put(Duck.class, 40);

        /* initialized probability for Boa */
        PROBABILITY_FOR_BOA.put(Fox.class, 15);
        PROBABILITY_FOR_BOA.put(Rabbit.class, 20);
        PROBABILITY_FOR_BOA.put(Mouse.class, 40);
        PROBABILITY_FOR_BOA.put(Duck.class, 10);

        /* initialized probability for Fox */
        PROBABILITY_FOR_FOX.put(Rabbit.class, 70);
        PROBABILITY_FOR_FOX.put(Mouse.class, 90);
        PROBABILITY_FOR_FOX.put(Duck.class, 60);
        PROBABILITY_FOR_FOX.put(Caterpillar.class, 40);

        /* initialized probability for Bear */
        PROBABILITY_FOR_BEAR.put(Boa.class, 80);
        PROBABILITY_FOR_BEAR.put(Horse.class, 40);
        PROBABILITY_FOR_BEAR.put(Deer.class, 80);
        PROBABILITY_FOR_BEAR.put(Rabbit.class, 80);
        PROBABILITY_FOR_BEAR.put(Mouse.class, 90);
        PROBABILITY_FOR_BEAR.put(Goat.class, 70);
        PROBABILITY_FOR_BEAR.put(Sheep.class, 70);
        PROBABILITY_FOR_BEAR.put(Boar.class, 50);
        PROBABILITY_FOR_BEAR.put(Buffalo.class, 20);
        PROBABILITY_FOR_BEAR.put(Duck.class, 10);

        /* initialized probability for Eagle */
        PROBABILITY_FOR_EAGLE.put(Fox.class, 10);
        PROBABILITY_FOR_EAGLE.put(Rabbit.class, 90);
        PROBABILITY_FOR_EAGLE.put(Mouse.class, 90);
        PROBABILITY_FOR_EAGLE.put(Duck.class, 80);

        /* initialized probability for Mouse */
        PROBABILITY_FOR_MOUSE.put(Caterpillar.class, 90);

        /* initialized probability for Boar */
        PROBABILITY_FOR_BOAR.put(Mouse.class, 50);
        PROBABILITY_FOR_BOAR.put(Caterpillar.class, 90);

        /* initialized probability for Duck */
        PROBABILITY_FOR_DUCK.put(Caterpillar.class, 90);

        /* initialized PROBABILITY_FOR_EATERS */
        PROBABILITY_FOR_EATERS.put(Wolf.class, PROBABILITY_FOR_WOLF);
        PROBABILITY_FOR_EATERS.put(Boa.class, PROBABILITY_FOR_BOA);
        PROBABILITY_FOR_EATERS.put(Fox.class, PROBABILITY_FOR_FOX);
        PROBABILITY_FOR_EATERS.put(Bear.class, PROBABILITY_FOR_BEAR);
        PROBABILITY_FOR_EATERS.put(Eagle.class, PROBABILITY_FOR_EAGLE);
        PROBABILITY_FOR_EATERS.put(Mouse.class, PROBABILITY_FOR_MOUSE);
        PROBABILITY_FOR_EATERS.put(Boar.class, PROBABILITY_FOR_BOAR);
        PROBABILITY_FOR_EATERS.put(Duck.class, PROBABILITY_FOR_DUCK);
    }
}
