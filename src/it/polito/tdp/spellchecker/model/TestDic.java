package it.polito.tdp.spellchecker.model;

import java.util.LinkedList;
import java.util.List;

public class TestDic {

	public static void main(String[] args) {

		Dictionary d = new Dictionary();
		List<String> l = new LinkedList<String>();
		d.loadDictionary("Italian");
		l.add("ciao");
		l.add("come");
		l.add("stai");
		System.out.println(d.spellCheckText(l).toString());
	}

}
