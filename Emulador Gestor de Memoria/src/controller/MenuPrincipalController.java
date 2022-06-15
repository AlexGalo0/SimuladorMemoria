package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
    public Button irMemFija;
    public Button irMemFijaVar;
    public Button irMemDinamica;
    public Button irMemColegas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clickGoFija(ActionEvent actionEvent) throws IOException {

    FXMLLoader loader= new FXMLLoader(getClass().getResource("../view/memoriaFija.fxml"));
    Parent root=loader.load();
    Scene scene= new Scene(root);
    Stage stage=new Stage();
    stage.setTitle("Memoria Fija");
    stage.setScene(scene);
    stage.show();

    }

    public void clickGoFijaVar(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader= new FXMLLoader(getClass().getResource("../view/memoriaFijaVariable.fxml"));
        Parent root=loader.load();
        Scene scene= new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("Memoria Fija variable");
        stage.setScene(scene);
        stage.show();
    }

    public void clickGoDinamica(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader= new FXMLLoader(getClass().getResource("../view/memoriaDinamica.fxml"));
        Parent root=loader.load();
        Scene scene= new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("Memoria Dinamica");
        stage.setScene(scene);
        stage.show();
    }

    public void clickGoColegas(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader= new FXMLLoader(getClass().getResource("../view/memoriaColegas.fxml"));
        Parent root=loader.load();
        Scene scene= new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("Sistema de Colegas");
        stage.setScene(scene);
        stage.show();
    }


}
