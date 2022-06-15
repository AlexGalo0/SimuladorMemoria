package controller;

import logic.Dynamic.Memory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class MemoriaDinamica implements Initializable {

    public TextField txtKillProcess;
    @FXML
    private TextField boxSpaceMemory;
    @FXML
    private TextField boxAgregarProcesos;

    public Button buttonAgregarProceso;

    public Label verEspacioMemoria;
    @FXML
    private PieChart pieChartStatusMemory;
    @FXML
    private ChoiceBox<String> selectAlgoritmo;
    private String[] opciones={"primer ajuste","mejor ajuste","siguiente ajuste"};

    Memory memory;

    int contador=0;
    int freeSpace;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectAlgoritmo.getItems().addAll(opciones);
        ObservableList<PieChart.Data> pieChartStatus=FXCollections.observableArrayList(
               // new PieChart.Data("libre "+5,128),
                new PieChart.Data("S",50));
        memory=new Memory(128);
        freeSpace=128;
        verEspacioMemoria.setText("Espacio:" + memory.getSize()+" MB");

        pieChartStatusMemory.getData().add(0,new PieChart.Data("free: "+
                memory.getSize()+" MB",memory.getSize()));



    }


    public void setEspacio(ActionEvent event) {

        int space=Integer.parseInt(boxSpaceMemory.getText());
    memory=new Memory(space);
        pieChartStatusMemory.getData().set(0,new PieChart.Data("free: "+
                memory.getSize()+" MB",memory.getSize()));
    freeSpace=space;

    System.out.print("Actual"+freeSpace+"MB");

    verEspacioMemoria.setText("Espacio:" +space+" MB");

    }

    public void agregarProceso(ActionEvent event) {

        int process=Integer.parseInt(boxAgregarProcesos.getText());
        memory.getMemory();

        switch (selectAlgoritmo.getValue()){

            case "mejor ajuste":
                System.out.print("mejor");



                if(process>freeSpace)

                    break;

                memory.bestFit(process);
                memory.getMemory();

                freeSpace=freeSpace-process;

                pieChartStatusMemory.getData().set(0,new PieChart.Data("free: "+freeSpace+"MB",freeSpace));
                pieChartStatusMemory.getData().add(new PieChart.Data("P"+contador+":"+process+"MB",process));
                contador++;
                System.out.print("Actual"+freeSpace+"MB");


                break;

            case "primer ajuste":
                System.out.print("primer");

                if(process>freeSpace){


                    break;}

                else{
                memory.firstFit(process);
                memory.getMemory();
                freeSpace=freeSpace-process;


                pieChartStatusMemory.getData().set(0,new PieChart.Data("free: "+freeSpace+"MB",freeSpace));
                pieChartStatusMemory.getData().add(new PieChart.Data("P"+contador+":"+process+"MB",process));
                contador++;
                break;}

            case "siguiente ajuste":

                System.out.print("siguiente");


                if(process>freeSpace)

                    break;

                memory.nextFit(process);
                memory.getMemory();
                freeSpace=freeSpace-process;

                pieChartStatusMemory.getData().set(0,new PieChart.Data("free: "+freeSpace+"MB",freeSpace));
                pieChartStatusMemory.getData().add(new PieChart.Data("P"+contador+":"+process+"MB",process));
                contador++;

                break;
        }
    }

    public void endProcess(ActionEvent event) {
        int indexProcessKill=Integer.parseInt(txtKillProcess.getText());
        //ANTES DE TERMINAR
        int tmp= ((int) pieChartStatusMemory.getData().get(indexProcessKill + 1).getPieValue());
        freeSpace=freeSpace+tmp;
        pieChartStatusMemory.getData().set(0,new PieChart.Data("free: "+freeSpace+"MB",freeSpace));
        int indexSearchKillData=pieChartStatusMemory.getData().indexOf(indexProcessKill+1);

        pieChartStatusMemory.getData().set(indexProcessKill+1,new PieChart.Data("P"+indexProcessKill+"(free): "+tmp+"MB",0));
        memory.killProcess(indexProcessKill);





        memory.getMemory();
    }
}
