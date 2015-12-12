package gl8080.lifegame.web.resource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import gl8080.lifegame.logic.Cell;
import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.Position;
import gl8080.lifegame.logic.definition.CellDefinition;
import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.test.GameDefinitionBuilder;

@RunWith(HierarchicalContextRunner.class)
public class LifeGameDtoTest {

    public class GameDefinitionの変換 {

        private GameDefinition gameDefinition;
        
        @Before
        public void setup() {
            gameDefinition =
                    gameDefinition(3)
                        .id(10L)
                        .live().live().dead()
                        .dead().live().dead()
                        .dead().dead().live()
                    .build();
        }
        
        @Test
        public void idが連携されていること() {
            // exercise
            LifeGameDto dto = LifeGameDto.of(gameDefinition);
            
            // verify
            assertThat(dto.getId(), is(10L));
        }
        
        @Test
        public void サイズが連携されていること() throws Exception {
            // exercise
            LifeGameDto dto = LifeGameDto.of(gameDefinition);
            
            // verify
            assertThat(dto.getSize(), is(3));
        }
        
        @Test
        public void セルの状態が連携されていること() throws Exception {
            // exercise
            LifeGameDto dto = LifeGameDto.of(gameDefinition);
            
            // verify
            Map<Position, CellDefinition> originalCells = gameDefinition.getCellDefinitions();
            
            List<List<Boolean>> dtoCells = dto.getCells();
            int i=0;
            int j=0;
            
            for (i=0; i<dtoCells.size(); i++) {
                List<Boolean> row = dtoCells.get(i);
                for (j=0; j<row.size(); j++) {
                    CellDefinition cell = originalCells.get(new Position(i, j));
                    assertThat(row.get(j), is(cell.isAlive()));
                }
            }
            
            assertThat(i, greaterThan(0));
            assertThat(j, greaterThan(0));
        }
    }

    public class Gameの変換 {

        private Game game;
        
        @Before
        public void setup() {
            game = gameDefinition(3)
                        .id(10L)
                        .live().live().dead()
                        .dead().live().dead()
                        .dead().dead().live()
                    .buildAsGame();
        }
        
        @Test
        public void idが連携されていること() {
            // exercise
            LifeGameDto dto = LifeGameDto.of(game);
            
            // verify
            assertThat(dto.getId(), is(10L));
        }
        
        @Test
        public void サイズが連携されていること() throws Exception {
            // exercise
            LifeGameDto dto = LifeGameDto.of(game);
            
            // verify
            assertThat(dto.getSize(), is(3));
        }
        
        @Test
        public void セルの状態が連携されていること() throws Exception {
            // exercise
            LifeGameDto dto = LifeGameDto.of(game);
            
            // verify
            Map<Position, Cell> originalCells = game.getCells();
            
            List<List<Boolean>> dtoCells = dto.getCells();
            int i=0;
            int j=0;
            
            for (i=0; i<dtoCells.size(); i++) {
                List<Boolean> row = dtoCells.get(i);
                for (j=0; j<row.size(); j++) {
                    Cell cell = originalCells.get(new Position(i, j));
                    assertThat(row.get(j), is(cell.isAlive()));
                }
            }
            
            assertThat(i, greaterThan(0));
            assertThat(j, greaterThan(0));
        }
    }
    
    public static GameDefinitionBuilder gameDefinition(int size) {
        return new GameDefinitionBuilder(size);
    }
}
