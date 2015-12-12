package gl8080.lifegame.test;

import gl8080.lifegame.logic.Position;
import gl8080.lifegame.logic.definition.GameDefinition;

public class GameDefinitionBuilder {

    private GameDefinition gameDef;
    private int horizontalIndex;
    private int verticalIndex;
    private int size;
    
    public GameDefinitionBuilder(int size) {
        this.size = size;
        this.gameDef = new GameDefinition(size);
    }
    
    public GameDefinitionBuilder live() {
        return this.cell(true);
    }
    
    public GameDefinitionBuilder dead() {
        return this.cell(false);
    }
    
    private GameDefinitionBuilder cell(boolean alive) {
        this.gameDef.setStatus(new Position(this.verticalIndex, this.horizontalIndex), alive);
        this.horizontalIndex++;
        
        if (this.size <= this.horizontalIndex) {
            this.horizontalIndex = 0;
            this.verticalIndex++;
        }
        
        return this;
    }
    
    public GameDefinition build() {
        return this.gameDef;
    }
}
