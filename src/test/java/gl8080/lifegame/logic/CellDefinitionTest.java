package gl8080.lifegame.logic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellDefinitionTest {
    
    private CellDefinition cellDef;
    
    @Before
    public void setup() {
        cellDef = new CellDefinition();
    }
    
    @Test
    public void デフォルトは_死んでいると判定される() throws Exception {
        // verify
        assertThat(cellDef.isAlive(), is(false));
    }

    @Test
    public void ステータスにtrueを設定したら_生きていると判定されるようになる() {
        // exercise
        cellDef.setStatus(true);
        
        // verify
        assertThat(cellDef.isAlive(), is(true));
    }
    
    @Test
    public void ステータスにfalseを設定したら_死んでいると判定されるようになる() {
        // exercise
        cellDef.setStatus(false);
        
        // verify
        assertThat(cellDef.isAlive(), is(false));
    }
}
