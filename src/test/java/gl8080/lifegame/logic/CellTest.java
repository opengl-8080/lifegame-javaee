package gl8080.lifegame.logic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class CellTest {

    public class 基本的なメソッドのテスト {
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
        
        @Rule
        public ExpectedException exception = ExpectedException.none();
        
        @Test
        public void 隣のセルにnullを渡した場合_例外がスローされること() throws Exception {
            // setup
            Cell liveCell = Cell.alive();
            
            // verify
            exception.expect(NullPointerException.class);
            
            // exercise
            liveCell.setNeighbors(null);
        }
    }
    
    public class セルが死んでいる場合のルールのテスト {

        private Cell deadCell;
        
        @Before
        public void setup() {
            deadCell = Cell.dead();
        }
        
        @Test
        public void 生きたセルが周りに４つ存在する場合_次の状態は_死_で予約される() throws Exception {
            assertRule(deadCell, alive(4).dead(4), false);
        }
        
        @Test
        public void 生きたセルが周りに３つ存在する場合_次の状態は_生_で予約される() throws Exception {
            assertRule(deadCell, alive(3).dead(5), true);
        }
        
        @Test
        public void 生きたセルが周りに２つ存在する場合_次の状態は_死_で予約される() throws Exception {
            assertRule(deadCell, alive(2).dead(6), false);
        }
        
        @Test
        public void 生きたセルが周りに１つ存在する場合_次の状態は_死_で予約される() throws Exception {
            assertRule(deadCell, alive(1).dead(7), false);
        }
        
        @Test
        public void 生きたセルが周りに存在しない場合_次の状態は_死_で予約される() throws Exception {
            assertRule(deadCell, Collections.emptyList(), false);
        }
    }
    
    public class セルが生きている場合のルールのテスト {

        private Cell liveCell;
        
        @Before
        public void setup() {
            liveCell = Cell.alive();
        }
        
        @Test
        public void 生きたセルが周りに４つ存在する場合_次の状態は_死_で予約される() throws Exception {
            assertRule(liveCell, alive(4).dead(4), false);
        }
        
        @Test
        public void 生きたセルが周りに３つ存在する場合_次の状態は_生_で予約される() throws Exception {
            assertRule(liveCell, alive(3).dead(5), true);
        }
        
        @Test
        public void 生きたセルの周りに_生きたセルが周りに２つ存在する場合_次の状態は_生_で予約される() throws Exception {
            assertRule(liveCell, alive(2).dead(6), true);
        }
        
        @Test
        public void 生きたセルが周りに１つ存在する場合_次の状態は_死_で予約される() throws Exception {
            assertRule(liveCell, alive(1).dead(7), false);
        }
        
        @Test
        public void 生きたセルが周りに存在しない場合_次の状態は_死_で予約される() throws Exception {
            assertRule(liveCell, Collections.emptyList(), false);
        }
    }
    
    public static void assertRule(Cell cell, List<Cell> neighbors, boolean toBeStatus) {
        // setup
        cell.setNeighbors(neighbors);
        
        // exercise
        cell.reserveNextStatus();
        
        // verify
        assertThat(cell.toBeAlive(), is(toBeStatus));
    }
    
    public class 予約に関するテスト {
        
        private Cell liveCell;
        
        @Before
        public void setup() {
            liveCell = Cell.alive();
            liveCell.setNeighbors(alive(1).dead(7));
        }
        
        public class 予約した場合 {

            @Before
            public void setup() {
                liveCell.reserveNextStatus();
            }
            
            @Test
            public void まだセルの実際の状態は変わっていない() throws Exception {
                // verify
                assertThat(liveCell.isAlive(), is(true));
            }
            
            public class 遷移を実行した後の場合 {
                
                @Rule
                public ExpectedException exception = ExpectedException.none();
                
                @Before
                public void setup() {
                    liveCell.stepNextStatus();
                }
                
                @Test
                public void セルの状態が実際に変更される() throws Exception {
                    // verify
                    assertThat(liveCell.isAlive(), is(false));
                }
                
                @Test
                public void 予約せずに再度遷移を実行しようとすると_例外がスローされること() throws Exception {
                    // verify
                    exception.expect(IllegalStateException.class);
                    
                    // exercise
                    liveCell.stepNextStatus();
                }

                @Test
                public void 予約せずに再度次の状態を参照しようとしたら_例外がスローされること() throws Exception {
                    // verify
                    exception.expect(IllegalStateException.class);
                    
                    // exercise
                    liveCell.toBeAlive();
                }
            }
        }
        
        public class まだ予約していない場合 {

            @Rule
            public ExpectedException exception = ExpectedException.none();
            
            @Test
            public void 遷移を実行しようとすると_例外がスローされること() throws Exception {
                // verify
                exception.expect(IllegalStateException.class);
                
                // exercise
                liveCell.stepNextStatus();
            }

            @Test
            public void 次の状態を参照しようとしたら_例外がスローされること() throws Exception {
                // verify
                exception.expect(IllegalStateException.class);
                
                // exercise
                liveCell.toBeAlive();
            }
        }
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
        
        private List<Cell> dead(int n) {
            this.deadCount = n;
            
            int length = this.aliveCount + this.deadCount;
            List<Cell> cells = new ArrayList<>();
            
            for (int i=0; i<length; i++) {
                if (i < this.aliveCount) {
                    cells.add(Cell.alive());
                } else {
                    cells.add(Cell.dead());
                }
            }
            
            return cells;
        }
    }
}
