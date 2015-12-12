(function() {
    lg.view.LifeGameBoard = Backbone.View.extend({
        tagName: 'canvas',
        
        constructor: function(options) {
            this.model = options.model;
            var self = this;
            
            this.model.on('search', function() {
                self.render();
            });
        },
        
        render: function() {
            console.log(this.model);
            return this;
        }
    });
})();