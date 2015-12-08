package gl8080.lifegame.web.resource;

import java.util.List;

public class LifeGameDto {
    
    private int size;
    private List<List<Boolean>> cells;
    
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<List<Boolean>> getCells() {
        return cells;
    }
    public void setCells(List<List<Boolean>> cells) {
        this.cells = cells;
    }
    
    @Override
    public String toString() {
        return "LifeGameDto [size=" + size + ", cells=" + cells + "]";
    }
}
