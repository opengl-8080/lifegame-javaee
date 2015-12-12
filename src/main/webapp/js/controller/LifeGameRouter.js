define(function(require) {
    var $ = require('jquery');
    var Backbone = require('Backbone');
    var _ = require('underscore');
    var GameDefinition = require('GameDefinition');
    var RegisterGameDefinitionForm = require('RegisterGameDefinitionForm');
    
    var LifeGameRouter = Backbone.Router.extend({
        routes: {
            '': 'registerGameDefinition',
            'page/game/definition/:id': 'editGameDefinition'
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
            
            form.on('registerGameDefinition', function(param) {
                self.navigate('page/game/definition/' + param.id, true);
            });
        },
        
        editGameDefinition: function() {
            
        }
    });
    
    return LifeGameRouter;
});
