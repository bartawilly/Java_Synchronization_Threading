import java.util.concurrent.Semaphore;

public class SharedDataBase {
	String Data ;
	int ReadingCounter;
	Semaphore mutex ;
	Semaphore Dbaccess;
	
	SharedDataBase(){
		Data="";
		ReadingCounter=0;
		mutex= new Semaphore(1);
		Dbaccess= new Semaphore(1);
	}
	SharedDataBase(String Data){
		this.Data=Data;
		ReadingCounter=0;
		mutex= new Semaphore(1);
		Dbaccess= new Semaphore(1);
	}
	public void acquireReadLock(String readerName){
		System.out.println( readerName + " Start  reading. ");
	
		try {
			mutex.acquire();
			
		} catch (InterruptedException e) {}
		ReadingCounter++;
		if (ReadingCounter== 1){
            	try {
            		if (Dbaccess.availablePermits()<=0){
            			System.out.println(readerName+ "blocked");
            		}
					Dbaccess.acquire();
				} catch (InterruptedException e) {}
            }
	
		mutex.release();
		
	}
    public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data += data;
	}
	public void acquireWriteLock(String writerName, String newData){
		 System.out.println( writerName+ " Start writing.");
			if (Dbaccess.availablePermits()==0){
    			System.out.println(writerName+ "blocked");
    		}
    	try {
			Dbaccess.acquire();
		} catch (InterruptedException e) {}	
    }
    public void releaseReadLock(String readerName){
    	try {
			mutex.acquire();
		} catch (InterruptedException e) {}
    	ReadingCounter--;
    	if (ReadingCounter == 0){
    		Dbaccess.release();
         }
    	System.out.println("Reader " + readerName + " Finish  reading. ");
    	mutex.release();
    	
    }
    public  void releaseWriteLock(String writerName){
    	Dbaccess.release();
    	System.out.println("Writer " + writerName+ " Finish writing.");
    }
}
