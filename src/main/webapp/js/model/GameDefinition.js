(function() {
    var urlRoot = '/lifegame/api/game/definition';
    
    lg.model.GameDefinition = Backbone.Model.extend({
        defaults: {
            size: 5
        },
        register: function() {
            var url = urlRoot + '?size=' + this.get('size');
            return this.save(null, {url: url});
        },
        search: function() {
            var _self = this;
            var url = urlRoot + '/' + this.id;
            
            return this.fetch({url: url})
                .done(function() {
                    _self.trigger('search');
                });
        }
    });
    
    
})();
