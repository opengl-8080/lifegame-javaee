package gl8080.lifegame.application;

import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameDefinition;
import gl8080.lifegame.logic.GameDefinitionRepository;
import gl8080.lifegame.logic.GameRepository;
import gl8080.lifegame.logic.Position;

@Stateless
public class SampleService {
    @Inject
    private Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    @Inject
    private GameRepository gameRepository;
    
    public void registerDef(int size) {
        GameDefinition gameDef = new GameDefinition(size);
        gameDef.setStatus(new Position(0, 0), true);
        this.gameDefinitionRepository.register(gameDef);
    }
    
    public void searchDef(long id) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.gameDefinitionRepository.search(id).getCellDefinitions().forEach((p, c) -> {
            System.out.println("  {" + p + " -> " + c.isAlive() + "}");
        });
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
    
    public void registerGame(int gameDefId) {
        GameDefinition gameDef = this.gameDefinitionRepository.search(gameDefId);
        Game game = new Game(gameDef);
        this.gameRepository.register(game);
    }
    
    public void searchGame(long id) {
        StringBuffer sb = new StringBuffer("======================================>\r\n");
        this.gameRepository
            .search(id)
            .getCells()
            .forEach((p, c) -> {
                sb.append("{" + p + " -> " + c.isAlive() + "}\r\n")
                  .append("  neighbors > [" + c.getNeighbors().stream().map(m -> "" + m.isAlive()).collect(Collectors.joining(", ")) + "]\r\n");
            });
        sb.append("<======================================\r\n");
        logger.info(sb.toString());
    }
}
