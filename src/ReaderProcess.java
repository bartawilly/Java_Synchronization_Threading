
public class ReaderProcess implements Runnable {
	String name;
	private  SharedDataBase Database ;
    
	
	ReaderProcess(String name ,SharedDataBase Database  ){
		this.name= name;
		this.Database=Database;
		
	}
	
	@Override
	public void run() {
		
        
            Database.acquireReadLock(name);
         
            try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
         
   	System.out.println( name + " Reads  " +Database.getData());
         
            Database.releaseReadLock(name);
         }
      }
	


