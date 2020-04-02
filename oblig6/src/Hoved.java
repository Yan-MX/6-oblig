

public class Hoved {

	public static void main(String[] args) {
		
	//type in two numbers in terminal --works
	int antallKanal = Integer.parseInt(args[0]);
	int antallKrytograf= Integer.parseInt(args[1]);
//		int antallKanal =4;
//		int antallKrytograf= 20;
		Operasjonssentral CIA = new Operasjonssentral(antallKanal);
		if (antallKanal >3) {
			antallKanal =3;
		}
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