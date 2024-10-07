package org.example;

public class Main {
    public static void main(String[] args) {

        DataManager dataManager = DataManager.getInstance();
        InputProcessor inputProcessor = new InputProcessor(dataManager);

        String testInput1 = """
                7
                C 1.1 8.15.1 P 15.10.2012 83
                C 1 10.1 P 01.12.2012 65
                C 1.1 5.5.1 P 01.11.2012 117
                D 1.1 8 P 01.01.2012-01.12.2012
                C 3 10.2 N 02.10.2012 100
                D 1 * P 8.10.2012-20.11.2012
                D 3 10 P 01.12.2012
                """;
//        Expected output
//        83
//        100
//        -

        String testInput2 = """
                4
                C 1.1 8.15.1 P 15.10.2012 50
                C 1 10.1 N 01.12.2012 60
                D 2 * P 01.01.2012-31.12.2012
                D 1 8 N 01.01.2012-31.12.2012
                """;
//        Expected output
//        -
//        -

        String testInput3 = """
                3
                C 1.1 8.15.1 P 15.10.2012 75
                C 2.2 5.10 N 17.12.2012 85
                D * * P 01.01.2012-31.12.2012
                """;
//        Expected output
//        75

        String testInput4 = """
                4
                C 1.1 8.15.1 P 15.10.2012 80
                C 2.1 8 P 15.12.2012 70
                D 1.1 8 P 01.10.2012
                D * * P 01.01.2012
                """;
//        Expected output
//        80
//        75

        String testInput5 = """
                6
                C 1 10.1 P 01.01.2012 50
                C 1.2 10.1 N 15.01.2012 40
                C 2 5 P 01.02.2012 60
                D 1 * P 01.01.2012-31.12.2012
                D * * P 01.01.2012-01.03.2012
                D 1 10 P 01.01.2012-31.12.2012
                """;
//        Expected output
//        50
//        55
//        50

        String testInput6 = """
                3
                C 1 8 P 01.01.2011 45
                C 1.1 8 P 15.12.2010 55
                D 1 * P 01.01.2012-31.12.2012
                """;
//        Expected output
//        -

        String testInput7 = """
                2
                C 3.1 4 P 10.10.2012 20
                D 3 4 P 10.10.2012-10.10.2012
                """;
//        Expected output
//        20

        System.out.println("Test 1:");
        inputProcessor.processInput(testInput1);

        System.out.println("\nTest 2:");
        inputProcessor.processInput(testInput2);

        System.out.println("\nTest 3:");
        inputProcessor.processInput(testInput3);

        System.out.println("\nTest 4:");
        inputProcessor.processInput(testInput4);

        System.out.println("\nTest 5:");
        inputProcessor.processInput(testInput5);

        System.out.println("\nTest 6:");
        inputProcessor.processInput(testInput6);

        System.out.println("\nTest 7:");
        inputProcessor.processInput(testInput7);
    }
}