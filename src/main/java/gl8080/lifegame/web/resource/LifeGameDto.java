package gl8080.lifegame.web.resource;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import gl8080.lifegame.logic.Cell;
import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.Position;
import gl8080.lifegame.logic.definition.CellDefinition;
import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.util.NestedLoop;

public class LifeGameDto {
    private long id;
    private int size;
    private List<List<Boolean>> cells;

    /**
     * {@link GameDefinition} からインスタンスを生成する。
     * 
     * @param gameDefinition {@link GameDefinition}
     * @return 生成された DTO
     */
    public static LifeGameDto of(GameDefinition gameDefinition) {
        LifeGameDto dto = new LifeGameDto();
        dto.id = gameDefinition.getId();
        dto.size = gameDefinition.getSize();

        Map<Position, CellDefinition> cells = gameDefinition.getCellDefinitions();
        
        dto.cells = NestedLoop.collectList(gameDefinition.getSize(), (i, j) -> {
            return cells.get(new Position(i, j)).isAlive();
        });
        
        return dto;
    }

    public static LifeGameDto of(Game game) {
        LifeGameDto dto = new LifeGameDto();
        dto.id = game.getId();
        dto.size = game.getSize();

        Map<Position, Cell> cells = game.getCells();
        
        dto.cells = NestedLoop.collectList(game.getSize(), (i, j) -> {
            return cells.get(new Position(i, j)).isAlive();
        });
        
        return dto;
    }
    
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
