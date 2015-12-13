package gl8080.lifegame.web.resource;

import java.util.List;
import java.util.function.BiConsumer;

import gl8080.lifegame.logic.LifeGame;
import gl8080.lifegame.logic.Position;
import gl8080.lifegame.util.NestedLoop;

public class LifeGameDto {
    private long id;
    private int size;
    private List<List<Boolean>> cells;
    private Long version;

    /**
     * {@link LifeGame} からインスタンスを生成する。
     * 
     * @param lifeGame {@link LifeGame}
     * @return 生成された DTO
     */
    public static LifeGameDto of(LifeGame lifeGame) {
        LifeGameDto dto = new LifeGameDto();
        dto.id = lifeGame.getId();
        dto.size = lifeGame.getSize();
        dto.version = lifeGame.getVersion();

        dto.cells = NestedLoop.collectList(lifeGame.getSize(), (i, j) -> {
            return lifeGame.getCells().get(new Position(i, j)).isAlive();
        });
        
        return dto;
    }
    
    /**
     * この DTO が持つ全てのセルに対して、指定した処理を適用します。
     * @param iterator 反復処理
     */
    public void eachCell(BiConsumer<Position, Boolean> iterator) {
        NestedLoop.each(this.cells, (i, j, status) -> {
            iterator.accept(new Position(i, j), status);
        });
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
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LifeGameDto [id=" + id + ", size=" + size + ", cells=" + cells + "]";
    }
}
