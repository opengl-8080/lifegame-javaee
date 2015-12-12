package gl8080.lifegame.web.resource;

import java.util.List;
import java.util.function.BiConsumer;

import gl8080.lifegame.logic.Position;

public class LifeGameDto {
    private long id;
    private int size;
    private List<List<Boolean>> cells;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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
    
    public void eachCell(BiConsumer<Position, Boolean> iterator) {
        for (int v=0; v<this.cells.size(); v++) {
            List<Boolean> row = this.cells.get(v);
            for (int h=0; h<row.size(); h++) {
                Position p = new Position(v, h);
                iterator.accept(p, row.get(h));
            }
        }
    }
    
    @Override
    public String toString() {
        return "LifeGameDto [id=" + id + ", size=" + size + ", cells=" + cells + "]";
    }
}
