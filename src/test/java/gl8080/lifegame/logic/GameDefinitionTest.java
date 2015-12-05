package gl8080.lifegame.logic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import gl8080.lifegame.logic.exception.IllegalParameterException;

public class GameDefinitionTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private int size = 5;
    private GameDefinition gameDef;
    
    @Before
    public void setup() {
        gameDef = new GameDefinition(size);
    }
    
    @Test
    public void サイズに負数を指定したら例外がスローされること() throws Exception {
        // verify
        exception.expect(IllegalParameterException.class);
        
        // exercise
        new GameDefinition(-1);
    }
    
    @Test
    public void サイズに０を指定したら例外がスローされること() throws Exception {
        // verify
        exception.expect(IllegalParameterException.class);
        
        // exercise
        new GameDefinition(0);
    }
    
    @Test
    public void インスタンスを生成すると_指定したサイズを二乗した数の死んだセル定義が作成される() {
        // verify
        Map<Position, CellDefinition> matrix = gameDef.getCellDefinitions();
        
        Set<CellDefinition> uniqued = new HashSet<>(matrix.values());
        
        assertThat(uniqued.size(), is(size * size));
        assertThat(uniqued.stream().anyMatch(CellDefinition::isAlive), is(false));
    }
    
    @Test
    public void 位置を指定して_セル定義の状態を変更できる() {
        // setup
        Position position = new Position(2, 3);
        CellDefinition targetCell = gameDef.getCellDefinitions().get(position);

        assertThat(targetCell.isAlive(), is(false));
        
        // exercise
        gameDef.setStatus(position, true);
        
        // verify
        assertThat(targetCell.isAlive(), is(true));
    }
    
    @Test
    public void 指定した位置がnullの場合_例外がスローされる() {
        // verify
        exception.expect(NullPointerException.class);
        
        // exercise
        gameDef.setStatus(null, true);
    }
    
    @Test
    public void 座標が_サイズで指定した範囲より外の場合_例外がスローされる() {
        // verify
        exception.expect(IllegalParameterException.class);
        
        // exercise
        gameDef.setStatus(new Position(size, 0), true);
    }
    
    @Test
    public void 取得したセル定義一覧を変更しても_ゲーム定義が持っているセル定義には影響を与えない() {
        // setup
        Position position = new Position(1, 2);
        
        // exercise
        gameDef.getCellDefinitions().remove(position);
        
        // verify
        assertThat(gameDef.getCellDefinitions().containsKey(position), is(true));
    }

}
