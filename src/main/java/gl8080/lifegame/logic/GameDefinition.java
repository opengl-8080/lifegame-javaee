package gl8080.lifegame.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static java.util.stream.IntStream.*;

import gl8080.lifegame.logic.exception.IllegalParameterException;

public class GameDefinition {

    public static final int MAX_SIZE = 100;
    
    private final int size;
    private Map<Position, CellDefinition> cells;
    
    public GameDefinition(int size) {
        if (size < 1) {
            throw new IllegalParameterException("サイズに０以外の値は指定できません size =" + size);
        } else if (MAX_SIZE < size) {
            throw new IllegalParameterException("サイズに " + MAX_SIZE + " 以上の値は指定できません size =" + size);
        }
        
        this.size = size;
        this.cells = new HashMap<>();
        
        range(0, size).forEach(i -> {
            range(0, size).forEach(j -> {
                this.cells.put(new Position(i, j), new CellDefinition());
            });
        });
    }

    public Map<Position, CellDefinition> getCellDefinitions() {
        return new HashMap<>(this.cells);
    }

    public void setStatus(Position position, boolean status) {
        Objects.requireNonNull(position, "位置が null です");
        
        if (!this.cells.containsKey(position)) {
            throw new IllegalParameterException("位置が範囲外です (size = " + this.size + ", position = " + position + ")");
        }
        
        this.cells.get(position).setStatus(status);
    }
}
