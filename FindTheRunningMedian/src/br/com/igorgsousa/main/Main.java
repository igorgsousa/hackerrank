package br.com.igorgsousa.main;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * {@link https://www.hackerrank.com/challenges/find-the-running-median/problem}
 * */
public class Main {

	/*
     * Complete the runningMedian function below.
     */
    static double runningMedian(List<Integer> numbers) {
        
    	double ret = -1;
    	
    	if(numbers.size() == 1) {
    		ret = numbers.get(0);
    	}else {
    		
    		int middle = numbers.size()/2;
    		
    		if(numbers.size()%2 == 0) {
    			ret = ((double)(numbers.get(middle-1)+numbers.get(middle)))/2;
    		}else {
    			ret = numbers.get(middle);
    		}
    	}
    	
    	return ret;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("results/result.txt"));

        int entryNumber = Integer.parseInt(scanner.nextLine().trim());

        List<Integer> numbers = new ArrayList<>(entryNumber);
        
        double[] result = new double[entryNumber];

        for (int aItr = 0; aItr < entryNumber; aItr++) {
            int newNumber = Integer.parseInt(scanner.nextLine().trim());
            
            if(numbers.isEmpty()) {
            	numbers.add(newNumber);
            }else {
            	if(newNumber <= numbers.get(0)) {
            		numbers.add(0, newNumber);
            	}else if(numbers.size() == 1 || newNumber >= numbers.get(numbers.size()-1)) {
            		numbers.add(numbers.size(), newNumber);
            	}else {
            		for(int i = 0; i < numbers.size()-1;i++) {
            			if( newNumber >= numbers.get(i) && newNumber <= numbers.get(i+1)) {
            				numbers.add(i+1,newNumber);
            				break;
            			}
            		}
            	}
            }
            
            double runningMedian = runningMedian(numbers);
            System.out.println(runningMedian);
			result[aItr] = runningMedian;
        }

        
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

