

public class Hoved {

	public static void main(String[] args) {
		
		int antallKanal = 3;
		int antallKrytograf= 8;
		Operasjonssentral CIA = new Operasjonssentral(antallKanal);
		Kanal [] kanalArray = CIA.hentKanalArray();
		Monitor m1 = new Monitor(antallKanal,antallKrytograf);
		Monitor m2 = new Monitor(antallKrytograf,1);
		for(Kanal k: kanalArray) {
			Telegrafist t = new Telegrafist(m1,k.getTotal(),k);
			Thread thread= new Thread(t);
			thread.start();
		}
		for (int i=0;i<antallKrytograf;i++) {
			
			Kryptograf f = new Kryptograf(m1,m2);
			Thread f1 = new Thread(f);
			f1.start();
		
		}
		Operasjonsleder o1 = new Operasjonsleder(m1,m2,antallKanal);
		Thread o = new Thread(o1);
		o.start();
		//System.out.println(m1.messageInMoniter.size()+ " message left");
}
}