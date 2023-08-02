package gb.exception.lesson2.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

    public static void main(String[] args) {
        float userVal = getFloatVal();
        System.out.println();
        System.out.println("You entered the following number:");
        System.out.println(userVal);
    }

    static float getFloatVal() {
        float val = 0;
        boolean status=true;
        while(status){
            InputStream UserInput = System.in;
            Reader inputReader = new InputStreamReader(UserInput);
            System.out.println("Input some float value:");
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            try {
                val=Float.parseFloat(bufferedReader.readLine());
                status=false;
            }
            catch (IOException | NumberFormatException exception) {
                System.out.println("User input is not number. Or there is any IO problem. Try again!");
                //exception.printStackTrace();
            }
        }
        return val;
    }
}