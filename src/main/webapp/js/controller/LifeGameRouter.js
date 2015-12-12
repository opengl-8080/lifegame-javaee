define(function(require) {
    var $ = require('jquery');
    var Backbone = require('Backbone');
    var _ = require('underscore');
    var Game = require('Game');
    var GameDefinition = require('GameDefinition');
    var RegisterGameDefinitionForm = require('RegisterGameDefinitionForm');
    var EditGameDefinitionForm = require('EditGameDefinitionForm');
    var RunGameForm = require('RunGameForm');
    
    var LifeGameRouter = Backbone.Router.extend({
        routes: {
            '': 'registerGameDefinition',
            'page/game/definition/:id': 'editGameDefinition',
            'page/game/:id': 'runGame'
        },
        
        initialize: function() {
            this.on('route', function(route, param) {
                $('.page').hide();
                $('.' + route).fadeIn(200);
            });
        },
        
        registerGameDefinition: function() {
            var self = this;
            var gameDefinition = new GameDefinition();
            
            var form = new RegisterGameDefinitionForm({model: gameDefinition});
            
            form.on('register-game-definition', function(param) {
                self.navigate('page/game/definition/' + param.id, true);
            });
        },
        
        editGameDefinition: function(id) {
            var gameDefinition = new GameDefinition({id: id});
            var form = new EditGameDefinitionForm({model: gameDefinition});
            var self = this;
            
            form.on('start-game', function(game) {
                self.navigate('page/game/' + game.id, true);
            });
        },
        
        runGame: function(id) {
            var game = new Game({id: id});
            var form = new RunGameForm({model: game});
        }
    });
    
    return LifeGameRouter;
});
