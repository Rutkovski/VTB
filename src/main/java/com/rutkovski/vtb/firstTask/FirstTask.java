package com.rutkovski.vtb.firstTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class FirstTask {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            System.out.print("Введите число N (N >= 3): ");
            while ((line = br.readLine()) != null) {
                int userNumber;
                try {
                    userNumber = Integer.parseInt(line.trim());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                if (userNumber >= 3) {
                    int[][] twoDimArray = getArray(userNumber);
                    printArray(twoDimArray);
                    int minElement = findMinElement(twoDimArray);
                    System.out.println("Минимальный элемент побочной диагонали: " + minElement);
                } else {
                    System.out.println("Ошибка! Число должно быть больше или равно 3!");
                }
                System.out.println("Введите число N (N >= 3): ");
            }

        } catch (Exception e) {
            System.err.println("IOException in try block =>" + e.getMessage());
        }
    }

    //Создаем массив из чисел
    private static int[][] getArray(int userNumber) {
        int[][] twoDimArray = new int[userNumber][userNumber];
        for (int i = 0; i < userNumber; i++) {
            for (int j = 0; j < userNumber; j++) {
                twoDimArray[i][j] = (int) (Math.random() * 100);
            }
        }
        return twoDimArray;
    }

    //Выводим массив на экран
    private static void printArray(int[][] twoDimArray) {
        for (int[] ints : twoDimArray) {
            for (int j = 0; j < twoDimArray.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    //Находим минимальный элемент побочной диагонали
    private static int findMinElement(int[][] twoDimArray) {
        int minElement = twoDimArray[twoDimArray.length - 1][0];
        for (int i = 1; i < twoDimArray.length; i++) {
            if (twoDimArray[twoDimArray.length - 1 - i][i] < minElement) {
                minElement = twoDimArray[twoDimArray.length - 1 - i][i];
            }
        }
        return minElement;
    }

}
