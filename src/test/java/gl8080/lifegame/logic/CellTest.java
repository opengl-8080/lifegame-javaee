package gl8080.lifegame.logic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CellTest {

    @Test
    public void 死んだセルを生成できる() {
        // setup
        Cell deadCell = Cell.dead();
        
        // verify
        assertThat(deadCell.isAlive(), is(false));
    }
    
    @Test
    public void 生きたセルを生成できる() {
        // setup
        Cell liveCell = Cell.alive();
        
        // verify
        assertThat(liveCell.isAlive(), is(true));
    }
    
    private Cell liveCell;
    
    @Before
    public void setup() {
        liveCell = Cell.alive();
    }

    @Test
    public void 生きたセルが周りに４つ存在する場合_次の状態は_死_で予約される() throws Exception {
        // setup
        liveCell.setNeighbors(alive(4).dead(4));
        
        // exercise
        liveCell.reserveNextStatus();
        
        // verify
        assertThat(liveCell.toBeAlive(), is(false));
    }
    
    @Test
    public void 生きたセルが周りに３つ存在する場合_次の状態は_生_で予約される() throws Exception {
        // setup
        liveCell.setNeighbors(alive(3).dead(5));
        
        // exercise
        liveCell.reserveNextStatus();
        
        // verify
        assertThat(liveCell.toBeAlive(), is(true));
    }
    
    @Test
    public void 生きたセルが周りに２つ存在する場合_次の状態は_生_で予約される() throws Exception {
        // setup
        liveCell.setNeighbors(alive(2).dead(6));
        
        // exercise
        liveCell.reserveNextStatus();
        
        // verify
        assertThat(liveCell.toBeAlive(), is(true));
    }
    
    @Test
    public void 生きたセルが周りに１つ存在する場合_次の状態は_死_で予約される() throws Exception {
        // setup
        liveCell.setNeighbors(alive(1).dead(7));
        
        // exercise
        liveCell.reserveNextStatus();
        
        // verify
        assertThat(liveCell.toBeAlive(), is(false));
    }
    
    @Test
    public void 生きたセルが周りに存在しない場合_次の状態は_死_で予約される() throws Exception {
        // exercise
        liveCell.reserveNextStatus();
        
        // verify
        assertThat(liveCell.toBeAlive(), is(false));
    }
    
    @Test
    public void 予約した段階では_まだセルの実際の状態は変わっていない() throws Exception {
        // setup
        liveCell.setNeighbors(alive(1).dead(7));
        liveCell.reserveNextStatus();
        
        // verify
        assertThat(liveCell.isAlive(), is(true));
    }
    
    @Test
    public void 予約した状態で_遷移を実行すると_セルの状態が実際に変更される() throws Exception {
        // setup
        liveCell.setNeighbors(alive(1).dead(7));
        liveCell.reserveNextStatus();
        
        // exercise
        liveCell.stepNextStatus();
        
        // verify
        assertThat(liveCell.isAlive(), is(false));
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void 予約していないのに遷移を実行しようとすると_例外がスローされること() throws Exception {
        // verify
        exception.expect(IllegalStateException.class);
        
        // exercise
        liveCell.stepNextStatus();
    }
    
    @Test
    public void 一度遷移した後で_予約せずに再度遷移を実行しようとすると_例外がスローされること() throws Exception {
        // setup
        liveCell.reserveNextStatus();
        liveCell.stepNextStatus();
        
        // verify
        exception.expect(IllegalStateException.class);
        
        // exercise
        liveCell.stepNextStatus();
    }
    
    @Test
    public void 予約していない状態で次の状態を参照しようとしたら_例外がスローされること() throws Exception {
        // verify
        exception.expect(IllegalStateException.class);
        
        // exercise
        liveCell.toBeAlive();
    }
    
    @Test
    public void 隣のセルにnullを渡した場合_例外がスローされること() throws Exception {
        // verify
        exception.expect(NullPointerException.class);
        
        // exercise
        liveCell.setNeighbors(nullObject());
    }
    
    private static Cell[] nullObject() {
        // 可変長引数に直接 null を渡すと警告がでるので、メソッドを経由させている
        return null;
    }
    
    private static CellBuilder alive(int n) {
        return new CellBuilder().alive(n);
    }
    
    private static class CellBuilder {
        
        private int aliveCount;
        private int deadCount;
        
        private CellBuilder alive(int n) {
            this.aliveCount = n;
            return this;
        }
        
        private Cell[] dead(int n) {
            this.deadCount = n;
            
            int length = this.aliveCount + this.deadCount;
            Cell[] cells = new Cell[length];
            
            for (int i=0; i<length; i++) {
                if (i < this.aliveCount) {
                    cells[i] = Cell.alive();
                } else {
                    cells[i] = Cell.dead();
                }
            }
            
            return cells;
        }
    }
}
