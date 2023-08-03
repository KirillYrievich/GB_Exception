package gb.exception.lesson3.Task1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Record record = new Record();
        record.InputData();
        record.DataToFile();
    }
}

class Record{
    private String data;
    private String[] strArray;
    private final int countData = 6;

    public void InputData(){
        Scanner scanner = new Scanner(System.in);

        int res;
        do {
            System.out.println("Введите данные в следующем формате через пробел: ");
            System.out.println("""
                    Фамилия Имя Отчество дата рождения номер телефона пол
                    Форматы данных:
                    фамилия, имя, отчество - строки
                    дата рождения - строка формата dd.mm.yyyy
                    номер телефона - целое беззнаковое число без форматирования
                    пол - символ латиницей f или m.
                    """);
            this.data = scanner.nextLine();
            res = CheckQuantity();
            if(res == -1){
                System.out.println("Вы ввели меньше данных, чем нужно, либо пропустили пробелы");
            }
            if (res == -2) {
                System.out.println("Вы ввели больше данных, чем нужно");
            }

            try {
                ParseData();
            } catch (InputException e) {
                res = -3;
            }
        }

        while(res < 0);
        scanner.close();
    }

    private void ParseData() throws InputException{
        if (!strArray[0].matches("[a-zA-Zа-яёА-ЯЁ]+")){
            throw new InputException("В фамилии {" + strArray[0] + "} должны быть только буквы");
        }
        if (!strArray[1].matches("[a-zA-Zа-яёА-ЯЁ]+")){
            throw new InputException("В имени {" + strArray[1] + "} должны быть только буквы");
        }
        if (!strArray[2].matches("[a-zA-Zа-яёА-ЯЁ]+")){
            throw new InputException("В отчестве {" + strArray[2] + "} должны быть только буквы");
        }
        if (!strArray[3].matches("\\d{2}\\.\\d{2}\\.\\d{4}")){
            throw new InputException("Неправильно указана дата рождения {" + strArray[3] + "}");
        }
        if (!strArray[4].matches("\\d{11}")){
            throw new InputException("Неправильно введён номер телефона {" + strArray[4] + "}");
        }
        if (!strArray[5].matches("[fm]")){
            throw new InputException("Неправильно указан пол {" + strArray[5] + "}");
        }
    }

    private int CheckQuantity() {
        strArray = data.split("\\s+");  // Иванов Иван Иванович 01.01.1999 22222222222 m - тестовая запись
        if (strArray.length < this.countData){
            return -1;
        }
        if (strArray.length > this.countData){
            return -2;
        }
        return 0;
    }

    public void DataToFile(){
        try(FileWriter writer = new FileWriter(strArray[0]+".txt", true)) {
            data = String.join(" ", strArray) + "\n";
            writer.write(data);
            System.out.println("Запись успешно занесена в файл "+ strArray[0] + ".txt");
        }
        catch(IOException e){
            System.out.println("Ошибка записи в файл");
            e.getStackTrace();
        }
    }
}

class InputException extends Exception{//Runtime

    public InputException(String e) {super(e);}
}
