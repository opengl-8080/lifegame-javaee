define(function(require) {
    var Backbone = require('Backbone');
    var urlRoot = '/lifegame/api/game/definition';
    
    var GameDefinition = Backbone.Model.extend({
        defaults: {
            size: 5
        },
        
        urlRoot: urlRoot,
        
        validate: function(attrs) {
            if (!attrs.size) {
                return 'サイズを指定してください。';
            }
            
            var size = attrs.size - 0;
            
            if (size < 1) {
                return 'サイズは 1 以上で指定してください。';
            } else if (35 < size) {
                return 'サイズは 35 以下で指定してください。';
            }
        },
        
        register: function() {
            var url = urlRoot + '?size=' + this.get('size');
            
            return this.save(null, {url: url});
        },
        
        update: function() {
            var self = this;
            var url = urlRoot + '/' + this.id;
            
            return this.sync('update', this, {url: url});
        }
    });
    
    return GameDefinition;
});
