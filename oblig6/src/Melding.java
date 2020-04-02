
public class Melding {
	private int sekvensnummer;
	private int ID;
	private String melding;
	
	
	public Melding(int a, int b, String c) {
		sekvensnummer = a;
		ID = b;
		melding= c;
	}


	public void setMelding(String melding) {
		this.melding = melding;
	}


	public int getSekvensnummer() {
		return sekvensnummer;
	}


	public int getID() {
		return ID;
	}


	public String getMelding() {
		return melding;
	}


}
