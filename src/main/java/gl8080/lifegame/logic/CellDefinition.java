package gl8080.lifegame.logic;

public class CellDefinition {

    private boolean alive;

    public void setStatus(boolean alive) {
        this.alive = alive;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
}
