package controller;

import logic.Fijo.Memory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MemoriaFija extends JPanel implements Initializable{


    public Label mensaje;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField txtkillProcess;
    @FXML
    private Text spacePerSector;
    @FXML
    private TextField seleccionSector;
    @FXML
    private BarChart<String,Number> chartStatusMemory;
    @FXML
    private Button botonAgregar;
    @FXML
    private TextField txtAgregar;

    Memory memory=new Memory(128);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        memory.setEqualSectors();
        memory.getNumeroSectores();
        spacePerSector.setText("Espacio por sector: "+memory.sectorSizeforEquals()+"MB");

        for (int i = 0; i <memory.getNumeroSectores() ; i++) {
            XYChart.Series<String,Number> series = new XYChart.Series<>();
            series.setName("Sector"+i);
            series.getData().add(new XYChart.Data<>("Sector"+i,0));
            chartStatusMemory.getData().add(series);
        }
    }


    public void clickBotonAgregar(ActionEvent event) {

        int valueProcessAdd=Integer.parseInt(txtAgregar.getText());
        int valueIndexSector=Integer.parseInt(seleccionSector.getText());

        if((valueProcessAdd<=memory.sectorSizeforEquals()) && memory.availableSector(valueIndexSector)) {

            XYChart.Series<String, Number> series = new XYChart.Series<>();

            memory.putProcessEqualV2(valueProcessAdd, valueIndexSector);

            series.setName("Sector" + valueIndexSector);

            chartStatusMemory.getData().remove(valueIndexSector);

            series.getData().add(new XYChart.Data<>("Sector" + valueIndexSector, valueProcessAdd));
            chartStatusMemory.getData().add(valueIndexSector, series);

            //chartStatusMemory.getData().add(series);
            errorMessage.setText("");
            memory.getEqualSectors();
            System.out.println("//////////////////////////////////////////////////////////////////");
        }
        else{
            errorMessage.setText("Entrada invalida");
        }
    }


    public void killProcessSector(ActionEvent event) {

        int valueIndexSectorKiller=Integer.parseInt(txtkillProcess.getText());
        memory.killProcessEqual(valueIndexSectorKiller);

        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("Sector"+valueIndexSectorKiller);
        chartStatusMemory.getData().remove(valueIndexSectorKiller);

        series.getData().add(new XYChart.Data<>("Sector"+valueIndexSectorKiller,0));
        chartStatusMemory.getData().add(valueIndexSectorKiller,series);

        memory.getEqualSectors();
        System.out.println("//////////////////////////////////////////////////////////////////");

    }
}
