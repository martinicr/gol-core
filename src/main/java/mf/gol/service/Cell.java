package mf.gol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class Cell {

    private List<Cell> neighbours;
    private int x;
    private int y;
    private CellStatus cellStatus;

    public Cell(int x, int y, CellStatus cellStatus) {
        this(x, y, cellStatus, new ArrayList<>());
    }

    public Cell(int x, int y, CellStatus cellStatus, List<Cell> neighbours) {
        this.x = x;
        this.y = y;
        this.cellStatus = requireNonNull(cellStatus);
        this.neighbours = requireNonNull(neighbours);
    }

    public int getNumberOfNeighbours(){
        return this.neighbours.size();
    }

    public int getNumberOfAliveNeighbours(){
        return (int) this.neighbours.stream()
                .filter(n -> n.getCellStatus() == CellStatus.ALIVE)
                .count();
    }

    public List<Cell> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Cell> neighbours) {
        this.neighbours = neighbours;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public boolean isAlive(){
        return this.cellStatus == CellStatus.ALIVE;
    }

    public void addNeighbour(Cell neighbour){
        this.neighbours.add(neighbour);
    }

    public static Cell getInstance() {
        return new Cell(0, 0, CellStatus.DEAD, new ArrayList<>());
    }

    public String getNeighboursCoordinatesToString(){
        return this.getNeighbours().stream()
                .map(n -> "{"+ n.getX() +", "+ n.getY() +"}")
                .collect(joining(", "));
    }
}
