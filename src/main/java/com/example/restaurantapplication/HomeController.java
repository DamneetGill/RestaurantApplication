package com.example.restaurantapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ImageView HomeImage;
    int count;

    public void slideshow() {
        ArrayList<Image> images = new ArrayList<Image>();

        images.add(new Image("5366bec0d07a21700d556c5be7fdc2e4.jpg"));
        images.add(new Image("dawnburrellcooking2.jpg"));
        images.add(new Image("scott-warman-525484-unsplash.jpg"));
        images.add(new Image("01ee8f5f19585b5aefde1d444bcdbd74.jpg"));
        images.add(new Image("2311a65bd19841a7b43287a3bceb3ba7.jpg"));
        images.add(new Image("be93ef97205d094c368f1d218b7a7bd0.jpg"));
        images.add(new Image("04bef61884051d5695ad0d2ce37632f2.jpg"));
        images.add(new Image("31160834c28e6c10133936f6915b2843.jpg"));
        images.add(new Image("d55b8bf08f4ba29545ef9d3ba236273d.jpg"));
        images.add(new Image("stylish-restaurant-interior-for-dinner-and-rest-with-great-cocktails.jpg"));


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            HomeImage.setImage(images.get(count));
            count++;
            if (count == 10) {
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideshow();
    }
}

