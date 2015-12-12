define(function(require) {
    var Backbone = require('Backbone');
    var _ = require('underscore');

    var WIDTH = 20;
    
    var LifeGameBoard = Backbone.View.extend({
        tagName: 'canvas',
        
        initialize: function(options) {
            var width = (options.size * WIDTH) + 'px';
            var height = (options.size * WIDTH) + 'px';
            
            this.$el
                .attr('width', width)
                .attr('height', height)
                .on('click', this.onClickCell.bind(this));
        },
        
        onClickCell: function(e) {
            var rect = e.target.getBoundingClientRect();
            var x = e.clientX - rect.left;
            var y = e.clientY - rect.top;
            
            this.trigger('click-cell', {h: Math.floor(x/WIDTH), v: Math.floor(y/WIDTH)});
        },
        
        render: function(cells) {
            var context = this.el.getContext('2d');
            
            _.each(cells, function(row, v) {
                _.each(row, function(alive, h) {
                    var args = [v*WIDTH, h*WIDTH, WIDTH, WIDTH];
                    
                    context.clearRect.apply(context, args);
                    
                    if (alive) {
                        context.fillRect.apply(context, args);
                    } else {
                        context.strokeRect.apply(context, args);
                    }
                });
            });
            
            return this;
        }
    });
    
    return LifeGameBoard;
});