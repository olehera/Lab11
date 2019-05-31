package it.polito.tdp.bar;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.Evento.TipoEvento;

public class Simulatore {
	
	private PriorityQueue<Evento> queue = new PriorityQueue<>();
	
	// Stato del mondo 
	private List<Tavolo> tavoli;
	
	// Parametri di simulazione
	private LocalTime oraInizio;
	private Random rand;
	
	// Statistiche raccolte
	private int numClienti;
	private int numSoddisfatti;
	private int numInsoddisfatti;
	
	public Simulatore() {
		oraInizio = LocalTime.of(8, 0);
		
		rand = new Random();
		
		tavoli = new ArrayList<>();
		tavoli.add(new Tavolo(1, 10));
		tavoli.add(new Tavolo(2, 10));
		tavoli.add(new Tavolo(3, 8));
		tavoli.add(new Tavolo(4, 8));
		tavoli.add(new Tavolo(5, 8));
		tavoli.add(new Tavolo(6, 8));
		tavoli.add(new Tavolo(7, 6));
		tavoli.add(new Tavolo(8, 6));
		tavoli.add(new Tavolo(9, 6));
		tavoli.add(new Tavolo(10, 6));
		tavoli.add(new Tavolo(11, 4));
		tavoli.add(new Tavolo(12, 4));
		tavoli.add(new Tavolo(13, 4));
		tavoli.add(new Tavolo(14, 4));
		tavoli.add(new Tavolo(15, 4));
		
		Collections.sort(tavoli);
	}
	
	public void init() {
		numClienti = 0;
		numSoddisfatti = 0;
		numInsoddisfatti = 0;
		
		this.queue.clear();
		
		for (LocalTime ora = oraInizio; queue.size() < 2000 ; ora.plus(Duration.ofMinutes(rand.nextInt(9)+1))) {
			GruppoClienti gc = new GruppoClienti(rand.nextInt(9)+1, Duration.ofMinutes(rand.nextInt(60)+60), rand.nextFloat());
			queue.add(new Evento(ora, TipoEvento.ARRIVO_GRUPPO_CLIENTI, gc));
		}
		
	}
	
	public void run() {
		
		while ( !queue.isEmpty() ) {
			Evento ev = queue.poll();
			
			switch ( ev.getTipo() ) {
			
			case ARRIVO_GRUPPO_CLIENTI:
				numClienti += ev.getClienti().getNumPersone();
				boolean bancone = true;
				
				for (Tavolo t : tavoli)
					if ( !t.isOccupato() && ev.getClienti().getNumPersone() <= t.getPosti() && ev.getClienti().getNumPersone() >= t.getPosti()/2 ) { 
						t.setOccupato(true);
						ev.getClienti().setTavolo(t);
						bancone = false;
						numSoddisfatti += ev.getClienti().getNumPersone();
						queue.add(new Evento(ev.getTempo().plus(ev.getClienti().getDurata()), TipoEvento.PARTENZA_GRUPPO_CLIENTI, ev.getClienti()));
						break ;
					} 
					
				if ( bancone ) {   
					float probabilita = rand.nextFloat();
					
					if ( probabilita <= ev.getClienti().getTolleranza() )
						numSoddisfatti += ev.getClienti().getNumPersone();
					else
						numInsoddisfatti += ev.getClienti().getNumPersone();
				}
				
				break;

			case PARTENZA_GRUPPO_CLIENTI:
				ev.getClienti().getTavolo().setOccupato(false);
				
				break;
			}
			
		}
		
	}

	public int getNumClienti() {
		return numClienti;
	}

	public int getNumSoddisfatti() {
		return numSoddisfatti;
	}

	public int getNumInsoddisfatti() {
		return numInsoddisfatti;
	}

}