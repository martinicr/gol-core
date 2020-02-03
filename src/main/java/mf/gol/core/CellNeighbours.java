package mf.gol.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;
import static mf.gol.core.CellStatus.*;

public class CellNeighbours {

    private final List<Cell> cells;

    public CellNeighbours() {
        this(new ArrayList<>());
    }

    public CellNeighbours(List<Cell> cells) {
        this.cells = cells;
    }

    public int getNumberOfNeighbours() {
        return this.cells.size();
    }

    public int getNumberOfAliveNeighbours() {
        return (int) this.cells.stream()
                .filter(Objects::nonNull)
                .filter(n -> n.getCellStatus() == ALIVE)
                .count();
    }

    public String getNeighboursCoordinatesToString(){
        return this.cells.stream()
                .filter(Objects::nonNull)
                .map(n -> "{"+ n.getX() +", "+ n.getY() +"}")
                .collect(joining(", "));
    }

    public void addNeighbour(Cell neighbour){
        this.cells.add(neighbour);
    }
}
