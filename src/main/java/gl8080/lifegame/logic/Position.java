package gl8080.lifegame.logic;

import java.util.Objects;

public class Position {
    
    private final int vertical;
    private final int horizontal;

    public Position(int vertical, int horizontal) {
        if (vertical < 0 || horizontal < 0) {
            throw new IllegalArgumentException("座標にマイナスは指定できません (" + vertical + ", " + horizontal + ")");
        }
        
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Position)) return false;
        
        Position other = (Position) o;
        return this.vertical == other.vertical && this.horizontal == other.horizontal;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.vertical, this.horizontal);
    }
    
    @Override
    public String toString() {
        return Position.class.getSimpleName() + " (" + this.vertical + ", " + this.horizontal + ")";
    }
}
