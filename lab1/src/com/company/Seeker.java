package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seeker implements Callable<ArrayList<String>> {

    File dir;
    ExecutorService pool;

    public Seeker(File dir, ExecutorService pool) {
        this.dir = dir;
        this.pool = pool;
    }

    public ArrayList<String> seek(File file) {
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            ArrayList<String> result = new ArrayList<>();
            while (sc.hasNextLine()) {
                String str = sc.nextLine();

                Pattern patternNumber = Pattern.compile("(\\+*)\\d{12}\s");
                Matcher matcherNumber = patternNumber.matcher(str);
                if (matcherNumber.find()) {
                    result.add(file.getName());
                }
                Pattern patternNumberName = Pattern.compile("(\\+)\\d{12}\s[КС]+[а-я]*\\W");
                Matcher matcherNumberName = patternNumberName.matcher(str);
                if (matcherNumberName.find()) {
                    result.add(matcherNumberName.group());
                }
            }
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public ArrayList<String> call() {
        try {
            File[] files = dir.listFiles();
            ArrayList<Future<ArrayList<String>>> seekResult = new ArrayList<>();
            ArrayList<String> result = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    Seeker seeker = new Seeker(file, pool);
                    Future<ArrayList<String>> res = pool.submit(seeker);
                    seekResult.add(res);
                } else {
                    result.addAll(seek(file));
                }
            }
            for (Future<ArrayList<String>> res : seekResult){
                result.addAll(res.get());
            }
            return result;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
