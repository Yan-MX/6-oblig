
public class Kryptograf implements Runnable {
	Monitor m1, m2;
	int Id;
	static int counter = 1;
	static String message = "";
	Melding melding=new Melding(0,0," ");

	public Kryptograf(Monitor a, Monitor b) {
		m1 = a;
		m2 = b;
		Id = counter;
		counter++;
	}

	public void run() {

		while (melding != null) {
			try {
				melding = m1.getMessage();
				if(melding != null) {
				message = Kryptografi.dekrypter(melding.getMelding());
			//	System.out.println("now message = " + message);
				melding.setMelding(message);

				m2.addMessage(melding);
				}else {
					try {
						m2.addMessage(new Melding(0,0,"end"));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			} }catch (Exception e) {
				System.out.println("Error");
			}
		}
		System.out.println("Kryptograf " + Id + " er ferdig");
	}
}
