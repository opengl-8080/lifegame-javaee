package gl8080.lifegame.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gl8080.lifegame.logic.exception.IllegalParameterException;

public class Position {
    
    private final int vertical;
    private final int horizontal;

    public Position(int vertical, int horizontal) {
        if (vertical < 0 || horizontal < 0) {
            throw new IllegalParameterException("座標にマイナスは指定できません (" + vertical + ", " + horizontal + ")");
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

    public List<Position> getNeighborPositions() {
        List<Position> neighbors = new ArrayList<>();
        
        for (int v=this.vertical-1; v<this.vertical+2; v++) {
            for (int h=this.horizontal-1; h<this.horizontal+2; h++) {
                if ((0<=v && 0<=h) && !(this.vertical==v && this.horizontal==h)) {
                    neighbors.add(new Position(v, h));
                }
            }
        }
        
        return neighbors;
    }
}
