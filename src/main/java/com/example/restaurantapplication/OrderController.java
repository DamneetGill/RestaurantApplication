package com.example.restaurantapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private VBox ChosenPlate;

    @FXML
    private ImageView ChosenPlateImage;

    @FXML
    private Label ChosenPlateInfo;

    @FXML
    private Label ChosenPlateName;

    @FXML
    private Label ChosenPlatePrice;
    @FXML
    private GridPane Grid;

    @FXML
    private ScrollPane Scroll;

    private List<Plate> plates=new ArrayList<>();
    private MyListener myListener;
    private Image image;

    private List<Plate> getData(){
        List<Plate> plates=new ArrayList<>();
        Plate plate;

        plate=new Plate();
        plate.setPlateName("Mushroom Bruschetta");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Caramelized mushrooms with garlic and thyme on crispy toasted bread");
        plate.setImageLink("/img/gnocchi.jpeg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Ricotta Cheese Balls");
        plate.setPlatePrice(3.50);
        plate.setPlateInformation("Juicy bites of ricotta flavored with slightly tangy Pecorino cheese and fresh parsley");
        plate.setImageLink("/img/ricotta_cheese_balls.jpeg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Potato Mushroom");
        plate.setPlatePrice(3.50);
        plate.setPlateInformation("Baskets of potatoes stuffed with scamorza cheese and mushrooms");
        plate.setImageLink("/img/potato_mushroom.jpeg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Mushroom Risotto");
        plate.setPlatePrice(12.99);
        plate.setPlateInformation("Risotto with Parmesan cheese, white wine and mushrooms");
        plate.setImageLink("/img/mushroom_risotto.jpeg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Pappardelle With Mushroom");
        plate.setPlatePrice(10.50);
        plate.setPlateInformation("Wide pappardelle pasta with a rosemary mushroom sauce and parmesan");
        plate.setImageLink("/img/pappardelle_mushroom.jpg");
        plates.add(plate);


        plate=new Plate();
        plate.setPlateName("Gnocchi");
        plate.setPlatePrice(11.50);
        plate.setPlateInformation("Homemade potato gnocchi tossed in a simple tomato sauce");
        plate.setImageLink("/img/gnocchi.jpeg");
        plates.add(plate);


        plate=new Plate();
        plate.setPlateName("Rigatoni With Pesto");
        plate.setPlatePrice(10.50);
        plate.setPlateInformation("Creamy pesto rigatoni with chili garlic breadcrumbs");
        plate.setImageLink("/img/pesto_pasta.jpeg");
        plates.add(plate);


        plate=new Plate();
        plate.setPlateName("Lamb");
        plate.setPlatePrice(13.50);
        plate.setPlateInformation("Garlic lamb chops flavored with rosemary accompanied with a red wine sauce");
        plate.setImageLink("/img/lamb.jpeg");
        plates.add(plate);


        plate=new Plate();
        plate.setPlateName("Eggplant Rolls");
        plate.setPlatePrice(8.50);
        plate.setPlateInformation("Thinly sliced eggplant rolled up with ricotta cheese and an herb filling ");
        plate.setImageLink("/img/rolls.jpg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Steak");
        plate.setPlatePrice(14.50);
        plate.setPlateInformation("Pan-seared steak with garlic butter and accompanied with mashed potatoes, broccoli and mushrooms");
        plate.setImageLink("/img/steak_with_mushroom.jpeg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Scallopini");
        plate.setPlatePrice(12.50);
        plate.setPlateInformation("Veal scallopini served with a mushroom sauce");
        plate.setImageLink("/img/scallopina.jpeg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Tiramisu");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Espresso-dipped ladyfingers and a creamy lightly sweetened mascarpone cream");
        plate.setImageLink("/img/tiramisu.jpg");
        plates.add(plate);

        plate=new Plate();
        plate.setPlateName("Italian Trifle");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Layers of sponge cake dipped in Alchermes liqueur altered with pastry cream and blueberries");
        plate.setImageLink("/img/italian_trifle.jpeg");
        plates.add(plate);


        plate=new Plate();
        plate.setPlateName("Blueberry Pie");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Blueberry pie with warm spices, lemon and a lattice crust");
        plate.setImageLink("/img/blueberry_pie.jpg");
        plates.add(plate);


        plate=new Plate();
        plate.setPlateName("Lemon Sorbet");
        plate.setPlatePrice(2.50);
        plate.setPlateInformation("Lemon rosemary sorbet");
        plate.setImageLink("/img/lemon_sorbet.jpg");
        plates.add(plate);

        return plates;
    }

    private void setChosenPlate(Plate plate){
        ChosenPlateName.setText(plate.getPlateName());
        ChosenPlatePrice.setText("€" + Double.toString(plate.getPlatePrice()));
        image = new Image(getClass().getResourceAsStream(plate.getImageLink()));
        ChosenPlateImage.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plates.addAll(getData());
        if (plates.size() > 0) {
            setChosenPlate(plates.get(0));
            MyListener myListener = new MyListener() {
                @Override
                public void onClickListener(Plate plate) {
                    setChosenPlate(plate);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < plates.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("base-overview"));
                AnchorPane anchorPane = fxmlLoader.load();

                PlateController plateController = fxmlLoader.getController();
                plateController.setData(plates.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                Grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                Grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                Grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                Grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                Grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                Grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                Grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
