
public class WriterProcess implements Runnable {

	String name;
	private  SharedDataBase Database ;
	String newData;
    
	
	WriterProcess (String name ,SharedDataBase Database, String newData  ){
		this.name= name;
		this.Database=Database;
		this.newData= newData;
		
		
	}
	
	@Override
	public void run() {
            Database.acquireWriteLock(name, newData);
            
            try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
         
            Database.setData(newData);
         	System.out.println( name + " Writes  " +Database.getData());
         
            Database.releaseWriteLock(name);
         }
      }
	

