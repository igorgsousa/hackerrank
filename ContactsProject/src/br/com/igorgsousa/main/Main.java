package br.com.igorgsousa.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * {@link https://www.hackerrank.com/challenges/contacts/problem}
 * */
public class Main {

    static int[] contacts(String[][] queries) {
    	
    	Set<String> names = new HashSet<>();
    	List<Integer> partialResults = new ArrayList<>();
    	
        for(int index = 0 ; index < queries.length;index++){
           
            String[] innerArr = queries[index];
            
            String command = innerArr[0].toLowerCase();
            
            if(command.equalsIgnoreCase("add")) {
            	names.add(innerArr[1].toLowerCase());
            }else {
            	continue;
            }
        }
        
        for(int index = 0 ; index < queries.length;index++){
            
            String[] innerArr = queries[index];
            
            String command = innerArr[0].toLowerCase();
            int founded = 0;
            if(command.equalsIgnoreCase("find")) {
            	
            	Iterator<String> iterator = names.iterator();
				while(iterator.hasNext()) {
            		String name = iterator.next();
            		if(name.startsWith(innerArr[1].toLowerCase())) {
            			founded++;
            		}
            	}
				
				partialResults.add(founded);
            }else {
            	continue;
            }
        }
        
        int[] ret = new int[partialResults.size()];
        for(int index=0; index < partialResults.size() ; index ++) {
        	ret[index] = partialResults.get(index);
        }
        
		return ret;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        
    	
    	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("results/result.txt"));


        String[][] queries = new String[][] {{"add","hack"},{"add","hackerrank"},{"find","hac"},{"find","hak"} };

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }
        
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
