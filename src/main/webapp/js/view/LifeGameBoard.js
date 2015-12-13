define(function(require) {
    var Backbone = require('Backbone');
    var _ = require('underscore');

    var WIDTH = 20;
    
    var LifeGameBoard = Backbone.View.extend({
        tagName: 'canvas',
        
        initialize: function(options) {
            var self = this;
            var size = options.size;
            var width = (size * WIDTH) + 'px';
            var height = width;
            
            var drag = false;
            
            self.$el
                .attr('width', width)
                .attr('height', height)
                .on('mousedown', function(e) {
                    if (e.which === 1) {
                        drag = true;
                        self.onClickCell(e);
                    }
                })
                .on('mouseup', function() {
                    drag = false;
                })
                .on('mouseout', function() {
                    drag = false;
                })
                .on('mousemove', function(e) {
                    if (drag) {
                        self.onClickCell(e);
                    }
                });
        },
        
        onClickCell: function(e) {
            var rect = e.target.getBoundingClientRect();
            var x = e.clientX - rect.left;
            var y = e.clientY - rect.top;
            
            this.trigger('draw-cell', {h: Math.floor(x/WIDTH), v: Math.floor(y/WIDTH), ctrl: e.ctrlKey});
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