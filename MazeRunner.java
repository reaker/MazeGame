package MazePack;

import java.util.Scanner;

public class MazeRunner {

    private static int moveCounter = 0;
    private Maze myMap = new Maze();

    public static void main(String[] args) {

        MazeRunner mazeRunner = new MazeRunner();

        mazeRunner.intro();

        while (!mazeRunner.getMyMap().didIWin()) {

            mazeRunner.getMyMap().printMap();

            mazeRunner.movesMessage(moveCounter++);


            switch (mazeRunner.userMove()) {
                case "R":
                    if (mazeRunner.getMyMap().isThereAPit("R")) {
                        mazeRunner.navigatePit("R");
                    } else if (mazeRunner.getMyMap().canIMoveRight()) {
                        mazeRunner.getMyMap().moveRight();
                    } else mazeRunner.wall();
                    break;

                case "L":
                    if (mazeRunner.getMyMap().isThereAPit("L")) {
                        mazeRunner.navigatePit("L");
                    } else if (mazeRunner.getMyMap().canIMoveLeft()) {
                        mazeRunner.getMyMap().moveLeft();
                    } else mazeRunner.wall();
                    break;

                case "U":
                    if (mazeRunner.getMyMap().isThereAPit("U")) {
                        mazeRunner.navigatePit("U");
                    } else if (mazeRunner.getMyMap().canIMoveUp()) {
                        mazeRunner.getMyMap().moveUp();
                    } else mazeRunner.wall();
                    break;

                case "D":
                    if (mazeRunner.getMyMap().isThereAPit("D")) {
                        mazeRunner.navigatePit("D");
                    } else if (mazeRunner.getMyMap().canIMoveDown()) {
                        mazeRunner.getMyMap().moveDown();
                    } else mazeRunner.wall();
                    break;

                default:
                    break;
            }
            if (moveCounter >= 100) {
                mazeRunner.movesMessage(100);
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                return;
            }
        }
        System.out.println("Congratulations, you made it out alive!" + "\nand you did it in " + moveCounter + " moves");

    }

    private Maze getMyMap() {
        return myMap;
    }

    private void movesMessage(int moves) {

        switch (moves) {
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");

        }
    }

    private void navigatePit(String dir) {
        System.out.println("Watch out! There's a pit ahead, jump it?");

        String input = "";
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            input = scan.next();
            input = input.substring(0, 1).toLowerCase();
            if (input.equals("y")) {

                switch (dir) {

                    case "R":
                        try {
                            myMap.jumpOverPit(dir);
                        } catch (IllegalArgumentException e) {
                            try {
                                myMap.move(1, 1);
                                return;
                            } catch (IllegalArgumentException e1) {
                                myMap.move(-1, 1);
                                return;
                            }
                        }
                        return;

                    case "L":
                        try {
                            myMap.jumpOverPit(dir);
                        } catch (IllegalArgumentException e) {
                            try {
                                myMap.move(1, -1);
                                return;
                            } catch (IllegalArgumentException e1) {
                                myMap.move(-1, -1);
                                return;
                            }
                        }
                        return;

                    case "U":
                        try {
                            myMap.jumpOverPit(dir);
                        } catch (IllegalArgumentException e) {
                            try {
                                myMap.move(1, -1);
                                return;
                            } catch (IllegalArgumentException e1) {
                                myMap.move(-1, -1);
                                return;
                            }
                        }
                        return;

                    case "D":
                        try {
                            myMap.jumpOverPit(dir);
                        } catch (IllegalArgumentException e) {
                            try {
                                myMap.move(1, 1);
                                return;
                            } catch (IllegalArgumentException e1) {
                                myMap.move(-1, 1);
                                return;
                            }
                        }
                        return;
                }
                break;
            } else break;
        }
    }

    private void wall() {
        System.out.println("Sorry, you’ve hit a wall");
    }

    private void intro() {
        System.out.println("Welcome to Maze Runner!\nHere is your current position:");
    }

    private String userMove() {

        System.out.println("Where would you like to move? (R, L, U, D)");

        String input = "";
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            input = scan.next();
            if (input.equals("R") || input.equals("L") || input.equals("D") || input.equals("U")) {
                return input;

            } else System.out.println("Where would you like to move? (R, L, U, D)");
        }

        return input;
    }

}




