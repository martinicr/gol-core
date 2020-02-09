package mf.gol;

import mf.gol.core.Cell;
import mf.gol.core.GameOfLife;
import mf.gol.core.Grid;

import java.io.Console;

import static java.util.Optional.ofNullable;
import static mf.gol.core.CellStatus.ALIVE;

public class App {

    public static final String ANSI_RESET  = "\u001B[0m";

    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK  = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED    = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN  = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE   = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN   = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE  = "\u001B[97m";

    public static final String[] FOREGROUNDS = {
            ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW,
            ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE,
            ANSI_BRIGHT_BLACK, ANSI_BRIGHT_RED, ANSI_BRIGHT_GREEN, ANSI_BRIGHT_YELLOW,
            ANSI_BRIGHT_BLUE, ANSI_BRIGHT_PURPLE, ANSI_BRIGHT_CYAN, ANSI_BRIGHT_WHITE
    };

    public static final String ANSI_BG_BLACK  = "\u001B[40m";
    public static final String ANSI_BG_RED    = "\u001B[41m";
    public static final String ANSI_BG_GREEN  = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE   = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN   = "\u001B[46m";
    public static final String ANSI_BG_WHITE  = "\u001B[47m";

    public static final String ANSI_BRIGHT_BG_BLACK  = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED    = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN  = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE   = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN   = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE  = "\u001B[107m";

    public static final String[] BACKGROUNDS = {
            ANSI_BG_BLACK, ANSI_BG_RED, ANSI_BG_GREEN, ANSI_BG_YELLOW,
            ANSI_BG_BLUE, ANSI_BG_PURPLE, ANSI_BG_CYAN, ANSI_BG_WHITE,
            ANSI_BRIGHT_BG_BLACK, ANSI_BRIGHT_BG_RED, ANSI_BRIGHT_BG_GREEN, ANSI_BRIGHT_BG_YELLOW,
            ANSI_BRIGHT_BG_BLUE, ANSI_BRIGHT_BG_PURPLE, ANSI_BRIGHT_BG_CYAN, ANSI_BRIGHT_BG_WHITE };

    public static void main( String[] args ) {

//        System.out.println("\n  Default text\n");
//        StringBuilder colorSb = new StringBuilder();
//        for (String fg : FOREGROUNDS) {
//            for (String bg : BACKGROUNDS) {
////                System.out.print(fg + bg + "  TEST  ");
//                colorSb.append(ANSI_BLACK + ANSI_BG_BLACK + "  TEST  ");
//            }
////            System.out.println(ANSI_RESET);
//            colorSb.append(ANSI_RESET + "\n");
//        }
//        System.out.println(colorSb.toString());
//
//        System.out.println(ANSI_RESET + "\n  Back to default.\n");

//        String[] spinner = new String[] {"\u0008/", "\u0008-", "\u0008\\", "\u0008|" };
//        Console console = System.console();
//        console.printf("|");
//        for (int i = 0; i < 1000; i++) {
//            Thread.sleep(150);
//            console.printf("%s", spinner[i % spinner.length]);
//        }

        // Blinker patter
//        Grid initialGrid = new Grid(5, 5);
//        Cell cell1 = new Cell(1,2, ALIVE);
//        Cell cell2 = new Cell(2,2, ALIVE);
//        Cell cell3 = new Cell(3,2, ALIVE);
//
//        initialGrid.addCell(cell1, cell2, cell3);

        // Pulsar pattern
//        Grid initialGrid = Grid.squareGrid(17);
//        initialGrid.addCell(
//                new Cell(2,4, ALIVE),
//                new Cell(2,5, ALIVE),
//                new Cell(2,6, ALIVE),
//                new Cell(2,10, ALIVE),
//                new Cell(2,11, ALIVE),
//                new Cell(2,12, ALIVE),
//                new Cell(4,2, ALIVE),
//                new Cell(4,7, ALIVE),
//                new Cell(4, 9, ALIVE),
//                new Cell(4, 14, ALIVE),
//                new Cell(5,2, ALIVE),
//                new Cell(5,7, ALIVE),
//                new Cell(5, 9, ALIVE),
//                new Cell(5, 14, ALIVE),
//                new Cell(6,2, ALIVE),
//                new Cell(6,7, ALIVE),
//                new Cell(6, 9, ALIVE),
//                new Cell(6, 14, ALIVE),
//                new Cell(7,4, ALIVE),
//                new Cell(7,5, ALIVE),
//                new Cell(7,6, ALIVE),
//                new Cell(7,10, ALIVE),
//                new Cell(7,11, ALIVE),
//                new Cell(7,12, ALIVE),
//                new Cell(9,4, ALIVE),
//                new Cell(9,5, ALIVE),
//                new Cell(9,6, ALIVE),
//                new Cell(9,10, ALIVE),
//                new Cell(9,11, ALIVE),
//                new Cell(9,12, ALIVE),
//                new Cell(10,2, ALIVE),
//                new Cell(10,7, ALIVE),
//                new Cell(10, 9, ALIVE),
//                new Cell(10, 14, ALIVE),
//                new Cell(11,2, ALIVE),
//                new Cell(11,7, ALIVE),
//                new Cell(11, 9, ALIVE),
//                new Cell(11, 14, ALIVE),
//                new Cell(12,2, ALIVE),
//                new Cell(12,7, ALIVE),
//                new Cell(12, 9, ALIVE),
//                new Cell(12, 14, ALIVE),
//                new Cell(14,4, ALIVE),
//                new Cell(14,5, ALIVE),
//                new Cell(14,6, ALIVE),
//                new Cell(14,10, ALIVE),
//                new Cell(14,11, ALIVE),
//                new Cell(14,12, ALIVE)
//        );
        // Pentadecathlon Pattern
        Grid initialGrid = new Grid(18, 11);
        initialGrid.addCell(
                new Cell(1, 5, ALIVE),
                new Cell(2, 5, ALIVE),
                new Cell(4, 5, ALIVE),
                new Cell(5, 4, ALIVE),
                new Cell(5, 6, ALIVE),
                new Cell(7, 4, ALIVE),
                new Cell(7, 5, ALIVE),
                new Cell(7, 6, ALIVE),
                new Cell(10, 4, ALIVE),
                new Cell(10, 5, ALIVE),
                new Cell(10, 6, ALIVE),
                new Cell(12, 4, ALIVE),
                new Cell(12, 6, ALIVE),
                new Cell(13, 5, ALIVE),
                new Cell(15, 5, ALIVE),
                new Cell(16, 5, ALIVE)
        );

        try {
            for (int i = 0; i < 16; i++) {
                System.out.print("\033[2J");     // clear terminal

                Thread.sleep(500L);
                printGrid(initialGrid);
                initialGrid = GameOfLife.newGeneration(initialGrid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printGrid(Grid grid) {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < grid.getNumberOfRows(); i++) {
            for(int j = 0; j < grid.getNumberOfColumns(); j++) {
                String status = ofNullable(grid.getCell(i, j))
                        .map(c -> (c.getCellStatus() == ALIVE) ? ANSI_CYAN + ANSI_BG_CYAN + "   " : ANSI_BLACK + ANSI_BG_BLACK + "   ")
                        .orElse(ANSI_BLACK + ANSI_BG_BLACK + "   ");
                sb.append(status);

//                if(j != (this.columns - 1)) {
//                    sb.append(" ");
//                }
            }
            sb.append(ANSI_RESET + "\n");
        }
       System.out.println(sb.toString());
    }

}
