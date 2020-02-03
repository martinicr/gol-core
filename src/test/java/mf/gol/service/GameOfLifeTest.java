package mf.gol.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static mf.gol.service.CellStatus.*;
import static org.assertj.core.api.Assertions.*;

public class GameOfLifeTest {

    /**
     * 0 0 0 0 0
     * 0 1 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void aliveCellWithNoNeighboursDies() {
        Grid initialGrid = new Grid(5, 5);
        Cell currentCell = new Cell(1,1, ALIVE);
        initialGrid.addCell(currentCell);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, currentCell);

        assertThat(actual.getCellStatus()).isEqualTo(DEAD);
    }

    /**
     * 0 0 0 0 0
     * 0 1 1 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void aliveCellWithOneNeighboursDies() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        initialGrid.addCell(cell1, cell2);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

        assertThat(actual.getCellStatus()).isEqualTo(DEAD);
    }

    /**
     * 0 0 0 0 0
     * 0 1 1 0 0
     * 0 1 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void aliveCellWithTwoNeighboursIsStillAlive() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

        assertThat(actual.getCellStatus()).isEqualTo(ALIVE);
    }

    /**
     * 0 1 0 0 0
     * 0 1 1 0 0
     * 0 1 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void aliveCellWithThreeNeighboursIsStillAlive() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(0,1, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

        assertThat(actual.getCellStatus()).isEqualTo(ALIVE);
    }

    /**
     * 0 1 1 0 0
     * 0 1 1 0 0
     * 0 1 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void aliveCellWithFourNeighboursWillDie() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(0,1, ALIVE);
        Cell cell5 = new Cell(0,2, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

        assertThat(actual.getCellStatus()).isEqualTo(DEAD);
    }

    /**
     * 0 1 0 0 0
     * 0 0 1 0 0
     * 0 1 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void deadCellWithExactlyThreeNeighboursWillLive() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,1, DEAD);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(0,1, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4);
        Cell actual = GameOfLife.nextCellStatus(initialGrid, cell1);

        assertThat(actual.getCellStatus()).isEqualTo(ALIVE);
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    @DisplayName("when a grid with all dead cells is passed next generation will be dead")
    void gridWillAllDeadCellsWillBeDeadInNextGeneration() {
        Grid initialGrid = new Grid(5, 5);
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0
     * 0 1 1 0
     * 0 1 1 0
     * 0 0 0 0
     */
    @Test
    @DisplayName("when block grid is passed as input then return new grid with same cell distribution")
    void gridWithBlockIsPassedThenReturnNewGridWithSameCellDistribution() {
        Grid initialGrid = new Grid(4, 4);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(2,2, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4);

        Grid state1 = GameOfLife.newGeneration(initialGrid);
        Grid state2 = GameOfLife.newGeneration(state1);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0\n" +
                "0 1 1 0\n" +
                "0 1 1 0\n" +
                "0 0 0 0\n");

        assertThat(state2.printGrid()).isEqualTo("" +
                "0 0 0 0\n" +
                "0 1 1 0\n" +
                "0 1 1 0\n" +
                "0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0 0
     * 0 0 1 1 0 0
     * 0 1 0 0 1 0
     * 0 0 1 1 0 0
     * 0 0 0 0 0 0
     */
    @Test
    @DisplayName("when beehive grid is passed as input then return new grid with same cell distribution")
    void gridWithBeehiveIsPassedThenReturnNewGridWithSameCellDistribution() {
        Grid initialGrid = new Grid(5, 6);
        Cell cell1 = new Cell(1,2, ALIVE);
        Cell cell2 = new Cell(1,3, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(2,4, ALIVE);
        Cell cell5 = new Cell(3,2, ALIVE);
        Cell cell6 = new Cell(3,3, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5, cell6);

        Grid state1 = GameOfLife.newGeneration(initialGrid);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 1 0 0 1 0\n" +
                "0 0 1 1 0 0\n" +
                "0 0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0
     * 0 0 1 0 0
     * 0 0 1 0 0
     * 0 0 1 0 0
     * 0 0 0 0 0
     */
    @Test
    void nextGeneration1() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,2, ALIVE);
        Cell cell2 = new Cell(2,2, ALIVE);
        Cell cell3 = new Cell(3,2, ALIVE);

        initialGrid.addCell(cell1, cell2, cell3);
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        Grid state2 = GameOfLife.newGeneration(state1);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 1 1 1 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n");

        assertThat(state2.printGrid()).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 0 1 0 0\n" +
                "0 0 1 0 0\n" +
                "0 0 1 0 0\n" +
                "0 0 0 0 0\n");
    }

}
