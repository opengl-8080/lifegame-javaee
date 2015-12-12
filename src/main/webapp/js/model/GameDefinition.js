define(function(require) {
    var Backbone = require('Backbone');
    var urlRoot = '/lifegame/api/game/definition';
    
    var GameDefinition = Backbone.Model.extend({
        defaults: {
            size: 5
        },
        
        validate: function(attrs) {
            if (!attrs.size) {
                return 'サイズを指定してください';
            }
            
            var size = attrs.size - 0;
            
            if (size < 1) {
                return 'サイズは 1 以上で指定してください';
            } else if (100 < size) {
                return 'サイズは 100 以下で指定してください';
            }
        },
        
        register: function() {
            var self = this;
            var url = urlRoot + '?size=' + self.get('size');
            
            return self.save(null, {url: url});
        },
        
        search: function() {
            var self = this;
            var url = urlRoot + '/' + self.id;
            
            return self.fetch({url: url})
                        .done(function() {
                            self.trigger('search');
                        });
        }
    });
    
    return GameDefinition;
});
