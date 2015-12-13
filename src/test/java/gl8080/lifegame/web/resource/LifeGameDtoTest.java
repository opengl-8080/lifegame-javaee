package gl8080.lifegame.web.resource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import gl8080.lifegame.logic.LifeGame;
import gl8080.lifegame.logic.LifeGameCell;
import gl8080.lifegame.logic.Position;
import gl8080.lifegame.test.GameDefinitionBuilder;

@RunWith(HierarchicalContextRunner.class)
public class LifeGameDtoTest {

    private LifeGame lifeGame;
    
    @Before
    public void setup() {
        lifeGame =
                lifeGame(3)
                    .id(10L)
                    .version(59L)
                    .live().live().dead()
                    .dead().live().dead()
                    .dead().dead().live()
                .build();
    }
    
    @Test
    public void idが連携されていること() {
        // exercise
        LifeGameDto dto = LifeGameDto.of(lifeGame);
        
        // verify
        assertThat(dto.getId(), is(10L));
    }
    
    @Test
    public void サイズが連携されていること() throws Exception {
        // exercise
        LifeGameDto dto = LifeGameDto.of(lifeGame);
        
        // verify
        assertThat(dto.getSize(), is(3));
    }
    
    @Test
    public void セルの状態が連携されていること() throws Exception {
        // exercise
        LifeGameDto dto = LifeGameDto.of(lifeGame);
        
        // verify
        Map<Position, ? extends LifeGameCell> originalCells = lifeGame.getCells();
        
        List<List<Boolean>> dtoCells = dto.getCells();
        int i=0;
        int j=0;
        
        for (i=0; i<dtoCells.size(); i++) {
            List<Boolean> row = dtoCells.get(i);
            for (j=0; j<row.size(); j++) {
                LifeGameCell cell = originalCells.get(new Position(i, j));
                assertThat(row.get(j), is(cell.isAlive()));
            }
        }
        
        assertThat(i, greaterThan(0));
        assertThat(j, greaterThan(0));
    }
    
    @Test
    public void バージョンが連携されていること() throws Exception {
        // exercise
        LifeGameDto dto = LifeGameDto.of(lifeGame);
        
        // verify
        assertThat(dto.getVersion(), is(lifeGame.getVersion()));
    }
    
    
    public static GameDefinitionBuilder lifeGame(int size) {
        return new GameDefinitionBuilder(size);
    }
}
