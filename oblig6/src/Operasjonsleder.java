
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Operasjonsleder implements Runnable {
	private Monitor m2;
	private int antallKanal;
	private Melding melding = new Melding(0, 0, " ");
	private Monitor m1;
	private int index;

	private List<ArrayList<Melding>> info = new ArrayList<ArrayList<Melding>>();

	public Operasjonsleder(Monitor c, Monitor a, int b) {
		m1 = c;
		m2 = a;
		antallKanal = b;
		for (int i = 0; i < b; i++) {
			ArrayList<Melding> l = new ArrayList<Melding>();
			info.add(l);
		}

	}

	@Override
	public void run() {

		while (!(m1.addDone() && m1.getDone() && m2.addDone())) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Operasjonalsleder start working");

		while (melding != null) {
			try {
				melding = m2.getMessage();

				if (melding != null) {
					index = melding.getID();

					info.get(index - 1).add(melding);
				}
			} catch (Exception e) {
				System.out.println("Error O");
			}
		}
		sort();
		print();
		System.out.println("Operasjonalsleder " + " er ferdig");
		
		
	}
	public void print() {
		int counter = 1;
		for (ArrayList<Melding> j:info) {
			String filename =Integer.toString(counter);
			counter++;
			try {
				PrintWriter pw = new PrintWriter(filename+ ".txt","UTF-8");
				for(Melding m: j) {
					pw.println(m.getMelding());
				}
				System.out.println("file: "+filename+ ".txt is created, please check");
				pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void sort() {
		
		List<ArrayList<Melding>> info2 = new ArrayList<ArrayList<Melding>>();
		try {
			for (ArrayList<Melding> m : info) {
				ArrayList<Melding> copy= new ArrayList<Melding>();
				for (int r = 0; r < m.size(); r++) {

					for (Melding f : m) {
						if (f.getSekvensnummer() == r) {
							copy.add(f);
							break;
						}
					}
				}
				info2.add(copy);
			} info = info2;
			
			
		} catch (Exception e) {
			System.out.println("error " + e.getCause());
		}
		
	}

}
