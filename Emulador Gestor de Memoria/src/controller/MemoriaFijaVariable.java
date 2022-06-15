package controller;

import logic.Fijo.Memory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MemoriaFijaVariable implements Initializable {

    @FXML
    private Label errorMessage;
    @FXML
    private TextField txtSizeNewProcess;
    @FXML
    private TextField txtSelectSector;
    @FXML
    private TextField txtSectorProcessToKill;
    @FXML
    private BarChart<String,Number> chartStatusMemory;

    Memory memory=new Memory(128);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memory.setDifferentSectors();
        memory.getNumeroSectoresDiff();

        for (int i = 0; i <memory.getNumeroSectoresDiff() ; i++) {
            XYChart.Series<String,Number> series = new XYChart.Series<>();
            series.setName("S"+i+" "+memory.sectorSizeForDiff(i)+"MB/"+memory.sectorSizeForDiff(i)+"MB");
            series.getData().add(new XYChart.Data<>("Sector"+i,0));
            chartStatusMemory.getData().add(series);
        }

    }

    public void addNewProcess(ActionEvent event) {
        int valueProcessAdd=Integer.parseInt(txtSizeNewProcess.getText());
        int valueIndexSector=Integer.parseInt(txtSelectSector.getText());

        if((valueProcessAdd<=memory.sectorSizeForDiff(valueIndexSector)) && memory.availableSectorDiff(valueIndexSector))
        {
            errorMessage.setText("");
            int residuo=memory.sectorSizeForDiff(valueIndexSector)-valueProcessAdd;
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            memory.putProcessDifferentV2(valueProcessAdd,valueIndexSector);
            series.setName("S"+valueIndexSector+" "+residuo+"MB/"+memory.sectorSizeForDiff(valueIndexSector)+"MB");
            chartStatusMemory.getData().remove(valueIndexSector);
            series.getData().add(new XYChart.Data<>("Sector" + valueIndexSector, valueProcessAdd));
            chartStatusMemory.getData().add(valueIndexSector, series);
        }
        else{
            errorMessage.setText("Entrada Invalida");
        }
    }

    public void endSectorProcess(ActionEvent event) {
        int valueIndexSectorKiller=Integer.parseInt(txtSectorProcessToKill.getText());
        memory.killProcessDifferent(valueIndexSectorKiller);
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("S"+valueIndexSectorKiller+" "+memory.sectorSizeForDiff(valueIndexSectorKiller)+"MB/"
                +memory.sectorSizeForDiff(valueIndexSectorKiller)+"MB");
        chartStatusMemory.getData().remove(valueIndexSectorKiller);
        series.getData().add(new XYChart.Data<>("Sector"+valueIndexSectorKiller,0));
        chartStatusMemory.getData().add(valueIndexSectorKiller,series);
    }
}
