package gl8080.lifegame.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import gl8080.lifegame.logic.GameDefinitionRepository;
import gl8080.lifegame.web.resource.LifeGameDto;

@Stateless
public class SaveGameDefinitionService {
    @Inject
    private Logger logger;
    
    @Inject
    private GameDefinitionRepository gameDefinitionRepository;
    
    public void save(LifeGameDto dto) {
        logger.info("save game definition (id={})", dto.getId());
        logger.debug("dto = {}", dto);
        
        this.gameDefinitionRepository
            .search(dto.getId())
            .ifPresent(gameDefinition -> {
                dto.eachCell(gameDefinition::setStatus);
            });
    }
}
