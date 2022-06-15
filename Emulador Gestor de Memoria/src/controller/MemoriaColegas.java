package controller;


import logic.Colegas.Metodos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MemoriaColegas implements Initializable {

    @FXML
    private Label statusProcessMade;
    @FXML
    private Label statusMemorySize;
    @FXML
    private TextField txtProcessSize;
    @FXML
    private TextField txtSpaceMemory;

    Metodos metodos=new Metodos();
    Metodos.Buddy Tmpobj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusMemorySize.setText("Tamaño: Indefinido");
    }

    public void setMemorySize(ActionEvent event) {
        int memorySize=Integer.parseInt(txtSpaceMemory.getText());
        Metodos.Buddy obj=new Metodos.Buddy(memorySize);
        Tmpobj = obj;
        statusMemorySize.setText("Tamaño: "+txtSpaceMemory.getText()+"MB");

    }


    public void addProcess(ActionEvent event) {
        int process=Integer.parseInt(txtProcessSize.getText());
        Tmpobj.allocate(process);
        statusProcessMade.setText(Tmpobj.getResultadoAsignar());

    }

    public void quitProcess(ActionEvent event) {
        int process=Integer.parseInt(txtProcessSize.getText());
        Tmpobj.deallocate(process);
        statusProcessMade.setText(Tmpobj.getResultadoDesasignar());

    }

  
}
