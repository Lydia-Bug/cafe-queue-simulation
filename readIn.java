import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class readIn
{
    final int MAXLINES = 3600;
    final int VALUESPERLINE = 3;
    String AllLinesAllElements[][] = new String[MAXLINES][VALUESPERLINE];
    public readIn(){
        
    }
    void readInFile(String filename){
        File thefile = new File(filename);
        String CSVlines[] = new String[MAXLINES];
        
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
                for (int j = 0; j < values.length; j++){
                    AllLinesAllElements[i][j] = values[j];
                }
            }
        } catch (IOException e) {System.out.println(e);}
        /*
        for (int i = 0; i < linecount; i++){
            System.out.print(AllLinesAllElements[i][1]);
            System.out.print(" ");
            System.out.println(AllLinesAllElements[i][2]);
        }
        */
        
    }
    boolean fileExists(String filename){
        File thefile = new File(filename);
        if(thefile.exists() && !thefile.isDirectory()){
            return true;
        }else{
            return false;
        }
    }
    boolean fileIsCSV(String filename){ //checks if file is csv
        String fileExtension = filename.substring(filename.length() - 4); //finds file extension
        if(fileExtension.equals(".csv")){
            return true;
        }else{
            return false;
        }
    }
    int students(int i){
        return Integer.parseInt(AllLinesAllElements[i][1]);
    }
    int teachers(int i){
        return Integer.parseInt(AllLinesAllElements[i][2]);
    }
    
}
