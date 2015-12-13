package gl8080.lifegame.logic;

import static javax.persistence.CascadeType.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gl8080.lifegame.logic.definition.GameDefinition;

/**
 * ゲームを表すクラス。
 */
@Entity
@Table(name="GAME")
public class Game extends AbstractEntity implements LifeGame {
    private static final long serialVersionUID = 1L;

    private int size;
    
    @OneToMany(cascade={PERSIST, MERGE, REMOVE})
    @JoinColumn(name="GAME_ID")
    private Map<Position, Cell> cells;

    /**
     * 新しいゲームを作成する。
     * 
     * @param gameDef このゲームの元となるゲーム定義
     * @throws NullPointerException ゲーム定義が {@code null} の場合
     */
    public Game(GameDefinition gameDef) {
        Objects.requireNonNull(gameDef, "ゲーム定義が null です。");
        this.cells = new HashMap<>();
        this.size = gameDef.getSize();
        
        this.initializeCells(gameDef);
        this.setupNeighborCells();
    }

    private void initializeCells(GameDefinition gameDef) {
        gameDef.getCells().forEach((position, cellDef) -> {
            this.cells.put(position, cellDef.isAlive() ? Cell.alive() : Cell.dead());
        });
    }

    private void setupNeighborCells() {
        this.cells.forEach((position, cell) -> {
            List<Cell> neighbors =
                position
                    .getNeighborPositions().stream()
                    .filter(this.cells::containsKey)
                    .map(this.cells::get)
                    .collect(Collectors.toList());
            
            cell.setNeighbors(neighbors);
        });
    }

    @Override
    public Map<Position, Cell> getCells() {
        return new HashMap<>(this.cells);
    }

    /**
     * このゲームを１ステップ進める。
     */
    public void nextStep() {
        this.cells.values().forEach(Cell::reserveNextStatus);
        this.cells.values().forEach(Cell::stepNextStatus);
    }
    
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "Game [id=" + this.getId() + ", size=" + size + ", cells=" + cells + "]";
    }

    /**
     * @deprecated このコンストラクタはフレームワークから使用されることを想定しています。
     */
    @Deprecated @SuppressWarnings("unused")
    private Game() {}
}
