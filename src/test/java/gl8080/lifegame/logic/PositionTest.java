package gl8080.lifegame.logic;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import gl8080.lifegame.logic.exception.IllegalParameterException;

@RunWith(HierarchicalContextRunner.class)
public class PositionTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void 同じ縦横座標を持つインスタンス同士は_equalsメソッドで同じモノと判定される() throws Exception {
        // setup
        Position p1 = new Position(10, 14);
        Position p2 = new Position(10, 14);
        
        // exercise
        boolean actual = p1.equals(p2);
        
        // verify
        assertThat(actual, is(true));
    }
    
    @Test
    public void 異なる縦横座標を持つインスタンス同士は_equalsメソッドで別物と判定される() throws Exception {
        // setup
        Position p1 = new Position(10, 14);
        Position p2 = new Position(10, 15);
        
        // exercise
        boolean actual = p1.equals(p2);
        
        // verify
        assertThat(actual, is(false));
    }
    
    @Test
    public void 同じ縦横座標を持つインスタンス同士は_hashCodeメソッドは同じ値を返す() throws Exception {
        // setup
        Position p1 = new Position(20, 0);
        Position p2 = new Position(20, 0);
        
        // verify
        assertThat(p1.hashCode(), is(p2.hashCode()));
    }
    
    @Test
    public void 異なる縦横座標を持つインスタンス同士は_hashCodeメソッドは異なる値を返す() throws Exception {
        // setup
        Position p1 = new Position(20, 0);
        Position p2 = new Position(20, 1);
        
        // verify
        assertThat(p1.hashCode(), is(not(p2.hashCode())));
    }
    
    @Test
    public void 縦と横の座標が入れ替わったインスタンス同士は_hashCodeメソッドが異なる値を返す() throws Exception {
        // setup
        Position p1 = new Position(12, 30);
        Position p2 = new Position(30, 12);
        
        // verify
        assertThat(p1.hashCode(), is(not(p2.hashCode())));
    }
    
    @Test
    public void equalsメソッドにnullを渡した場合_falseが返されること() throws Exception {
        // setup
        Position p1 = new Position(11, 14);
        
        // exercise
        boolean actual = p1.equals(null);
        
        // verify
        assertThat(actual, is(false));
    }
    
    @Test
    public void equalsメソッドに別のクラスのインスタンスを渡した場合_falseが返されること() throws Exception {
        // setup
        Position p1 = new Position(11, 14);
        
        // exercise
        boolean actual = p1.equals(new Object());
        
        // verify
        assertThat(actual, is(false));
    }
    
    @Test
    public void 縦座標に負数を渡した場合_例外がスローされること() throws Exception {
        // setup
        exception.expect(IllegalParameterException.class);
        
        // exercise
        new Position(-1, 1);
    }
    
    @Test
    public void 横座標に負数を渡した場合_例外がスローされること() throws Exception {
        // setup
        exception.expect(IllegalParameterException.class);
        
        // exercise
        new Position(1, -1);
    }
    
    @Test
    public void toStringすると_座標が文字列形式で返される() throws Exception {
        // setup
        Position position = new Position(5, 2);
        
        // exercise
        String actual = position.toString();
        
        // verify
        assertThat(actual, is("Position (5, 2)"));
    }
    
    public class 周囲の位置を取得するメソッドのテスト {
        
        @Test
        public void マイナスを指す位置は取得されないこと() throws Exception {
            // setup
            Position position = new Position(0, 0);
            
            // exercise
            List<Position> neighbors = position.getNeighborPositions();
            
            // verify
            assertThat(neighbors, containsInAnyOrder(new Position(0, 1), new Position(1, 0), new Position(1, 1)));
        }
        
        @Test
        public void 周囲８つの位置を取得できる() throws Exception {
            // setup
            Position position = new Position(5, 3);
            
            // exercise
            List<Position> neighbors = position.getNeighborPositions();
            
            // verify
            assertThat(neighbors, containsInAnyOrder(
                    new Position(4, 2), new Position(4, 3), new Position(4, 4),
                    new Position(5, 2),                     new Position(5, 4),
                    new Position(6, 2), new Position(6, 3), new Position(6, 4)
                    ));
        }
    }
}
