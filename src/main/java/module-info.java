module ru.javarush.golf.krivko.islandmodel.islandmodel {

    opens ru.javarush.golf.krivko.islandmodel to javafx.fxml;
    exports ru.javarush.golf.krivko.islandmodel;
    exports ru.javarush.golf.krivko.islandmodel.worldgeneration;
    opens ru.javarush.golf.krivko.islandmodel.worldgeneration to javafx.fxml;
}