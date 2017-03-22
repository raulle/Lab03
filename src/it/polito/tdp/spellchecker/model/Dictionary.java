package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List<String> dizionario;
	private List<RichWord> risultato;
	
	public Dictionary() {
		this.dizionario = new LinkedList<String>();
		this.risultato = new LinkedList<RichWord>();
	}
	
	public void loadDictionary(String language){
		dizionario.clear();
		if(language=="Italian"){
			try {
				FileReader fr = new FileReader("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			} 
			catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		if(language=="English"){
			try {
				FileReader fr = new FileReader("rsc/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			} 
			catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		risultato.clear();
		for(String p : inputTextList){
			if(dizionario.contains(p))
				risultato.add(new RichWord(p,true));
			else
				risultato.add(new RichWord(p,false));
		}
		return risultato;
	}
	
}
