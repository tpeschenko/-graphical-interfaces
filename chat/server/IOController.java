package ru.geekbrains.jdk.chat.server;

import java.io.File;
import java.util.List;

public interface IOController {

    /**
     * Записывает список строк в файл
     * @param list Список строк
     * @param file Файл
     */
    void writeFile(List<String> list, File file);

    /**
     * Читает файл с диска
     * @param file файл
     * @return Список строк
     */
    List<String> readFile(File file);
}
