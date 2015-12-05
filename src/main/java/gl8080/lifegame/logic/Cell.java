package gl8080.lifegame.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cell {

    public static Cell dead() {
        return new Cell(false);
    }

    public static Cell alive() {
        return new Cell(true);
    }
    
    private boolean alive;
    private Boolean nextStatus;
    private List<Cell> neighbors = Collections.emptyList();
    
    private Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void reserveNextStatus() {
        long liveCellCount = neighbors.stream().filter(Cell::isAlive).count();
        
        if (4 <= liveCellCount) {
            this.nextStatus = false;
        } else if (liveCellCount == 3) {
            this.nextStatus = true;
        } else if (liveCellCount == 2) {
            this.nextStatus = this.alive;
        } else {
            this.nextStatus = false;
        }
    }

    public void stepNextStatus() {
        if (this.nextStatus == null) {
            throw new IllegalStateException("次の状態への遷移が予約されていません。");
        }
        this.alive = this.nextStatus;
        this.nextStatus = null;
    }

    boolean toBeAlive() {
        if (this.nextStatus == null) {
            throw new IllegalStateException("次の状態への遷移が予約されていません。");
        }
        return this.nextStatus;
    }

    void setNeighbors(List<Cell> neighbors) {
        Objects.requireNonNull(neighbors, "隣接するセルが null です。");
        this.neighbors = new ArrayList<>(neighbors);
    }

    public List<Cell> getNeighbors() {
        return new ArrayList<>(this.neighbors);
    }

}
