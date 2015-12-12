package gl8080.lifegame.application.definition;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.Position;
import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;
import gl8080.lifegame.web.resource.LifeGameDto;

@Stateless
public class SearchGameDefinitionService {
    @Inject
    private Logger logger;
    @Inject
    private GameDefinitionRepository repository;
    
    public LifeGameDto search(long id) {
        logger.debug("search game definition (id={})", id);
        GameDefinition gameDefinition = this.repository.search(id).orElseThrow(RuntimeException::new);
        
        LifeGameDto dto = new LifeGameDto();
        dto.setId(gameDefinition.getId());
        dto.setSize(gameDefinition.getSize());
        
        List<List<Boolean>> matrix = new ArrayList<>();
        
        for (int i=0; i<dto.getSize(); i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j=0; j<dto.getSize(); j++) {
                row.add(gameDefinition.getCellDefinitions().get(new Position(i, j)).isAlive());
            }
            matrix.add(row);
        }
        dto.setCells(matrix);
        
        return dto;
    }
}
