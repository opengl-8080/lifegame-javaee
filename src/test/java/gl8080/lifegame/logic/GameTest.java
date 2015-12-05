package gl8080.lifegame.logic;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {
    
    private GameDefinition gameDef;
    private Game game;
    
    @Before
    public void setup() {
        gameDef = 
            gameDefinition(3)
                .dead().live().dead()
                .live().dead().live()
                .live().dead().live()
            .build();
        
        game = new Game(gameDef);
    }
    
    @Test
    public void ライフゲームのルールに従って次のステップに遷移できる() {
        // exercise
        game.nextStep();
        
        // verify
        gameDefinition(3)
            .dead().live().dead()
            .live().dead().live()
            .dead().dead().dead()
        .build()
        .getCellDefinitions()
        .forEach((p, c) -> {
            
            Cell cell = game.getCells().get(p);
            assertThat(p.toString(), cell.isAlive(), is(c.isAlive()));
        });
    }
    
    @Test
    public void 指定したセル定義と同じ位置_同じ状態でセルが生成されること() {
        // verify
        gameDef
            .getCellDefinitions()
            .forEach((position, cellDef) -> {
                Cell cell = game.getCells().get(position);
                
                assertThat(position.toString(), cellDef.isAlive(), is(cell.isAlive()));
            });
    }
    
    @Test
    public void 角のセルには_周囲３つのセルが設定されていること() {
        // verify
        Map<Position, Cell> cells = game.getCells();
        
        Cell corner = cells.get(new Position(0, 0));
        
        List<Cell> neighbors = corner.getNeighbors();
        
        assertThat(neighbors, containsInAnyOrder(
                                cells.get(new Position(0, 1)),
                                cells.get(new Position(1, 0)),
                                cells.get(new Position(1, 1))
                              ));
    }
    
    @Test
    public void 右真ん中のセルには_周囲５つのセルが設定されていること() {
        // verify
        Map<Position, Cell> cells = game.getCells();
        
        Cell corner = cells.get(new Position(1, 2));
        
        List<Cell> neighbors = corner.getNeighbors();
        
        assertThat(neighbors, containsInAnyOrder(
                                cells.get(new Position(0, 1)),
                                cells.get(new Position(0, 2)),
                                cells.get(new Position(1, 1)),
                                cells.get(new Position(2, 1)),
                                cells.get(new Position(2, 2))
                              ));
    }
    
    @Test
    public void 真ん中のセルには_周囲８つのセルが設定されていること() {
        // verify
        Map<Position, Cell> cells = game.getCells();
        
        Cell corner = cells.get(new Position(1, 1));
        
        List<Cell> neighbors = corner.getNeighbors();
        
        assertThat(neighbors, containsInAnyOrder(
                                cells.get(new Position(0, 0)),
                                cells.get(new Position(0, 1)),
                                cells.get(new Position(0, 2)),
                                cells.get(new Position(1, 0)),
                                cells.get(new Position(1, 2)),
                                cells.get(new Position(2, 0)),
                                cells.get(new Position(2, 1)),
                                cells.get(new Position(2, 2))
                              ));
    }
    
    public static GameDefinitionBuilder gameDefinition(int size) {
        return new GameDefinitionBuilder(size);
    }
    
    public static class GameDefinitionBuilder {
        
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
}
