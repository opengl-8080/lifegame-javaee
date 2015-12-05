package gl8080.lifegame.logic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        exception.expect(IllegalArgumentException.class);
        
        // exercise
        new Position(-1, 1);
    }
    
    @Test
    public void 横座標に負数を渡した場合_例外がスローされること() throws Exception {
        // setup
        exception.expect(IllegalArgumentException.class);
        
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
}
