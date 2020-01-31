package mf.gol.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static mf.gol.service.CellStatus.ALIVE;
import static mf.gol.service.CellStatus.DEAD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CellNeighboursTest {

    @Test
    void getNumberOfNeighbours() {
        Cell c1 = mock(Cell.class);
        Cell c2 = mock(Cell.class);
        CellNeighbours cellNeighbours = new CellNeighbours(List.of(c1, c2));
        int numberOfNeighbours = cellNeighbours.getNumberOfNeighbours();

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

        CellNeighbours cellNeighbours = new CellNeighbours(List.of(c1, c2, c3));
        int numberOfNeighbours = cellNeighbours.getNumberOfAliveNeighbours();
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

        CellNeighbours cellNeighbours = new CellNeighbours(List.of(c1, c2));
        String coordinates = cellNeighbours.getNeighboursCoordinatesToString();

        assertThat(coordinates).isEqualTo("{0, 1}, {1, 0}");
    }

}
