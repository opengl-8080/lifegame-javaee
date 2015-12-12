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

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

/**
 * ゲームを表すクラス。
 */
@Entity
@Table(name="GAME")
public class Game extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    private int size;
    
    @OneToMany(cascade={PERSIST, MERGE, REMOVE})
    @JoinColumn(name="GAME_ID")
    @JoinFetch(JoinFetchType.INNER)
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
        gameDef.getCellDefinitions().forEach((position, cellDef) -> {
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

    /**
     * このゲームが持つ全てのセルを取得する。
     * <p>
     * このメソッドが返すマップは、このオブジェクトが持つマップのコピーです。<br>
     * ここで取得したマップのエントリを削除するなどしても、このオブジェクトが持つオリジナルのマップには影響を与えません。
     * 
     * @return このゲームが持つ全てのセル
     */
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
    
    /**
     * このゲームのサイズを取得します。
     * @return このゲームのサイズ
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "Game [id=" + this.getId() + ", size=" + size + ", cells=" + cells + "]";
    }
    
    public String dump() {
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<this.size; i++) {
            for (int j=0; j<this.size; j++) {
                Position p = new Position(i, j);
                Cell cell = this.cells.get(p);
                
                sb.append(cell.isAlive() ? "+" : "-");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    /**
     * @deprecated このコンストラクタはフレームワークから使用されることを想定しています。
     */
    @Deprecated @SuppressWarnings("unused")
    private Game() {}
}
