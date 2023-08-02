package gb.exception.lesson2.Task4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

    public static void main(String[] args) {
        String userVal = getVal();
        System.out.println();
        System.out.println("You entered the following string:");
        System.out.println(userVal);
    }

    static String getVal() {
        String val = "";
        System.out.println("Input your string:");
        InputStream UserInput = System.in;
        Reader inputReader = new InputStreamReader(UserInput);
        BufferedReader bufferedReader = new BufferedReader(inputReader);
        try {
            val = bufferedReader.readLine();
            if(val.isBlank()){
                throw new IllegalArgumentException("Blank lines are prohibited!");
            }
        }
        catch (IOException exception) {
            System.out.println("There are some IO problems.");
            //exception.printStackTrace();
        }
        return val;
    }
}
