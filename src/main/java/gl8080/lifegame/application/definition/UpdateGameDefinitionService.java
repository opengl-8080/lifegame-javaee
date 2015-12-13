package gl8080.lifegame.application.definition;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;
import gl8080.lifegame.web.resource.LifeGameDto;

@Stateless
public class UpdateGameDefinitionService {
    @Inject
    private transient Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    
    public GameDefinition update(LifeGameDto dto) {
        logger.info("update game definition (id={})", dto.getId());
        logger.debug("dto = {}", dto);
        
        GameDefinition gameDefinition = this.gameDefinitionRepository.searchWithLock(dto.getId());

        dto.eachCell(gameDefinition::setStatus);
        gameDefinition.setVersion(dto.getVersion());
        
        return gameDefinition;
    }
}
