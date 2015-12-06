package gl8080.lifegame.logic;

import javax.persistence.Embeddable;

/**
 * セル定義を表すクラス。
 */
@Embeddable
public class CellDefinition {
    
    private boolean alive;

    /**
     * このセル定義の生死の状態を設定する。
     * @param alive 生存の場合は {@code true} を指定する。
     */
    public void setStatus(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * このセル定義が生きているかどうかを確認する。
     * @return 生きている場合は {@code true}
     */
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public String toString() {
        return "CellDefinition [alive=" + alive + "]";
    }
}
