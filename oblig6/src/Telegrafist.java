
public class Telegrafist implements Runnable {
	Monitor m1;
	Kanal k1;
	int antallMelding;
	int antallLytt;
	int Id;
	String message=null;
	Melding m;
	
	public Telegrafist(Monitor m, int a, Kanal k) {
		m1= m;
		antallMelding = a;	
		Id= k.hentId();
		k1= k;
	}
	@Override
	public void run() {
	
			while(antallMelding>antallLytt) {
				try {
				//System.out.println("Telegrafist "+Id+" lytter en ny melding fra kanal");
				message = k1.lytt();
				m= new Melding(antallLytt,Id,message);
				antallLytt++;
				//System.out.println("Telegrafist "+Id+" legger til en ny melding");
				m1.addMessage(m);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		
				try {
					m1.addMessage(new Melding(antallLytt+1,Id,"end"));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Telegrafist "+ Id + " er ferdig." );
			}
			
	}

