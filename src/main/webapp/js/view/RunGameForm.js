define(function(require) {
    var Backbone = require('Backbone');
    var LifeGameBoard = require('LifeGameBoard');
    var _ = require('underscore');
    var Game = require('Game');
    
    var SPEED_MAP = {
        'slow': 1000,
        'normal': 700,
        'speedy': 400
    };
    
    var RunGameForm = Backbone.View.extend({
        el: '#runGameForm',
        
        events: {
            'click .stopButton': 'stop',
            'click .restartButton': 'start'
        },
        
        initialize: function() {
            this.$stopButton = this.$('.stopButton');
            this.$restartButton = this.$('.restartButton');
            this.$errorMessage = this.$('.message .error');
            this.$speed = this.$('.speed');
        },
        
        render: function(model) {
            this.model = model;
            this.$errorMessage.text('');
            this.$('.board').empty();
            this.$speed.val('normal');

            this.controlButton('init');
            
            this.model
                .on('error', this.onServerError.bind(this))
                .fetch()
                .done(this.onLoadModel.bind(this));
        },
        
        onLoadModel: function() {
            this.board = new LifeGameBoard({size: this.model.get('size')});
            this.$('.board').append(this.board.el);
            this.refresh();

            this.start();
        },
        
        start: function() {
            this.controlButton('running');
            this.intervalId = setInterval(this.next.bind(this), SPEED_MAP[this.$speed.val()]);
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
            if (this.model) {
                this.stop();
                this.model.destroy();
                delete this.model;
            }
        },
        
        onServerError: function(model, xhr, options) {
            if (xhr.status === 404) {
                this.$errorMessage.text('ゲームが存在しません。');
            } else {
                this.$errorMessage.text('サーバーエラーが発生しました。');
            }
            
            this.controlButton('error');
        },
        
        controlButton: function(status) {
            if (status === 'running') {
                this.$stopButton.attr('disabled', false);
                this.$restartButton.attr('disabled', true);
                this.$speed.attr('disabled', true);
                
            } else if (status === 'stop') {
                this.$stopButton.attr('disabled', true);
                this.$restartButton.attr('disabled', false);
                this.$speed.attr('disabled', false);
                
            } else if (status === 'error' || status === 'init') {
                this.$stopButton.attr('disabled', true);
                this.$restartButton.attr('disabled', true);
                this.$speed.attr('disabled', true);
            }
        }
    });
    
    return RunGameForm;
});