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
//        Map<Position, Cell> map = new HashMap<>();
//        
//        Cell corner = Cell.dead();
//        Cell a = Cell.alive();
//        Cell b = Cell.alive();
//        Cell c = Cell.dead();
//        corner.setNeighbors(a, b, c);
//        
//        map.put(new Position(0, 0), corner);
//        map.put(new Position(0, 1), a);
//        map.put(new Position(0, 2), Cell.dead());
//        
//        map.put(new Position(1, 0), b);
//        map.put(new Position(1, 1), c);
//        map.put(new Position(1, 2), Cell.alive());
//        
//        map.put(new Position(2, 0), Cell.alive());
//        map.put(new Position(2, 1), Cell.dead());
//        map.put(new Position(2, 2), Cell.alive());
        
        return this.cells;
    }

    public void nextStep() {
        this.cells.values().forEach(Cell::reserveNextStatus);
        this.cells.values().forEach(Cell::stepNextStatus);
    }

}
