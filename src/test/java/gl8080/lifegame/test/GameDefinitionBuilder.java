package gl8080.lifegame.test;

import java.lang.reflect.Field;

import gl8080.lifegame.logic.Game;
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

    public GameDefinitionBuilder id(long id) {
        this.setIdInto(this.gameDef, id);
        return this;
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
    
    public Game buildAsGame() {
        Game game = new Game(this.gameDef);
        this.setIdInto(game, this.gameDef.getId());
        return game;
    }
    
    private void setIdInto(Object obj, long id) {
        Field field = null;
        try {
            field = obj.getClass().getSuperclass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(obj, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (field != null) {
                field.setAccessible(false);
            }
        }
    }

    public GameDefinitionBuilder version(long version) {
        this.gameDef.setVersion(version);
        return this;
    }
}
