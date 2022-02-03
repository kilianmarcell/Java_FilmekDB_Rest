package hu.petrik.filmdb;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            RequestHandler.get("");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}