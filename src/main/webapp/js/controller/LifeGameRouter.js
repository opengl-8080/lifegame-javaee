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
            'page/game/:id': 'runGame',
            '*notFound': 'notFound'
        },
        
        initialize: function() {
            this.runGameForms = [];
            $(window).on("beforeunload", this.removeRunningGameIfExists.bind(this));
            this.on('route', this.onMovePage.bind(this));
        },
        
        onMovePage: function(route) {
            this.removeRunningGameIfExists(route);
            $('.page').hide();
            $('.' + route).fadeIn(200);
        },
        
        removeRunningGameIfExists: function(route) {
            if (route !== 'runGame') {
                _.each(this.runGameForms, function(runGameForm) {
                    runGameForm.remove();
                });
                this.runGameForms = [];
            }
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
            this.runGameForms.push(new RunGameForm({model: game}));
        }
    });
    
    return LifeGameRouter;
});
