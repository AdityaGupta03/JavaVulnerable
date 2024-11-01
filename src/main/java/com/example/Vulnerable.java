// src/main/java/com/example/HelloWorld.java
package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vulnerable {

  public static void main(String[] args) {
    System.out.println("Hello, World!");

    // Vulnerability: Unsafe handling of user input leading to Command Injection
    if (args.length > 0) {
      String userInput = args[0];
      System.out.println("User input: " + userInput);

      try {
        // Command Injection vulnerability: user input is directly passed to the command
        Process process = Runtime.getRuntime().exec("echo " + userInput);
        BufferedReader reader = new BufferedReader(
          new InputStreamReader(process.getInputStream())
        );
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
        reader.close();
      } catch (IOException e) {
        System.err.println("An error occurred: " + e.getMessage());
      }
    } else {
      System.out.println("No input provided.");
    }
  }
}
