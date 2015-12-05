package gl8080.lifegame.logic;

import java.util.Arrays;
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
        } else if (liveCellCount == 3 || liveCellCount == 2) {
            this.nextStatus = true;
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

    void setNeighbors(Cell... neighbors) {
        Objects.requireNonNull(neighbors, "隣接するセルが null です。");
        this.neighbors = Arrays.asList(neighbors);
    }

}
