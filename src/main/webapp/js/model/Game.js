define(function(require) {
    var Backbone = require('Backbone');
    var urlRoot = '/lifegame/api/game';
    
    var Game = Backbone.Model.extend({
        urlRoot: urlRoot,
        
        register: function(gameDefinitionId) {
            var url = urlRoot + '?game-definition-id=' + gameDefinitionId;
            return this.save(null, {url: url});
        }
    });
    
    return Game;
});
