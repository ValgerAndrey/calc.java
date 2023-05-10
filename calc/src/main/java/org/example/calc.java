package org.example;

import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример: 2 + 2 или Х * Х");
        String expression = scanner.nextLine();
        System.out.println("Ответ: " + parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split(" ");
        if (operands.length != 3) throw new Exception("Должно быть два операнда");
        oper = opers(expression);
        if (oper == null) throw new Exception("Данная математическая операция не доступна");
        //проверяем условие: если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[2])) {
            //тут конвертируем оба числа в арабские для вычесления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[2]);
            isRoman = true;
        }
        // Проверяем условие: если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[2])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[2]);
            isRoman = false;
        }
        // Проверяем условие: если одни число римское, а другое - арабское
        else {
            throw new Exception("Числа должны быть в одном формате (Арабские или Римские) например 3 - 1 или II + V");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть в диапозоне от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, выводим исключение (число <1)
            if (arabian <= 0) {
                throw new Exception("Римское числа не могут быть = 0 и отрицательными");
            }
            //конвертируем результат операции из арабских в римские
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String opers(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (String s : romanArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabic) {
        return romanArray[arabic];
    }

}













