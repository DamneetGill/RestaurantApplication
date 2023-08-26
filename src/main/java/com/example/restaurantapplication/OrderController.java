package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    private Button MinusButton;
    @FXML
    private Label PlateQuantity;
    @FXML
    private Button PlusButton;
    @FXML
    private Label TotalPrice;
    @FXML
    private Button CancelOrderButton;
    @FXML
    private ScrollPane Scroll;
    @FXML
    private Button button_continue;
    private List<Plate> plates = new ArrayList<>();
    private MyListener myListener;
    int plateQuantity = 0;
    boolean firstPlate=false;

    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    private List<Plate> getData() {
        List<Plate> plates = new ArrayList<>();

        Plate plate = new Plate();
        plate.setPlateName("Mushroom Bruschetta");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Caramelized mushrooms with garlic and thyme on crispy toasted bread with an herbed ricotta spread");
        plate.setImageLink("/com/example/restaurantapplication/img/mushroom_bruschetta.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Ricotta Cheese Balls");
        plate.setPlatePrice(3.50);
        plate.setPlateInformation("Juicy bites of ricotta flavored with slightly tangy Pecorino cheese and fresh parsley");
        plate.setImageLink("/com/example/restaurantapplication/img/ricotta_cheese_balls.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Potato Mushroom");
        plate.setPlatePrice(3.50);
        plate.setPlateInformation("Baskets of potatoes stuffed with scamorza cheese and mushrooms");
        plate.setImageLink("/com/example/restaurantapplication/img/potato_mushroom.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Apple Ricotta Baskets");
        plate.setPlatePrice(3.50);
        plate.setPlateInformation("Baskets of bread, filled with ricotta and roasted apple ");
        plate.setImageLink("/com/example/restaurantapplication/img/ricotta_mela.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Mushroom Risotto");
        plate.setPlatePrice(12.99);
        plate.setPlateInformation("Risotto with Parmesan cheese, white wine and mushrooms");
        plate.setImageLink("/com/example/restaurantapplication/img/mushroom_risotto.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Spaghetti");
        plate.setPlatePrice(9.50);
        plate.setPlateInformation("Simple spaghetti with cherry tomatoes and roe");
        plate.setImageLink("/com/example/restaurantapplication/img/spaghetti.png");
        plates.add(plate);


        plate = new Plate();
        plate.setPlateName("Gnocchi");
        plate.setPlatePrice(11.50);
        plate.setPlateInformation("Homemade potato gnocchi tossed in a simple tomato sauce");
        plate.setImageLink("/com/example/restaurantapplication/img/gnocchi.png");
        plates.add(plate);


        plate = new Plate();
        plate.setPlateName("Rigatoni With Pesto");
        plate.setPlatePrice(10.50);
        plate.setPlateInformation("Creamy pesto rigatoni with chili garlic breadcrumbs");
        plate.setImageLink("/com/example/restaurantapplication/img/pesto_pasta.png");
        plates.add(plate);


        plate = new Plate();
        plate.setPlateName("Lamb");
        plate.setPlatePrice(13.50);
        plate.setPlateInformation("Garlic lamb chops flavored with rosemary accompanied with a red wine sauce");
        plate.setImageLink("/com/example/restaurantapplication/img/lamb.png");
        plates.add(plate);


        plate = new Plate();
        plate.setPlateName("Eggplant Rolls");
        plate.setPlatePrice(8.50);
        plate.setPlateInformation("Thinly sliced eggplant rolled up with ricotta cheese and an herb filling ");
        plate.setImageLink("/com/example/restaurantapplication/img/rolls.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Steak");
        plate.setPlatePrice(14.50);
        plate.setPlateInformation("Pan-seared steak with garlic butter and accompanied with mashed potatoes, broccoli and mushrooms");
        plate.setImageLink("/com/example/restaurantapplication/img/steak_with_mushroom.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Scallopini");
        plate.setPlatePrice(12.50);
        plate.setPlateInformation("Veal scallopini served with a mushroom sauce");
        plate.setImageLink("/com/example/restaurantapplication/img/scalloppina.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Tiramisu");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Espresso-dipped ladyfingers and a creamy lightly sweetened mascarpone cream");
        plate.setImageLink("/com/example/restaurantapplication/img/tiramisu.png");
        plates.add(plate);

        plate = new Plate();
        plate.setPlateName("Italian Trifle");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Layers of sponge cake dipped in Alchermes liqueur altered with pastry cream and blueberries");
        plate.setImageLink("/com/example/restaurantapplication/img/italian_trifle.png");
        plates.add(plate);


        plate = new Plate();
        plate.setPlateName("Blueberry Pie");
        plate.setPlatePrice(4.50);
        plate.setPlateInformation("Blueberry pie with warm spices, lemon and a lattice crust");
        plate.setImageLink("/com/example/restaurantapplication/img/blueberry_pie.png");
        plates.add(plate);


        plate = new Plate();
        plate.setPlateName("Lemon Sorbet");
        plate.setPlatePrice(2.50);
        plate.setPlateInformation("Lemon rosemary sorbet");
        plate.setImageLink("/com/example/restaurantapplication/img/lemon_sorbet.png");
        plates.add(plate);
        return plates;
    }

    private void setChosenPlate(Plate plate) {
        ChosenPlateName.setText(plate.getPlateName());
        String price = String.format(Locale.US, "%.2f", plate.getPlatePrice());
        ChosenPlatePrice.setText(price);
        Image image = new Image(plate.getImageLink());
        image = new Image(getClass().getResourceAsStream(plate.getImageLink()));
        ChosenPlateImage.setImage(image);
        ChosenPlateInfo.setText(plate.getPlateInformation());
        ChosenPlateInfo.setWrapText(true);
        plateQuantity = 0;
        PlateQuantity.setText(Integer.toString(0));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plates.addAll(getData());
        setChosenPlate(plates.get(0));
        MyListener myListener = new MyListener() {
            @Override
            public void onClickListener(Plate plate) {
                setChosenPlate(plate);
            }
        };

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < plates.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("base-overview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PlateController plateController = fxmlLoader.getController();
                plateController.setData(plates.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                column++;
                Grid.add(anchorPane, column, row); //(child,column,row)
                //set grid width
                Grid.setMinWidth(Region.USE_PREF_SIZE);
                Grid.setPrefWidth(Region.USE_PREF_SIZE);
                Grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                Grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                Grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                Grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        button_continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("summary-overview.fxml"));
                try {
                    Parent root=(Parent)loader.load();
                    SummaryController summaryController=loader.<SummaryController>getController();
                    summaryController.setSubtotalLabel(TotalPrice.getText().toString());
                    summaryController.setTotalLabel(TotalPrice.getText().toString());
                    summaryController.setUsernameLabel(username);
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void handlePlusButton(ActionEvent e) {
        if (plateQuantity < 9) {
            plateQuantity++;
            PlateQuantity.setText(Integer.toString(plateQuantity));
        }
    }

    public void handleMinusButton(ActionEvent e) {
        if (plateQuantity > 0) {
            plateQuantity--;
            PlateQuantity.setText(Integer.toString(plateQuantity));
        }
    }

    public void handleAddToCartButton(ActionEvent e) {
    if(TotalPrice.getText().matches("0")){
        firstPlate=true;
        //System.out.println(firstPlate);

    }
        Double currentTotal = Double.parseDouble(TotalPrice.getText().toString());
        Integer quantity = Integer.parseInt(PlateQuantity.getText().toString());
        Double plateTotal = Double.parseDouble(ChosenPlatePrice.getText().toString());
        plateTotal *= quantity;
        currentTotal += plateTotal;

        String current = String.format(Locale.US, "%.2f", currentTotal);
        TotalPrice.setText(current);
    }

    public void handleCancelOrderButton(ActionEvent e) {
        TotalPrice.setText("0.00");
    }
}