package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {
	
	Dictionary dizionario;
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtInput"
    private TextField txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="txtInfo"
    private Label txtInfo; // Value injected by FXMLLoader
	
    @FXML // fx:id="txtTempo"
    private Label txtTempo; // Value injected by FXMLLoader
    
    @FXML // fx:id="cmbLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader
	
    @FXML
    void doSpellCheck(ActionEvent event){
    	long t1=System.nanoTime();
    	String testo=txtInput.getText().toLowerCase();
    	int i=0;
    	dizionario.loadDictionary(cmbLanguage.getValue());
    	List<String> l= new LinkedList<String>();
    	StringTokenizer st = new StringTokenizer(testo," ");
    	while(st.hasMoreTokens()){
    		l.add(st.nextToken().replaceAll("[ \\p{Punct}]",""));
    	}
    	List<RichWord> r= dizionario.spellCheckText(l);
    	for(RichWord w : r){
    		if(!w.isCorrect()){
    			txtResult.appendText(w.getParola()+"\n");
    			i++;
    		}
    	}
		txtInfo.setText(String.format("The text contains %d errors", i));
		long t2 = System.nanoTime()-t1;
		txtTempo.setText(String.format("Spell Check completed in %d seconds", t2));
    }
    
    @FXML
    void doClearText(ActionEvent event){
    	txtResult.clear();
    	txtInput.clear();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInfo != null : "fx:id=\"txtInfo\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        cmbLanguage.getItems().addAll("English","Italian");
    }
        
    public void setDizionario(Dictionary dizionario) {
		this.dizionario = dizionario;
	}
}
