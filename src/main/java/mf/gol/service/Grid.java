package mf.gol.service;

import static java.util.Optional.ofNullable;
import static mf.gol.service.CellStatus.*;

public class Grid {

    private static final int MIN_GRID_SIZE = 3;
    private static final int MAX_GRID_SIZE = 10;

    private Cell[][] gameGrid;
    private final int rows;
    private final int columns;

    public Grid(int rows, int columns) {
        if(rows < MIN_GRID_SIZE || columns > MAX_GRID_SIZE) {
            throw new RuntimeException("Grid dimension is not allowed");
        }
        this.rows = rows;
        this.columns = columns;
        this.gameGrid = new Cell[rows][columns];
    }

    public static Grid squareGrid(int dimension) {
        return new Grid(dimension, dimension);
    }

    public Cell getCell(int x, int y) {
        //TODO: validaate x, y coordinates
        if(null == this.gameGrid[x][y]) {
            return new Cell(x, y);
        }
        return this.gameGrid[x][y];
    }

    public void addCell(Cell... cells) {
        for(Cell cell: cells) {
            this.gameGrid[cell.getX()][cell.getY()] = cell;
        }
    }

    public CellNeighbours getCellNeighbours(Cell cell) {
        return this.getCellNeighbours(cell.getX(), cell.getY());
    }

    public CellNeighbours getCellNeighbours(int x, int y) {
        CellNeighbours cellNeighbours = new CellNeighbours();

        for (int row = fromRow(x); row <= toRow(x); row++) {
            for (int column = fromColumn(y); column <= toColumn(y); column++) {
//                System.out.println("row " + row + ", column " + column);
                if(row == x && column == y) {
                    System.out.println("SAME =>> x " + x + ", row " + row + ", y " + y + ", column " + column);
                    continue;
                } else {
//                    try {
                        System.out.println("OTRO =>> x " + x + ", row " + row + ", y " + y + ", column " + column);
                        Cell neighbour = ofNullable(this.gameGrid[row][column])
                                .orElse(new Cell(row, column, DEAD));
                        cellNeighbours.addNeighbour(neighbour);
//                    } catch (Exception e) {
//                        System.out.println("Exp row " + row + " col " + column);
//                        e.printStackTrace();
//                    }
                }
            }
        }
        System.out.println("Cell neighbours: " + cellNeighbours.getNumberOfNeighbours() + ", " +
                "Alive: " + cellNeighbours.getNumberOfAliveNeighbours());
        return cellNeighbours;
    }

    public int getNumberOfRows() {
        return this.rows;
    }

    public int getNumberOfColumns() {
        return this.columns;
    }

    public String printGrid() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                int status = ofNullable(this.gameGrid[i][j])
                        .map(c -> (c.getCellStatus() == ALIVE) ? 1 : 0)
                        .orElse(0);
                sb.append(status);

                if(j != (this.columns - 1)) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
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

}
