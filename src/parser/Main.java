package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static File file;
    static HashMap<String, Integer> list = new HashMap<>();
    static String[] compare = {"lance","armstrong","barry","bonds","tony","hawk","hawks","sarah","hughes","jarome","iginla","lisa",
            "leslie","shaquille","o'neal","oneal","ronaldo","michael","vick","serena","williams"};
    static String[] compare2 = {"lance armstrong","barry bonds","tony hawk","tony hawks","sarah hughes","jarome","iginla","lisa leslie",
            "shaquille","o'neal","ronaldo","michael vick","serena williams","venus williams","david beckham","allen iverson","luis gonzalez"};
    static ArrayList<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        String filename = "data.txt";
        file = new File(filename);
        try {
            parser();
            printer();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This methods parsers a file line by line.
     * It takes the line and strips of the pieces we do not need; i.e. IP, Time, URL
     * Then it calls the counter() method with each line
     *
     * @throws Exception the exception is a precaution for scanning a file
     */
    private static void parser() throws Exception{
        Scanner in = new Scanner(file);
        while(in.hasNextLine()){
            String line = in.nextLine();
            //try to parse the line data
            try {
                //cuts the IP address off the front
                line = line.substring(line.indexOf("\t") + 1);
                //cuts the url off the end
                line = line.substring(0, line.lastIndexOf("\t"));
                //cuts the time off the start
                line = line.substring(line.indexOf("\t") + 1);
                //calls counter method to process the words in this line.
                counter(line.toLowerCase());
            }catch(StringIndexOutOfBoundsException e){
                //if there was an exception, print the line it erred on
                System.out.println("Skipped: "+line);
            }
        }
    }

    private static void counter(String line){
        String[] words = line.split(" ");
        for(String w: words){
            w = w.toLowerCase();
            if(list.containsKey(w)) {
                int i = list.get(w);
                list.put(w, i+1);
            }else{
                list.put(w, 1);
            }
        }

        for(String c: compare2){
            if(line.contains(c))
                lines.add(line);
        }
    }

    private static void printer(){
        System.out.println("-----------------------Start Print-----------------------");
        ArrayList<String> entries = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        entries.addAll(list.keySet());
        values.addAll(list.values());
        for(int i=0;i<compare.length;i++) {
            if (list.containsKey(compare[i]))
                System.out.println(compare[i]+"  "+list.get(compare[i]));
            else
                System.out.println(compare[i]+"  0");
        }
        System.out.println("------------------------End Print------------------------");
        System.out.println("----------------------Start Print 2----------------------");
        for(String line: lines){
            System.out.println(line);
        }
        System.out.println("-----------------------End Print 2-----------------------");
    }
}
