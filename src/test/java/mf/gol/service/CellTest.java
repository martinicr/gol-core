package mf.gol.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static mf.gol.service.CellStatus.*;

public class CellTest {

    @Test
    void getNumberOfNeighbours() {
        Cell c1 = mock(Cell.class);
        Cell c2 = mock(Cell.class);
        Cell cell = new Cell(0, 0, DEAD, List.of(c1, c2));
        int numberOfNeighbours = cell.getNumberOfNeighbours();
        assertThat(numberOfNeighbours).isEqualTo(2);
    }

    @Test
    void getNumberOfAliveNeighbours() {
        Cell c1 = mock(Cell.class);
        Cell c2 = mock(Cell.class);
        Cell c3 = mock(Cell.class);

        given(c1.getCellStatus()).willReturn(ALIVE);
        given(c2.getCellStatus()).willReturn(DEAD);
        given(c3.getCellStatus()).willReturn(DEAD);

        Cell cell = new Cell(0, 0, DEAD, List.of(c1, c2, c3));
        int numberOfNeighbours = cell.getNumberOfAliveNeighbours();
        assertThat(numberOfNeighbours).isEqualTo(1);
    }

    @Test
    void neighboursCoordinates() {

        Cell c1 = mock(Cell.class);
        Cell c2 = mock(Cell.class);

        given(c1.getX()).willReturn(0);
        given(c1.getY()).willReturn(1);
        given(c2.getX()).willReturn(1);
        given(c2.getY()).willReturn(0);

        Cell cell = new Cell(0, 0, DEAD, List.of(c1, c2));

        String coordinates = cell.getNeighboursCoordinatesToString();

        System.out.println(coordinates);

    }

}
