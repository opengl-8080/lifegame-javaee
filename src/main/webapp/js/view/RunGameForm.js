define(function(require) {
    var Backbone = require('Backbone');
    var LifeGameBoard = require('LifeGameBoard');
    var _ = require('underscore');
    var Game = require('Game');
    
    var RunGameForm = Backbone.View.extend({
        el: '#runGameForm',
        
        events: {
            'click .stopButton': 'stop',
            'click .restartButton': 'start'
        },
        
        initialize: function() {
            this.$stopButton = this.$('.stopButton');
            this.$restartButton = this.$('.restartButton');
            this.$message = this.$('.message').empty('');
            
            this.controlButton('init');
            
            this.model
                .on('error', this.onServerError.bind(this))
                .fetch()
                .done(this.onLoadModel.bind(this));
        },
        
        onLoadModel: function() {
            this.board = new LifeGameBoard({size: this.model.get('size')});
            
            this.$('.board').empty().append(this.board.el);
            
            this.start();
        },
        
        start: function() {
            this.controlButton('running');
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
            this.controlButton('stop');
            clearInterval(this.intervalId);
            delete this.intervalId;
        },
        
        remove: function() {
            this.stop();
            this.model.destroy();
        },
        
        onServerError: function(model, xhr, options) {
            if (xhr.status === 404) {
                this.$message.text('Game is not found.');
            }
            
            this.controlButton('error');
        },
        
        controlButton: function(status) {
            if (status === 'running') {
                this.$stopButton.attr('disabled', false);
                this.$restartButton.attr('disabled', true);
                
            } else if (status === 'stop') {
                this.$stopButton.attr('disabled', true);
                this.$restartButton.attr('disabled', false);
                
            } else if (status === 'error' || status === 'init') {
                this.$stopButton.attr('disabled', true);
                this.$restartButton.attr('disabled', true);
            }
        }
    });
    
    return RunGameForm;
});