package it.polito.tdp.bar;

public class Tavolo implements Comparable<Tavolo>{
	
	private int id;
	private int posti;
	private boolean occupato;
	
	public Tavolo(int id, int posti) {
		this.id = id;
		this.posti = posti;
		this.occupato = false;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public int getId() {
		return id;
	}

	public int getPosti() {
		return posti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Tavolo o) {
		return this.posti - o.posti;
	}

}