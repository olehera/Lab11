package it.polito.tdp.bar;

import java.time.LocalTime;

public class Evento implements Comparable<Evento> {
	
	public enum TipoEvento {
		ARRIVO_GRUPPO_CLIENTI,
		PARTENZA_GRUPPO_CLIENTI
	}
	
	private LocalTime tempo;
	private TipoEvento tipo;
	private GruppoClienti clienti;
	
	public Evento(LocalTime tempo, TipoEvento tipo, GruppoClienti clienti) {
		this.tempo = tempo;
		this.tipo = tipo;
		this.setClienti(clienti);
	}

	public LocalTime getTempo() {
		return tempo;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public GruppoClienti getClienti() {
		return clienti;
	}

	public void setClienti(GruppoClienti clienti) {
		this.clienti = clienti;
	}
	
	@Override
	public int compareTo(Evento ev) {
		return this.tempo.compareTo(ev.tempo);
	}
	
}