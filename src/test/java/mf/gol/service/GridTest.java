package mf.gol.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class GridTest {

    @Test
    void gridSize2by2IsNotAllowed(){
        assertThrows(RuntimeException.class, () -> new Grid(2, 2));
    }

    @Test
    void gridSize11by11IsNotAllowed(){
        assertThrows(RuntimeException.class, () -> new Grid(11, 11));
    }

    @Test
    @DisplayName("Grid size should be at least 3 x 3")
    void gridSizeMinimunIs3by3(){
        Grid grid = new Grid(3, 3);
        assertThat(grid.getNumberOfRows()).isEqualTo(3);
        assertThat(grid.getNumberOfColumns()).isEqualTo(3);
    }

    @Test
    void addCellToGrid() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);

        given(cell.getX()).willReturn(0);
        given(cell.getY()).willReturn(0);

        grid.addCell(cell);
        Cell actual = grid.getCell(0, 0);

        verify(cell, times(1)).getX();
        verify(cell, times(1)).getY();

        assertThat(actual).isEqualToComparingFieldByField(cell);
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 0,0")
    void getCellNeighboursWhenPositionIs_0_0() {
        Grid grid = new Grid(3, 3);
        Cell c1 = mock(Cell.class);
        Cell c2 = mock(Cell.class);
        Cell c3 = mock(Cell.class);

        given(c1.getX()).willReturn(0);
        given(c1.getY()).willReturn(0);
        given(c2.getX()).willReturn(1);
        given(c2.getY()).willReturn(1);
        given(c2.getCellStatus()).willReturn(CellStatus.ALIVE);
        given(c3.getX()).willReturn(2);
        given(c3.getY()).willReturn(2);

        grid.addCell(c1, c2, c3);
        CellNeighbours neighbours = grid.getCellNeighbours(0, 0);

        verify(c3, times(0)).getCellStatus();

        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(3);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(1);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 1}, {1, 0}, {1, 1}");
    }


    @Test
    @DisplayName("Get cell neighbours when cell position is 0,1")
    void getCellNeighboursWhenPositionIs_0_1() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(0);
        given(cell.getY()).willReturn(1);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(0, 1);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(5);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 0}, {0, 2}, {1, 0}, {1, 1}, {1, 2}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 0,2")
    void getCellNeighboursWhenPositionIs_0_2() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(0);
        given(cell.getY()).willReturn(2);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(0, 2);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(3);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 1}, {1, 1}, {1, 2}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 1,0")
    void getCellNeighboursWhenPositionIs_1_0() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(1);
        given(cell.getY()).willReturn(0);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(1, 0);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(5);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 0}, {0, 1}, {1, 1}, {2, 0}, {2, 1}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 1,1")
    void getCellNeighboursWhenPositionIs_1_1() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(1);
        given(cell.getY()).willReturn(1);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(1, 1);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(8);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}, {2, 2}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 1,2")
    void getCellNeighboursWhenPositionIs_1_2() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(1);
        given(cell.getY()).willReturn(2);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(1, 2);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(5);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 1}, {0, 2}, {1, 1}, {2, 1}, {2, 2}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 2,0")
    void getCellNeighboursWhenPositionIs_2_0() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(2);
        given(cell.getY()).willReturn(0);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(2, 0);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(3);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{1, 0}, {1, 1}, {2, 1}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 2,1")
    void getCellNeighboursWhenPositionIs_2_1() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(2);
        given(cell.getY()).willReturn(1);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(2, 1);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(5);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 2}");
    }

    @Test
    @DisplayName("Get cell neighbours when cell position is 2,2")
    void getCellNeighboursWhenPositionIs_2_2() {
        Grid grid = new Grid(3, 3);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(2);
        given(cell.getY()).willReturn(2);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(2, 2);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(3);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{1, 1}, {1, 2}, {2, 1}");
    }

    @Test
    @DisplayName("5x5 Grid Get cell neighbours when cell position is 0,0")
    void getCellNeighboursWhenGridIs5by5AndPositionIs_0_0() {
        Grid grid = new Grid(5, 5);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(0);
        given(cell.getY()).willReturn(0);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(0, 0);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(3);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{0, 1}, {1, 0}, {1, 1}");
    }

    @Test
    @DisplayName("5x5 Grid Get cell neighbours when cell position is 2,2")
    void getCellNeighboursWhenGridIs5by5AndPositionIs_2_2() {
        Grid grid = new Grid(5, 5);
        Cell cell = mock(Cell.class);
        given(cell.getX()).willReturn(2);
        given(cell.getY()).willReturn(2);
        grid.addCell(cell);
        CellNeighbours neighbours = grid.getCellNeighbours(2, 2);
        assertThat(neighbours.getNumberOfNeighbours()).isEqualTo(8);
        assertThat(neighbours.getNumberOfAliveNeighbours()).isEqualTo(0);
        assertThat(neighbours.getNeighboursCoordinatesToString()).isEqualTo("{1, 1}, {1, 2}, {1, 3}, {2, 1}, {2, 3}, {3, 1}, {3, 2}, {3, 3}");
    }

    /**
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    @Test
    void printRawGridValues() {
        Grid grid = Grid.squareGrid(5);
        grid.addCell(new Cell(2,2, CellStatus.ALIVE));
        grid.addCell(new Cell(3,3, CellStatus.ALIVE));
        grid.addCell(new Cell(1,0, CellStatus.DEAD));
        String actual = grid.printGrid();

        assertThat(actual).isEqualTo("" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 1 0 0\n" +
                "0 0 0 1 0\n" +
                "0 0 0 0 0\n");
    }

}
