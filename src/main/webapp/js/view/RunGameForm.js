define(function(require) {
    var Backbone = require('Backbone');
    var LifeGameBoard = require('LifeGameBoard');
    var Game = require('Game');
    
    var RunGameForm = Backbone.View.extend({
        el: '#runGameForm',
        
        events: {
            'click .stopButton': 'stop',
            'click .restartButton': 'start'
        },
        
        initialize: function() {
            this.model
                .fetch()
                .done(this.onLoadModel.bind(this));
        },
        
        onLoadModel: function() {
            this.board = new LifeGameBoard({size: this.model.get('size')});
            
            this.$('.board').empty().append(this.board.el);
            
            this.start();
        },
        
        start: function() {
            this.intervalId = setInterval(this.next.bind(this), 1000);
        },
        
        next: function() {
            this.model
                .next()
                .done(this.refresh.bind(this));
        },
        
        refresh: function() {
            this.board.render(this.model.get('cells'));
        },
        
        stop: function() {
            clearInterval(this.intervalId);
            delete this.intervalId;
        }
    });
    
    return RunGameForm;
});