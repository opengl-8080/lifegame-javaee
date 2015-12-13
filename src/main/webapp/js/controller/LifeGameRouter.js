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
            this.initializeEditGameDefinitionForm();
            this.initializeRegisterGameDefinitionform();
            this.initializeRunGameForm();
            
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
                this.runGameForm.remove();
            }
        },
        
        initializeEditGameDefinitionForm: function() {
            var self = this;

            self.editGameDefinitionForm = new EditGameDefinitionForm();

            self.editGameDefinitionForm.on('start-game', function(game) {
                self.navigate('page/game/' + game.id, true);
            });
        },
        
        initializeRegisterGameDefinitionform: function() {
            var self = this;
            
            this.registerGameDefinitionForm = new RegisterGameDefinitionForm();

            this.registerGameDefinitionForm.on('register-game-definition', function(param) {
                self.navigate('page/game/definition/' + param.id, true);
            });
        },
        
        initializeRunGameForm: function() {
            this.runGameForm = new RunGameForm();
        },
        
        registerGameDefinition: function() {
            this.registerGameDefinitionForm.render(new GameDefinition());
        },
        
        editGameDefinition: function(id) {
            this.editGameDefinitionForm.render(new GameDefinition({id: id}));
        },
        
        runGame: function(id) {
            this.runGameForm.render(new Game({id: id}));
        }
    });
    
    return LifeGameRouter;
});
