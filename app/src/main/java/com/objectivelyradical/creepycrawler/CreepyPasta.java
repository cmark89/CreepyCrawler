package com.objectivelyradical.creepycrawler;

import java.io.Serializable;
import java.util.ArrayList;

// This is a representation of all the relevant metadata of a single creepypasta
public class CreepyPasta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String title = "";
	String url = "";
	ArrayList<String> categories = new ArrayList<String>();
	int wordCount = 0;
	// NYI:
	int comments = 0;
	
	public CreepyPasta() { }
	public CreepyPasta(String title, String url) {
		this.title = title;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String t){
		title = t;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String u){
		url = u;
	}
	
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> c) {
		categories = c;
	}
	public void addCategory(String c) {
		categories.add(c);
	}
	
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int w) {
		wordCount = w;
	}
}