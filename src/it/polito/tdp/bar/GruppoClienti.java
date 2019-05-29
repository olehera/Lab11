package it.polito.tdp.bar;

import java.time.Duration;

public class GruppoClienti {
	
	private int numPersone;
	private Duration durata;
	private float tolleranza;
	private Tavolo tavolo;
	
	public GruppoClienti(int numPersone, Duration durata, float tolleranza) {
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

}