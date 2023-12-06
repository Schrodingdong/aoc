import java.util.*;
import java.io.*;

class AoC{
    private static List<String> validDigits = List.of("one","two","three","four","five","six","sevent","eight","nine");
    public static void main(String[] args) throws FileNotFoundException{
        // read input
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);
        // process
        long sum = 0;
        while(scanner.hasNext()){
            Map<Integer, Integer> digitsInProcessing = new HashMap<>();
            String toProcess = scanner.nextLine();
            // System.out.printf("%-20s",toProcess);
            String number = "";
            char temp = 0;
            int counter = 0;
            for(int i = 0; i < toProcess.length(); i++){
                char c = toProcess.charAt(i);
                if(Character.isDigit(c)){
                    if(temp == 0) number += c;
                    temp = c;
                } else {
                    // check what it is already starting
                    for(int digitIndex : digitsInProcessing.keySet()){
                        int digitStringIndex = digitsInProcessing.get(digitIndex);
                        if(c == validDigits.get(digitIndex).charAt(digitStringIndex)){
                            digitsInProcessing.put(digitIndex, ++digitStringIndex);
                        }
                        // System.out.println(digitsInProcessing);
                    }
                    // check if we ended a comparison
                    List<Integer> toRemove = new ArrayList<>();
                    for(Map.Entry<Integer,Integer> entry : digitsInProcessing.entrySet()){
                        String digitString = validDigits.get(entry.getKey());
                        int digitStringIndex = entry.getValue();
                        if(digitStringIndex == digitString.length()){
                            toRemove.add(entry.getKey());
                            int digit = entry.getKey() + 1;
                            if(temp == 0) number += (char) (int) (digit + '0');
                            temp = (char) (int) (digit + '0');
                            // System.out.println(temp);
                        }
                    }
                    // System.out.println(toRemove);
                    for(int r : toRemove){
                        digitsInProcessing.remove(r);
                    }
                    // check if numbers start with digit
                    for(int j = 0 ; j < validDigits.size(); j++){
                        String digitString = validDigits.get(j);
                        if(digitsInProcessing.containsKey(j)) continue;
                        if(c == digitString.charAt(0)){
                            digitsInProcessing.put(j, 1);
                        }
                    }
                }
            }
            number += temp;
            // System.out.println(number);
            sum += Integer.parseInt(number);
        }
        System.out.println(sum);
        scanner.close();
    }
}