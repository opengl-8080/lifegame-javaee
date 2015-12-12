define(function(require) {
    var Backbone = require('Backbone');
    var _ = require('underscore');
    var urlRoot = '/lifegame/api/game';
    
    var Game = Backbone.Model.extend({
        urlRoot: urlRoot,
        
        register: function(gameDefinitionId) {
            var url = urlRoot + '?game-definition-id=' + gameDefinitionId;
            return this.save(null, {url: url});
        },
        
        next: function() {
            var self = this;
            return this.sync('create', this).done(function(response) {
                self.set('cells', response.cells)
            });
        }
    });
    
    return Game;
});
