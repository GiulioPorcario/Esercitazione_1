/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.esercitazione_1_indovina_numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController 
{
   private int TMAX=8;
   private final int NMAX=100;
   private int segreto;
   private int tentativiFatti;
   double avanzamento;
   double progress;
   //private int vettore[];
   //private int i;
   
   @FXML // fx:id="btnAbbandona"
   private Button btnAbbandona; // Value injected by FXMLLoader

   @FXML // fx:id="hboxLivello"
   private HBox hboxLivello; // Value injected by FXMLLoader
   
   @FXML // fx:id="progressBar"
   private ProgressBar progressBar; // Value injected by FXMLLoader

   @FXML // fx:id="btnDifficile"
   private Button btnDifficile; // Value injected by FXMLLoader

   @FXML // fx:id="btnFacile"
   private Button btnFacile; // Value injected by FXMLLoader

   @FXML // fx:id="btnMedio"
   private Button btnMedio; // Value injected by FXMLLoader
   
    @FXML // fx:id="HboxTentativi"
    private HBox HboxTentativi; // Value injected by FXMLLoader
   
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="BtnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="TxtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtMessaggio"
    private TextArea txtMessaggio; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativiRimasti"
    private TextField txtTentativiRimasti; // Value injected by FXMLLoader
    
    @FXML
    void DoTentativo(ActionEvent event) 
    {
    	String ts = txtTentativo.getText();
    	int t;
    	try {
        t= Integer.parseInt(ts);
    	}catch (NumberFormatException e) {
    		 txtMessaggio.setText("Inserisci un numero tra 1 e 100, grazie");
    		 return;
    	     }
    	if (t>100||t<0)
    	{
    	txtMessaggio.setText("Inserisci un numero tra 1 e 100, grazie");
   		 return;
    	}
    		
    	tentativiFatti++;
    	
    	if(t == segreto)
    	{
    		txtMessaggio.setText("Hai Indovinato con "+ tentativiFatti+ " tentativi!");
    		HboxTentativi.setDisable(true);
    		hboxLivello.setDisable(true);
    		btnNuovaPartita.setDisable(false);
    		return;
    	}
    	
    	if(tentativiFatti==TMAX)
    	{
    		progress+=avanzamento;
        	progressBar.setProgress(progress);
    		txtMessaggio.setText("Hai perso \n Il numero da indovinare era " + segreto);
    		HboxTentativi.setDisable(true);
    		hboxLivello.setDisable(true);
    		btnNuovaPartita.setDisable(false);
    		return;
    	}
    	
    	if(t < segreto)
    	{txtMessaggio.setText("Numero troppo basso");
    	}else {txtMessaggio.setText("numero troppo alto");}
    	
    	//vettore[i]=t;
    	//i++;
    	progress+=avanzamento;
    	progressBar.setProgress(progress);
    	txtTentativiRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    	txtTentativo.clear();
    }
    		
  

    @FXML
    void FaiNuovaPartita(ActionEvent event) 
    {
    	//i=0;
    	avanzamento=0.0;
    	progress=0.0;
    	progressBar.setProgress(progress);
    	txtTentativiRimasti.setText("0");
    	txtMessaggio.clear();
    	txtTentativo.setText(""+TMAX);
        tentativiFatti=0;
        segreto= (int)((Math.random()*NMAX)+1);
        txtTentativiRimasti.setText(Integer.toString(TMAX));
        txtTentativo.clear();
        btnFacile.setDisable(false);
        btnMedio.setDisable(false);
        btnDifficile.setDisable(false);
        hboxLivello.setDisable(false);
        //hboxLivello.setDisable(false);
    }
    
    @FXML
    void DoAbbandona(ActionEvent event) 
    {
       HboxTentativi.setDisable(true);
       txtMessaggio.setText("Hai abbandonato");
       hboxLivello.setDisable(true);
       btnNuovaPartita.setDisable(false);
    }
    
    @FXML
    void schiaccioDifficile(ActionEvent event) 
    {
      TMAX=5;
      txtTentativiRimasti.setText("5");
      btnFacile.setDisable(true);
      btnMedio.setDisable(true);
      HboxTentativi.setDisable(false);
      btnNuovaPartita.setDisable(true);
      avanzamento=0.20;
    }

    @FXML
    void schiaccioFacile(ActionEvent event) 
    {
     TMAX=12;
     txtTentativiRimasti.setText("12");
     btnDifficile.setDisable(true);
     btnMedio.setDisable(true);
     HboxTentativi.setDisable(false);
     btnNuovaPartita.setDisable(true);
     avanzamento=0.083;
     }

    @FXML
    void schiaccioMedio(ActionEvent event) 
    {
    	TMAX=8;
    	txtTentativiRimasti.setText("8");
        btnDifficile.setDisable(true);
        btnFacile.setDisable(true);
        HboxTentativi.setDisable(false);
        btnNuovaPartita.setDisable(true);
        avanzamento=0.125;
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"BtnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"TxtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMessaggio != null : "fx:id=\"txtMessaggio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativiRimasti != null : "fx:id=\"txtTentativiRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAbbandona != null : "fx:id=\"btnAbbandona\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDifficile != null : "fx:id=\"btnDifficile\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnFacile != null : "fx:id=\"btnFacile\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMedio != null : "fx:id=\"btnMedio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert hboxLivello != null : "fx:id=\"hboxLivello\" was not injected: check your FXML file 'Scene.fxml'.";
        assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'Scene.fxml'.";
    }

}
