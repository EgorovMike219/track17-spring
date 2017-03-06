package track.lessons.lesson1;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 * Задание 1: Реализовать два метода
 *
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 *
 *
 * Пример файла - words.txt в корне проекта
 *
 * ******************************************************************************************
 *  Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 *
 *  Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 *
 * ******************************************************************************************
 *
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        int result = 0;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while( (line = br.readLine()) != null){
            try {
                int number = Integer.parseInt(line);
                result += number;
            } catch (Exception e) {
                //nothing
            }
        }
        br.close();
        fr.close();
        return result;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        StringBuilder builder = new StringBuilder();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while( (line = br.readLine()) != null){
            try {
                Integer.parseInt(line);
            } catch (Exception e) {
                if (line.length() != 0) {
                    builder.append(line);
                    builder.append(" ");
                }
            }
        }
        br.close();
        fr.close();
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
