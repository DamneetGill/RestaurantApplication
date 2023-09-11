package com.example.restaurantapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Locale;
import java.util.Objects;

public class PlateController {
    @FXML
    private ImageView img_plate;

    @FXML
    private Label label_name;

    @FXML
    private Label label_price;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(plate);
    }

    private Plate plate;
    private MyListener myListener;

    public void setData(Plate plate, MyListener myListener) {
        this.plate = plate;
        this.myListener = myListener;
        label_name.setText(plate.getPlateName());
        String labelPrice = String.format(Locale.US, "%.2f", plate.getPlatePrice());
        label_price.setText("€" + labelPrice);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(plate.getImageLink())));
        img_plate.setImage(image);
    }
}
