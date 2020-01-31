package mf.gol.service;

import static java.util.Optional.ofNullable;

public class Grid {

    private static final int MIN_GRID_SIZE = 3;
    private static final int MAX_GRID_SIZE = 10;

    private Cell[][] gameGrid;
    private int totalOfCells = 0;

    public Grid(int dimension) {
        if(dimension < MIN_GRID_SIZE || dimension > MAX_GRID_SIZE) {
            throw new RuntimeException("Grid dimension is not allowed");
        }
        this.gameGrid = new Cell[dimension][dimension];
    }

    public void addCell(Cell... cells) {
        for(Cell cell: cells) {
            this.gameGrid[cell.getX()][cell.getY()] = cell;
            this.totalOfCells++;
        }
    }

    public Cell getCellNeighbours(int x, int y) {
//        CellNeighbours cellNeighbours = new CellNeighbours();
        Cell cell = Cell.getInstance();
        for (int row = fromRow(x); row <= toRow(x); row++) {
            for (int column = fromColumn(y); column <= toColumn(y); column++) {
//                System.out.println("row " + row + ", column " + column);
                if(row == x && column == y) {
                    System.out.println("SAME =>> x " + x + ", row " + row + ", y " + y + ", column " + column);
                    continue;
                } else {
//                    cellNeighbours.addNeighbour(this.gameGrid[row][column]);
                    Cell neighbour = ofNullable(this.gameGrid[row][column])
                            .orElse(new Cell(row, column, CellStatus.DEAD));
                    cell.addNeighbour(neighbour);
//                    if(this.gameGrid[row][column] == 1) {
//                        cellNeighbours.addAliveNeighbour();
//                    }
                }
            }
        }
//        System.out.println("Number of neighbours: " + cell.getNumberOfNeighbours() + ", " +
//                "Alive: " + cell.getNumberOfAliveNeighbours());
        return cell;
    }

    private int fromRow(int x) {
        return Math.max(x - 1, 0);
    }

    private int toRow(int x) {
        return (x + 1 >= this.gameGrid.length) ? x : x + 1;
    }

    private int fromColumn(int y) {
        return Math.max(y - 1, 0);
    }

    private int toColumn(int y) {
        return (y + 1 >= this.gameGrid.length) ? y : y + 1;
    }

    public int getNumberOfRows() {
        return this.gameGrid.length;
    }

    public int getNumberOfColumns() {
        return this.gameGrid.length;
    }

    public int getTotalOfCells() {
        return this.totalOfCells;
    }

//    class CellNeighbours {
//
//        private int totalNeighbours;
//        private int aliveNeigbours;
//
//        public CellNeighbours() {
//            this.totalNeighbours = 0;
//            this.aliveNeigbours = 0;
//        }
//
//        public void addNeighbour() {
//            this.totalNeighbours++;
//        }
//
//        public void addAliveNeighbour() {
//            this.aliveNeigbours++;
//        }
//
//        public int getTotalNeighbours() {
//            return totalNeighbours;
//        }
//
//        public int getAliveNeigbours() {
//            return aliveNeigbours;
//        }
//    }
}
