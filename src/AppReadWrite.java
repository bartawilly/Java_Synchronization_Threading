import java.util.Scanner;

public class AppReadWrite {
Scanner input ;
Scanner input2 ;
int  numReadingProcess,numWritingProcess;
Thread [] RprocessArray;
Thread [] WprocessArray;
SharedDataBase Database;
AppReadWrite(){
	 input =  new Scanner(System.in);
	 RprocessArray= new Thread [1];
		WprocessArray= new Thread [1];
}
public void Test(){
	System.out.println("           Reading / Writing SharedDatabase Semaphores ");
	System.out.println("Please Enter Initial Buffer contents ");
	
	String Text = input.nextLine();
	
	Database= new SharedDataBase(Text);

	System.out.println("Please Enter Total Number of reader threads ");
	
	numReadingProcess= input.nextInt();
	
	System.out.println("Please Enter Total Number of writer threads ");
	
	numWritingProcess=input.nextInt();
	
	RprocessArray= new Thread [numReadingProcess];
WprocessArray= new Thread [numWritingProcess];
	
	String [ ] ReadersNames = new String [numReadingProcess];
	String [ ] WritersNames = new String [numWritingProcess];
	
	
	System.out.println("Reader Threads: ");
	
	for ( int i=0;i<numReadingProcess;i++ ){
		input = new Scanner(System.in);
		ReadersNames[i]= input.nextLine();}
	
	
	System.out.println("Writing Threads: ");
	for (int i = 0 ; i < numWritingProcess ; i++){
		WritersNames[i] = input.nextLine();}
	
		for ( int i=0;i<numReadingProcess;i++ ){
		RprocessArray[i]= new Thread ( new ReaderProcess(ReadersNames[i], Database));
		
	}
		
	for (int i = 0 ; i < numWritingProcess ; i++){
		String [] temp = WritersNames[i].split(" ");
		WprocessArray[i]= new Thread ( new WriterProcess(temp[0], Database, temp[1]));

	}
	
	for ( int i=0;i<numReadingProcess;i++ ){
		RprocessArray[i].start();}
		for (int j = 0 ; j < numWritingProcess ; j++)
			
			WprocessArray[j].start();
	}
		
	

	public static void main(String[] args) {
		AppReadWrite app= new AppReadWrite();
		app.Test();
		

	}

}
