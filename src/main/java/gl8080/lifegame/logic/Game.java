package gl8080.lifegame.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ゲームを表すクラス。
 */
public class Game {
    
    private Map<Position, Cell> cells;

    /**
     * 新しいゲームを作成する。
     * 
     * @param gameDef このゲームの元となるゲーム定義
     * @throws NullPointerException ゲーム定義が {@code null} の場合
     */
    public Game(GameDefinition gameDef) {
        this.cells = new HashMap<>();
        
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
}
