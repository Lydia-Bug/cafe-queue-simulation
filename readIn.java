import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class readIn
{
    final String filename = "arrivals.csv";
    final int MAXLINES = 3600;
    final int VALUESPERLINE = 3;
    public readIn(){
        File thefile = new File(filename);
        String CSVlines[] = new String[MAXLINES];
        String AllLinesAllElements[][] = new String[MAXLINES][VALUESPERLINE];
        int linecount = 0;
        
        
        try{
            Scanner reader = new Scanner(thefile);
            
            while (reader.hasNextLine() && linecount < MAXLINES){
                String line = reader.nextLine();
                CSVlines[linecount] = line;
                linecount++;
            }
            
            for (int i = 0; i<linecount; i++){
                String values[] = CSVlines[i].split(",");
                
                for(int j = 0; j < values.length; j++){
                    System.out.print(values[j]+"*******");
                }
                System.out.println("");
                
                for (int j = 0; j < values.length; j++){
                    AllLinesAllElements[i][j] = values[j];
                }
            }
            
        } catch (IOException e) {System.out.println(e);}
        
        
        
        System.out.println("The first column read");
        for (int i = 0; i < linecount; i++){
            System.out.println(AllLinesAllElements[i][0]);
        }
        
        System.out.println("Staff arriving at any given time.");
        for(int i = 0; i < linecount; i++){
            System.out.println("At " + AllLinesAllElements[i][0] + " " + AllLinesAllElements[i][2] + " arrived.");
        }
    }
}
