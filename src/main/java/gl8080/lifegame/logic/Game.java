package gl8080.lifegame.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    
    private Map<Position, Cell> cells;

    public Game(GameDefinition gameDef) {
        this.cells = new HashMap<>();
        
        gameDef.getCellDefinitions().forEach((position, cellDef) -> {
            this.cells.put(position, cellDef.isAlive() ? Cell.alive() : Cell.dead());
        });
        
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

    public Map<Position, Cell> getCells() {
        return new HashMap<>(this.cells);
    }

    public void nextStep() {
        this.cells.values().forEach(Cell::reserveNextStatus);
        this.cells.values().forEach(Cell::stepNextStatus);
    }

}
