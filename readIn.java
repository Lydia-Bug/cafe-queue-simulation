/*
 * Made by: Lydia Acton
 * Last updated: 20/08/20
 * This class is for reading in  CSV files, and checking that they are valid
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class readIn
{
    final int MAXLINES = 3601; //amount of expected rows (reads in one more line then nessacery so I can check if it has values)
    final int VALUESPERLINE = 2; //expected amount of columns
    String AllLinesAllElements[][] = new String[MAXLINES][VALUESPERLINE];
    public readIn(){
        
    }
    void readInFile(String filename){
        File thefile = new File(filename);
        String CSVlines[] = new String[MAXLINES];
        int linecount = 0;
        try{
            Scanner reader = new Scanner(thefile);
            //reads in CSV as lines of strings
            while (reader.hasNextLine() && linecount < MAXLINES){
                String line = reader.nextLine();
                CSVlines[linecount] = line;
                linecount++;
            }
            
            //coverts lines of strings to ints
            for (int i = 0; i<linecount; i++){
                String values[] = CSVlines[i].split(",");
                for (int j = 0; j < values.length; j++){
                    AllLinesAllElements[i][j] = values[j];
                }
            }
        } catch (IOException e) {System.out.println(e);}
    }
    //reads the file as lines of string to check format
    //also reads in file so I can check the rows afterwards
    boolean checkColumbs(String filename){
        File thefile = new File(filename);
        String CSVlines[] = new String[MAXLINES];
        int linecount = 0;
        try{
            Scanner reader = new Scanner(thefile);
            //reads in CSV as strings
            while (reader.hasNextLine() && linecount < MAXLINES){ 
                String line = reader.nextLine();
                CSVlines[linecount] = line;
                linecount++;
            }
            //convers lines of strings to ints
            for (int i = 0; i<linecount; i++){ 
                String values[] = CSVlines[i].split(",");
                if(values.length > 2)return false; //checks there are only 2 columbs
                for (int j = 0; j < values.length; j++){
                    AllLinesAllElements[i][j] = values[j];
                }
            }
        } catch (IOException e) {System.out.println(e);}
        return true;
    }
    
    //checks if file exists
    boolean fileExists(String filename){
        File thefile = new File(filename);
        if(thefile.exists() && !thefile.isDirectory()){
            return true;
        }else{
            return false;
        }
    }
    //checks that file is a CSV file
    boolean fileIsCSV(String filename){ 
        if(filename.length() < 4)   return false; //if string has less then 4 characters it's no valid because '.csv' is 4 characters
        String fileExtension = filename.substring(filename.length() - 4); //finds file extension
        if(fileExtension.equals(".csv")){
            return true;
        }else{
            return false;
        }
    }
    
    boolean checkRows(){
    int n;
        for(int i = 1; i<(3600); i++){
            try { //checks values are ints, and that there are no less then 3600 rows
               n = Integer.parseInt(AllLinesAllElements[i][0]);
               n = Integer.parseInt(AllLinesAllElements[i][1]);
            }catch (NumberFormatException e){
                return false;
            }
        }
        for(int i = 0; i<2; i++){
            try { //sees if there is a 3601st row
                n = Integer.parseInt(AllLinesAllElements[3600][i]);
                return false;
            }catch (NumberFormatException e){}
        }
        return true;
    }
    //returns if student is being added for certain time
    int students(int i){
        return Integer.parseInt(AllLinesAllElements[i][0]);
    }
    //returns if student is being added for certain time
    int teachers(int i){
        return Integer.parseInt(AllLinesAllElements[i][1]);
    }
}
