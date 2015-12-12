package gl8080.lifegame.logic;

import java.util.Map;

public interface LifeGame {
    
    /**
     * このゲームの ID を取得します。
     * @return ID
     */
    Long getId();
    
    /**
     * このゲームのサイズを取得します。
     * @return サイズ
     */
    int getSize();
    
    /**
     * このゲームが持つ全てのセルを取得します。
     * <p>
     * このメソッドが返すマップは、このオブジェクトが持つマップのコピーです。<br>
     * ここで取得したマップのエントリを削除するなどしても、このオブジェクトが持つオリジナルのマップには影響を与えません。
     * 
     * @return 全てのセル
     */
    Map<Position, ? extends LifeGameCell> getCells();
    
    /**
     * このゲームの状態を簡単な文字列形式で取得します。
     * <p>
     * {@code "+"} は生きたセルを、 {@code "-"} は死んだセルを表しています。
     * 
     * @return ゲームの状態を表す文字列
     */
    default String dump() {
        StringBuilder sb = new StringBuilder();
        int size = this.getSize();
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                Position p = new Position(i, j);
                LifeGameCell cell = this.getCells().get(p);
                
                sb.append(cell.isAlive() ? "+" : "-");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
