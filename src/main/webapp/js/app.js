$(function() {
    var GameDefinition = lg.model.GameDefinition;
    var LifeGameBoard = lg.view.LifeGameBoard;
    
    var gameDefinition = new GameDefinition();
    var lifeGameBoard = new LifeGameBoard({model: gameDefinition});
    
    $('#registerDefinitionButton').on('click', function() {
        gameDefinition.register();
    });
    
    $('#searchDefinitionButton').on('click', function() {
        gameDefinition.search();
    });
});