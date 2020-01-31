package mf.gol.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static mf.gol.service.CellStatus.*;
import static org.assertj.core.api.Assertions.*;

public class GameOfLifeTest {

    @Test
    @DisplayName("when block grid is passed as input then return new grid with same cell distribution")
    void blockGridIsPassedThenReturnNewGridWithSameCellDistribution() {
        Grid initialGrid = new Grid(5);
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        assertThat(initialGrid).isEqualToComparingFieldByField(state1);
    }

    @Test
    void aliveCellWithNoNeighboursDies() {
        Grid initialGrid = new Grid(5);
        Cell currentCell = new Cell(1,1, ALIVE);
        initialGrid.addCell(currentCell);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, currentCell);

        assertThat(actual.getCellStatus()).isEqualTo(DEAD);
    }

    @Test
    void aliveCellWithOneNeighboursDies() {
        Grid initialGrid = new Grid(5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        initialGrid.addCell(cell1, cell2);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

//        assertThat(actual.getNumberOfAliveNeighbours()).isEqualTo(1);
//        assertThat(actual.getCellStatus()).isEqualTo(DEAD);
    }

    @Test
    void aliveCellWithTwoNeighboursIsStillAlive() {
        Grid initialGrid = new Grid(5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

//        assertThat(actual.getNumberOfAliveNeighbours()).isEqualTo(2);
//        assertThat(actual.getCellStatus()).isEqualTo(ALIVE);
    }

    @Test
    void aliveCellWithThreeNeighboursIsStillAlive() {
        Grid initialGrid = new Grid(5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(0,1, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

//        assertThat(actual.getNumberOfAliveNeighbours()).isEqualTo(3);
//        assertThat(actual.getCellStatus()).isEqualTo(ALIVE);
    }

    @Test
    void aliveCellWithFourNeighboursWillDie() {
        Grid initialGrid = new Grid(5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(0,1, ALIVE);
        Cell cell5 = new Cell(0,2, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

//        assertThat(actual.getNumberOfAliveNeighbours()).isEqualTo(4);
//        assertThat(actual.getCellStatus()).isEqualTo(DEAD);
    }

    @Test
    void deadCellWithExactlyThreeNeighboursWillLive() {
        Grid initialGrid = new Grid(5);
        Cell cell1 = new Cell(1,1, DEAD);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(0,1, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

//        assertThat(actual.getNumberOfAliveNeighbours()).isEqualTo(3);
//        assertThat(actual.getCellStatus()).isEqualTo(ALIVE);
    }


//    @Test
//    @DisplayName("Grid size should be at least 3 x 3")
//    void gridSizeMinimunIs3by3(){
//
//        int[][] grid = new int[3][3];
//        for(int i = 0; i < grid.length; i++) {
//            System.out.println("I is " + i + " grid len: " + grid.length);
//            for(int j = 0; j < grid.length; j++) {
//                System.out.println("\tJ is " + j + " grid len: " + grid.length);
//            }
//        }
//
//    }

}
