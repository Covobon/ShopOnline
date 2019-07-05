package com.smartshop.configure.initial;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InitializerData {

    public static void getFileName(){
        /*try (Stream<Path> paths = Files.walk(Paths.get("../resources/img/Phone"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e){
            System.out.println("Khong tim thay file");
        }*/

        File folder = new File("./src/main/resources/img/Phone");
        File[] listOfFiles = folder.listFiles();
        System.out.println(folder);

        System.out.println(folder.getPath());

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    public static void main(String[] args) {
        getFileName();
    }
}
