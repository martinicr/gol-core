package mf.gol.core;

import static mf.gol.core.CellStatus.*;

public class GameOfLife {


    public static Grid newGeneration(Grid initialGrid){
        Grid newState = new Grid(initialGrid.getNumberOfRows(), initialGrid.getNumberOfColumns());
        for(int i = 0; i < initialGrid.getNumberOfRows(); i++) {
            for(int j = 0; j < initialGrid.getNumberOfColumns(); j++) {
                Cell newCell = nextCellStatus(initialGrid, initialGrid.getCell(i, j));
                newState.addCell(newCell);
            }
        }
        return newState;
    }

    public static Cell nextCellStatus(Grid initialGrid, Cell cell) {
        CellNeighbours cellNeighbours = initialGrid.getCellNeighbours(cell);
        Cell nextCell = new Cell(cell.getX(), cell.getY());
        //TODO: The following if/else statements can be changed to something simpler. I will leave it as it is for now
        //to make things more explicit
        if(cell.getCellStatus() == ALIVE) {
            if (cellNeighbours.getNumberOfAliveNeighbours() < 2) {
                nextCell.setCellStatus(DEAD);
            }
            if (cellNeighbours.getNumberOfAliveNeighbours() >= 2 && cellNeighbours.getNumberOfAliveNeighbours() <= 3) {
                nextCell.setCellStatus(ALIVE);
            }
            if (cellNeighbours.getNumberOfAliveNeighbours() > 3) {
                nextCell.setCellStatus(DEAD);
            }
        } else {
            if(cellNeighbours.getNumberOfAliveNeighbours() == 3) {
                nextCell.setCellStatus(ALIVE);
            }
        }
        return nextCell;
    }

}
