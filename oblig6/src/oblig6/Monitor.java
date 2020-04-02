package oblig6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	int numInMonitor;
	
	private Melding melding = null;
	private String message = "";
	private Lock mLock = new ReentrantLock();
	private Condition notEmpty = mLock.newCondition();
	//how many kanal in total, how many telegrafist in total
	private  int antallKanal = 0;
	//how many message added to the monitor
	private  int addOK = 0;
	//how many kanal is done
	private  int getOK= 0;
	// private Condition notfull = mLock.newCondition();
	private static int counter=1;
	private int Id = 0;
	List<Melding> messageInMoniter = new ArrayList<Melding>();
	private int antallGet=0;
	
	public Monitor(int a,int b) {
		antallKanal = a;
		antallGet = b;
		Id = counter;
		counter++;

	}

	void addMessage(Melding s) throws InterruptedException {
		try
		{
			mLock.lock();
			if (s.getMelding().equals("end")) {
//				System.out.println("add done once");
				addOK++;
			} else {
				messageInMoniter.add(s);
				this.numInMonitor++;
				notEmpty.signal();
			}
		}
		finally {
			mLock.unlock();
		}			
	}

	@SuppressWarnings("finally")
	Melding getMessage() throws InterruptedException {
		if (addOK ==antallKanal && numInMonitor ==0) {
			getOK++;
//			System.out.println("getOK now is"+ getOK);
			if(getOK == antallGet) {
				System.out.println();
			    System.out.println("Monitor"+this.Id+ " er helt ferdig!");;
			    System.out.println();
			}
			
			
			return null;
			
		} else {
			try {
				mLock.lock();
				while (numInMonitor == 0) {
					notEmpty.await();
				}
				melding = messageInMoniter.get(0);
				messageInMoniter.remove(0);
				numInMonitor--;
			} finally {
				mLock.unlock();
				return melding;
			}			
		}
	}
  boolean getDone() {
	  if(getOK == antallGet) {
		  return true;
	  }else {
		  return false;
	  }
  }
  boolean addDone() {
	  if(addOK ==antallKanal) {
		  return true;
	  }else {
		  return false;
	  }
		  
  }
	
}
