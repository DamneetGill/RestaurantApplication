package com.example.restaurantapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlateController {
    @FXML
    private  Label NameLabel;

    @FXML
    private  ImageView PlateImage;

    @FXML
    private  Label PriceLabel;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(plate);
    }

    private Plate plate;
    private MyListener myListener;

    public  void setData(Plate plate, MyListener myListener) {
        this.plate = plate;
        this.myListener = myListener;
        NameLabel.setText(plate.getPlateName());
        PriceLabel.setText("€" + Double.toString(plate.getPlatePrice()));
        Image image = new Image(getClass().getResourceAsStream(plate.getImageLink()));
        PlateImage.setImage(image);
    }

}
