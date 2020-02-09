package mf.gol.core;

import mf.gol.core.Cell;
import mf.gol.core.GameOfLife;
import mf.gol.core.Grid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static mf.gol.core.CellStatus.*;
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
     * ===========================================
     * Still lives
     * ===========================================
     */

    /**
     * Initial Status
     *
     * 0 0 0 0
     * 0 1 1 0
     * 0 1 1 0
     * 0 0 0 0
     */
    @Test
    @DisplayName("when block pattern is passed as input then return new grid with same cell distribution")
    void gridWithBlockPatternThenReturnNewGridWithSameCellDistribution() {
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
    @DisplayName("when beehive pattern is passed as input then return new grid with same cell distribution")
    void gridWithBeehivePatternThenReturnNewGridWithSameCellDistribution() {
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
     * 0 0 0 0 0 0
     * 0 0 1 1 0 0
     * 0 1 0 0 1 0
     * 0 0 1 0 1 0
     * 0 0 0 1 0 0
     * 0 0 0 0 0 0
     */
    @Test
    @DisplayName("when loaf pattern is passed as input then return new grid with same cell distribution")
    void gridWithLoafPatternThenReturnNewGridWithSameCellDistribution() {
        Grid initialGrid = Grid.squareGrid(6);
        Cell cell1 = new Cell(1,2, ALIVE);
        Cell cell2 = new Cell(1,3, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(2,4, ALIVE);
        Cell cell5 = new Cell(3,2, ALIVE);
        Cell cell6 = new Cell(3,4, ALIVE);
        Cell cell7 = new Cell(4,3, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5, cell6, cell7);

        Grid state1 = GameOfLife.newGeneration(initialGrid);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 1 0 0 1 0\n" +
                "0 0 1 0 1 0\n" +
                "0 0 0 1 0 0\n" +
                "0 0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0
     * 0 1 1 0 0
     * 0 1 0 1 0
     * 0 0 1 0 0
     * 0 0 0 0 0
     */
    @Test
    @DisplayName("when boat pattern is passed as input then return new grid with same cell distribution")
    void gridWithBoatPatterThenReturnNewGridWithSameCellDistribution() {
        Grid initialGrid = Grid.squareGrid(5);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(2,3, ALIVE);
        Cell cell5 = new Cell(3,3, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5);

        Grid state1 = GameOfLife.newGeneration(initialGrid);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 1 1 0 0\n" +
                "0 1 0 1 0\n" +
                "0 0 1 0 0\n" +
                "0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0
     * 0 0 1 0 0
     * 0 1 0 1 0
     * 0 0 1 0 0
     * 0 0 0 0 0
     */
    @Test
    @DisplayName("when tub pattern is passed as input then return new grid with same cell distribution")
    void gridWithTubPatternThenReturnNewGridWithSameCellDistribution() {
        Grid initialGrid = Grid.squareGrid(5);
        Cell cell1 = new Cell(1,2, ALIVE);
        Cell cell2 = new Cell(2,1, ALIVE);
        Cell cell3 = new Cell(2,3, ALIVE);
        Cell cell4 = new Cell(3,2, ALIVE);
        initialGrid.addCell(cell1, cell2, cell3, cell4);

        Grid state1 = GameOfLife.newGeneration(initialGrid);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 0 1 0 0\n" +
                "0 1 0 1 0\n" +
                "0 0 1 0 0\n" +
                "0 0 0 0 0\n");
    }

    /**
     * ===========================================
     * Oscillators
     * ===========================================
     */

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
    @DisplayName("blinker period 2")
    void gridWithBlinkerPatternPeriod2() {
        Grid initialGrid = new Grid(5, 5);
        Cell cell1 = new Cell(1,2, ALIVE);
        Cell cell2 = new Cell(2,2, ALIVE);
        Cell cell3 = new Cell(3,2, ALIVE);

        initialGrid.addCell(cell1, cell2, cell3);
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        Grid state2 = GameOfLife.newGeneration(state1);
        Grid state3 = GameOfLife.newGeneration(state2);

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

        assertThat(state3.printGrid()).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 1 1 1 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0 0
     * 0 0 0 1 0 0
     * 0 1 0 0 1 0
     * 0 1 0 0 1 0
     * 0 0 1 0 0 0
     * 0 0 0 0 0 0
     */
    @Test
    @DisplayName("toad period 2")
    void gridWithToadPatternPeriod2() {
        Grid initialGrid = Grid.squareGrid(6);
        Cell cell1 = new Cell(2,1, ALIVE);
        Cell cell2 = new Cell(3,1, ALIVE);
        Cell cell3 = new Cell(4,2, ALIVE);
        Cell cell4 = new Cell(1,3, ALIVE);
        Cell cell5 = new Cell(2,4, ALIVE);
        Cell cell6 = new Cell(3,4, ALIVE);

        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5, cell6);
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        Grid state2 = GameOfLife.newGeneration(state1);
        Grid state3 = GameOfLife.newGeneration(state2);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 1 1 1 0\n" +
                "0 1 1 1 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n");

        assertThat(state2.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 0 0 1 0 0\n" +
                "0 1 0 0 1 0\n" +
                "0 1 0 0 1 0\n" +
                "0 0 1 0 0 0\n" +
                "0 0 0 0 0 0\n");

        assertThat(state3.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 1 1 1 0\n" +
                "0 1 1 1 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0 0
     * 0 1 1 0 0 0
     * 0 1 0 0 1 0
     * 0 0 0 0 1 0
     * 0 0 1 0 0 0
     * 0 0 0 0 0 0
     */
    @Test
    @DisplayName("beacon period 2")
    void gridWithBeaconPatternPeriod2() {
        Grid initialGrid = Grid.squareGrid(6);
        Cell cell1 = new Cell(1,1, ALIVE);
        Cell cell2 = new Cell(1,2, ALIVE);
        Cell cell3 = new Cell(2,1, ALIVE);
        Cell cell4 = new Cell(4,3, ALIVE);
        Cell cell5 = new Cell(4,4, ALIVE);
        Cell cell6 = new Cell(3,4, ALIVE);

        initialGrid.addCell(cell1, cell2, cell3, cell4, cell5, cell6);
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        Grid state2 = GameOfLife.newGeneration(state1);
        Grid state3 = GameOfLife.newGeneration(state2);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 1 1 0 0 0\n" +
                "0 1 1 0 0 0\n" +
                "0 0 0 1 1 0\n" +
                "0 0 0 1 1 0\n" +
                "0 0 0 0 0 0\n");

        assertThat(state2.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 1 1 0 0 0\n" +
                "0 1 0 0 0 0\n" +
                "0 0 0 0 1 0\n" +
                "0 0 0 1 1 0\n" +
                "0 0 0 0 0 0\n");

        assertThat(state3.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0\n" +
                "0 1 1 0 0 0\n" +
                "0 1 1 0 0 0\n" +
                "0 0 0 1 1 0\n" +
                "0 0 0 1 1 0\n" +
                "0 0 0 0 0 0\n");
    }

    /**
     * Initial Status
     *
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
     * 0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
     * 0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
     * 0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0
     * 0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
     * 0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
     * 0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     */
    @Test
    @DisplayName("pulsar period 3")
    void gridWithPulsarPatternPeriod3() {
        Grid initialGrid = Grid.squareGrid(17);
        initialGrid.addCell(
                new Cell(2,4, ALIVE),
                new Cell(2,5, ALIVE),
                new Cell(2,6, ALIVE),
                new Cell(2,10, ALIVE),
                new Cell(2,11, ALIVE),
                new Cell(2,12, ALIVE),
                new Cell(4,2, ALIVE),
                new Cell(4,7, ALIVE),
                new Cell(4, 9, ALIVE),
                new Cell(4, 14, ALIVE),
                new Cell(5,2, ALIVE),
                new Cell(5,7, ALIVE),
                new Cell(5, 9, ALIVE),
                new Cell(5, 14, ALIVE),
                new Cell(6,2, ALIVE),
                new Cell(6,7, ALIVE),
                new Cell(6, 9, ALIVE),
                new Cell(6, 14, ALIVE),
                new Cell(7,4, ALIVE),
                new Cell(7,5, ALIVE),
                new Cell(7,6, ALIVE),
                new Cell(7,10, ALIVE),
                new Cell(7,11, ALIVE),
                new Cell(7,12, ALIVE),
                new Cell(9,4, ALIVE),
                new Cell(9,5, ALIVE),
                new Cell(9,6, ALIVE),
                new Cell(9,10, ALIVE),
                new Cell(9,11, ALIVE),
                new Cell(9,12, ALIVE),
                new Cell(10,2, ALIVE),
                new Cell(10,7, ALIVE),
                new Cell(10, 9, ALIVE),
                new Cell(10, 14, ALIVE),
                new Cell(11,2, ALIVE),
                new Cell(11,7, ALIVE),
                new Cell(11, 9, ALIVE),
                new Cell(11, 14, ALIVE),
                new Cell(12,2, ALIVE),
                new Cell(12,7, ALIVE),
                new Cell(12, 9, ALIVE),
                new Cell(12, 14, ALIVE),
                new Cell(14,4, ALIVE),
                new Cell(14,5, ALIVE),
                new Cell(14,6, ALIVE),
                new Cell(14,10, ALIVE),
                new Cell(14,11, ALIVE),
                new Cell(14,12, ALIVE)
        );

//        System.out.println(initialGrid.printGrid());
        Grid state1 = GameOfLife.newGeneration(initialGrid);
        Grid state2 = GameOfLife.newGeneration(state1);
        Grid state3 = GameOfLife.newGeneration(state2);

        assertThat(state1.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 1 1 1 0 0 1 1 0 1 1 0 0 1 1 1 0\n" +
                "0 0 0 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n" +
                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n" +
                "0 1 1 1 0 0 1 1 0 1 1 0 0 1 1 1 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n"
        );

        assertThat(state2.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 1 0 0 0 0 0 1 1 0 0 0 0\n" +
                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 1 0 0 1 0 1 0 1 0 1 0 0 1 0 0\n" +
                "0 0 1 1 1 0 1 1 0 1 1 0 1 1 1 0 0\n" +
                "0 0 0 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0\n" +
                "0 0 0 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n" +
                "0 0 1 1 1 0 1 1 0 1 1 0 1 1 1 0 0\n" +
                "0 0 1 0 0 1 0 1 0 1 0 1 0 0 1 0 0\n" +
                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
                "0 0 0 0 1 1 0 0 0 0 0 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n"
        );

        assertThat(state3.printGrid()).isEqualTo("" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0\n" +
                "0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0\n" +
                "0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0\n" +
                "0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0\n" +
                "0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0\n" +
                "0 0 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0 1 1 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n"
        );
    }

    @Test
    @DisplayName("pentadecathlon period 15")
    void gridWithPentadecathlonPatternPeriod15() {
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

        System.out.println(initialGrid.printGrid());
        Grid state1 = GameOfLife.newGeneration(initialGrid);
//        Grid state2 = GameOfLife.newGeneration(state1);
//        Grid state3 = GameOfLife.newGeneration(state2);

//        assertThat(state1.printGrid()).isEqualTo("" +
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
//                "0 1 1 1 0 0 1 1 0 1 1 0 0 1 1 1 0\n" +
//                "0 0 0 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n" +
//                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
//                "0 0 0 1 0 1 0 1 0 1 0 1 0 1 0 0 0\n" +
//                "0 1 1 1 0 0 1 1 0 1 1 0 0 1 1 1 0\n" +
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0\n" +
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n"
//        );
    }


}
