define(function(require) {
    var Backbone = require('Backbone');
    var GameDefinition = require('GameDefinition');
    var RegisterGameDefinitionForm = require('RegisterGameDefinitionForm');
    
    var LifeGameRouter = Backbone.Router.extend({
        routes: {
            '': 'top',
            'page/game/definition/:id': 'editGameDefinition'
        },
        
        top: function() {
            var gameDefinition = new GameDefinition();
            
            new RegisterGameDefinitionForm({model: gameDefinition});
        },
        
        editGameDefinition: function() {
            
        }
    });
    
    return LifeGameRouter;
});
