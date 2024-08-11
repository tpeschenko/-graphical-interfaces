package ru.geekbrains.jdk.chat.server;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IOControllerDefault implements IOController{
    /**
     * Записывает список строк в файл
     *
     * @param list Список строк
     * @param file Путь к файлу
     */
    @Override
    public void writeFile(List<String> list, File file) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(file.getAbsolutePath()))){
            for (String line: list) {
                output.write(line);
                output.newLine();
            }

//            while (list.size() > 0) {
//                output.write(list.remove(0));
//                output.newLine();
//            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("File access error");
        }


    }

    /**
     * Читает файл с диска
     *
     * @param file файл
     * @return Список строк
     */
    @Override
    public List<String> readFile(File file) {
        List<String> list = new ArrayList<>();
        String str;

        try (BufferedReader input = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            while ((str = input.readLine()) != null) {
                list.add(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("File access error");
        }



        return list;
    }
}
