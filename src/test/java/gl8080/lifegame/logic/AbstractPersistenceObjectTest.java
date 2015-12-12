package gl8080.lifegame.logic;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractPersistenceObjectTest {

    @Test
    public void IDが設定されていないオブジェクト同士は_別物と判定される() {
        // setup
        PersistenceObject o1 = new AbstractEntity();
        PersistenceObject o2 = new AbstractEntity();
        
        // verify
        assertThat(o1.equals(o2), is(false));
    }

    @Test
    public void IDが設定されていないオブジェクト同士は_別々のハッシュ値を返す() {
        // setup
        PersistenceObject o1 = new AbstractEntity();
        PersistenceObject o2 = new AbstractEntity();
        
        // verify
        assertThat(o1.hashCode(), is(not(o2.hashCode())));
    }

    @Test @SuppressWarnings("deprecation")
    public void レシーバにだけIDが設定されている場合_別物と判定される() {
        // setup
        PersistenceObject o1 = new AbstractEntity(10L);
        PersistenceObject o2 = new AbstractEntity();
        
        // verify
        assertThat(o1.equals(o2), is(false));
    }
    
    @Test @SuppressWarnings("deprecation")
    public void 同じIDが設定されている場合_同じものと判定される() {
        // setup
        PersistenceObject o1 = new AbstractEntity(20L);
        PersistenceObject o2 = new AbstractEntity(20L);
        
        // verify
        assertThat(o1.equals(o2), is(true));
    }

    @Test @SuppressWarnings("deprecation")
    public void 同じIDが設定されている場合_同じハッシュ値を返す() {
        // setup
        PersistenceObject o1 = new AbstractEntity(20L);
        PersistenceObject o2 = new AbstractEntity(20L);
        
        // verify
        assertThat(o1.hashCode(), is(o2.hashCode()));
    }

    @Test @SuppressWarnings("deprecation")
    public void nullを渡した場合_別物と判定される() {
        // setup
        PersistenceObject o1 = new AbstractEntity(20L);
        
        // verify
        assertThat(o1.equals(null), is(false));
    }

    @Test @SuppressWarnings("deprecation")
    public void 別クラスのオブジェクトを渡した場合_別物と判定される() {
        // setup
        PersistenceObject o1 = new AbstractEntity(20L);
        
        // verify
        assertThat(o1.equals(new Object()), is(false));
    }

}
