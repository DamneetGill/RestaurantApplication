package com.example.restaurantapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;
import java.util.Objects;

public class BaseSummaryController {
    @FXML
    private Label orderedNameLabel;

    @FXML
    private Label orderedPriceLabel;

    @FXML
    private Label orderedQuantityLabel;
    @FXML
    private ImageView orderedImageView;

    private Plate plate;

    public void setOrderedData(Plate plate, int count) {
        this.plate = plate;
        orderedNameLabel.setText(plate.getPlateName());
        String price = String.format(Locale.US, "%.2f", plate.getPlatePrice());
        orderedPriceLabel.setText("€" + price);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(plate.getImageLink())));
        orderedImageView.setImage(image);
        orderedQuantityLabel.setText("x " + Integer.toString(count));
    }

}
