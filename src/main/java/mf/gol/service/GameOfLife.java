package mf.gol.service;

import static mf.gol.service.CellStatus.*;

public class GameOfLife {


    public static Grid newGeneration(Grid initialState){
        Grid newState = new Grid(initialState.getNumberOfRows());
        return newState;
    }

    public static Cell nextCellStatus(Grid initialGrid, Cell cell) {
        CellNeighbours cellNeighbours = initialGrid.getCellNeighbours(cell);
        Cell nextCell = new Cell(cell.getX(), cell.getY(), DEAD);
        //TODO: The following if/else statements can be changed to something simplier. I will leave it as it is for now
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
