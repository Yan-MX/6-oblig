
 class Kokk implements Runnable {
      private FellesBord bord;
      private final int ANTALL;
      private int laget = 0;
      Kokk(FellesBord bord, int ant) {
		  this.bord = bord;
		  ANTALL = ant;
	}

	public void run() {
	     try { 
	        while(ANTALL != laget) {
                laget ++;
                System.out.println("Kokken lager tallerken nr: " + laget);
                bord.settTallerken();  // lag og lever tallerken             
                Thread.sleep((long) (500 * Math.random()));
              }
           } 
           catch (InterruptedException e) { 
                System.out.println("Uventet stopp 1");
           }
         // Kokken er ferdig
      }
   }