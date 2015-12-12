package gl8080.lifegame.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.GameRepository;
import gl8080.lifegame.logic.Position;
import gl8080.lifegame.web.resource.LifeGameDto;

@Stateless
public class SearchGameService {
    @Inject
    private Logger logger;
    @Inject
    private GameRepository repository;
    
    public LifeGameDto search(long id) {
        logger.info("search game (id={})", id);
        
        Game game =
                this.repository
                    .search(id)
                    .orElseThrow(RuntimeException::new);
        
        LifeGameDto dto = new LifeGameDto();
        dto.setId(game.getId());
        dto.setSize(game.getSize());
        
        List<List<Boolean>> matrix = new ArrayList<>();
        
        for (int i=0; i<dto.getSize(); i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j=0; j<dto.getSize(); j++) {
                row.add(game.getCells().get(new Position(i, j)).isAlive());
            }
            matrix.add(row);
        }
        dto.setCells(matrix);
        
        return dto;
    }
}
