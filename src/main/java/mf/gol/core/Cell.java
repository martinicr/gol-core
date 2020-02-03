package mf.gol.core;

import static java.util.Objects.requireNonNull;

public class Cell {

    private int x;
    private int y;
    private CellStatus cellStatus;

    public Cell(int x, int y) {
        this(x, y, CellStatus.DEAD);
    }

    public Cell(int x, int y, CellStatus cellStatus) {
        this.x = x;
        this.y = y;
        this.cellStatus = requireNonNull(cellStatus);
    }

//    public int getNumberOfNeighbours(){
//        return this.neighbours.size();
//    }
//
//    public int getNumberOfAliveNeighbours(){
//        return (int) this.neighbours.stream()
//                .filter(n -> n.getCellStatus() == CellStatus.ALIVE)
//                .count();
//    }

//    public List<Cell> getNeighbours() {
//        return neighbours;
//    }
//
//    public void setNeighbours(List<Cell> neighbours) {
//        this.neighbours = neighbours;
//    }

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

//    public void addNeighbour(Cell neighbour){
//        this.neighbours.add(neighbour);
//    }

//    public String getNeighboursCoordinatesToString(){
//        return this.getNeighbours().stream()
//                .map(n -> "{"+ n.getX() +", "+ n.getY() +"}")
//                .collect(joining(", "));
//    }

    public static Cell getInstance() {
        return new Cell(0, 0, CellStatus.DEAD);
    }
}
