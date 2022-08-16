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

    public static final double AMOUNT_OF_GRASS = 200;//200 // Если делать без создания объектов травы, то double!


    private Configuration(){
        throw new IllegalStateException("Configuration class");
    }

    /*Для изображения животных можно использовать юникод символы: 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐, 🐺, 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛, 🌿 */

    public static final Set<Class<?>> CLASS_ANIMALS = Set.of(/*Bear.class, Boa.class,*/ Boar.class, /*Buffalo.class, Deer.class,
            Fox.class, Goat.class, Horse.class, Mouse.class,*/ Rabbit.class,  Sheep.class, Wolf.class, Caterpillar.class/*,
            Duck.class, Eagle.class*/);

    public static final Map<Class<?>, double[]> CONFIGURATIONS_ANIMALS = new HashMap<>();

    static {
        CONFIGURATIONS_ANIMALS.put(Bear.class, new double[]{500, 5, 2, 80});//
        CONFIGURATIONS_ANIMALS.put(Boa.class, new double[]{15, 30, 1, 3});//
        CONFIGURATIONS_ANIMALS.put(Boar.class, new double[]{400, 50, 2, 50});//
        CONFIGURATIONS_ANIMALS.put(Buffalo.class, new double[]{700, 10, 3, 100});//
        CONFIGURATIONS_ANIMALS.put(Deer.class, new double[]{300, 20, 4, 50});//
        CONFIGURATIONS_ANIMALS.put(Fox.class, new double[]{8, 30, 2, 2});//
        CONFIGURATIONS_ANIMALS.put(Goat.class, new double[]{60, 140, 3, 10});//
        CONFIGURATIONS_ANIMALS.put(Horse.class, new double[]{400, 20, 4, 60});//
        CONFIGURATIONS_ANIMALS.put(Mouse.class, new double[]{0.05, 500, 1, 0.01});//
        CONFIGURATIONS_ANIMALS.put(Rabbit.class, new double[]{2, 150, 2, 0.45});//
        CONFIGURATIONS_ANIMALS.put(Sheep.class, new double[]{70, 140, 3, 15});//
        CONFIGURATIONS_ANIMALS.put(Wolf.class, new double[]{50, 30, 3, 8});//
        CONFIGURATIONS_ANIMALS.put(Duck.class, new double[]{1, 200, 4, 0.15});//
        CONFIGURATIONS_ANIMALS.put(Eagle.class, new double[]{6, 20, 3, 1});//
        CONFIGURATIONS_ANIMALS.put(Caterpillar.class, new double[]{0.01, 1000, 0, 0});//
    }

    public static final Map<Class<?>, Map<Class<?>, Integer>> PROBABILITY_FOR_EATERS = new HashMap<>();

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
