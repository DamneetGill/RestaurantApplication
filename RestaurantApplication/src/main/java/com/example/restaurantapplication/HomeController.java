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
    public void slideshow(){
        ArrayList<Image> images= new ArrayList<Image>();

        images.add(new Image("5366bec0d07a21700d556c5be7fdc2e4.jpg"));
        images.add(new Image("dawnburrellcooking2.jpg"));
        images.add(new Image("scott-warman-525484-unsplash.jpg"));
        images.add(new Image("stylish-restaurant-interior-for-dinner-and-rest-with-great-cocktails.jpg"));

        Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            HomeImage.setImage(images.get(count));
            count++;
            if(count==4){
                count=0;
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

