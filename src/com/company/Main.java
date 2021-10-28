package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a directory to look into: ");

        String dir = sc.next();

        ExecutorService pool = Executors.newCachedThreadPool();
        Seeker seeker = new Seeker(new File(dir), pool);
        Future<ArrayList<String>> res = pool.submit(seeker);

        try {
            List<String> noDuplicatesList = res.get().stream().distinct().collect(Collectors.toList());

            for (String s : noDuplicatesList) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "\\result", true));
                writer.append(s).append("\n");

                writer.close();

                System.out.println(s + "\n");
            }

        } catch (InterruptedException | ExecutionException | IOException e){
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
