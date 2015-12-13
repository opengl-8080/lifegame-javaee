define(function(require) {
    var Backbone = require('Backbone');
    var LifeGameBoard = require('LifeGameBoard');
    var Game = require('Game');
    var _ = require('underscore');
    
    var EditGameDefinitionForm = Backbone.View.extend({
        el: '#editGameDefinitionForm',
        
        events: {
            'click .startButton': 'start',
            'click .updateButton': 'update',
            'click .removeButton': 'remove'
        },
        
        initialize: function() {
            this.$message = this.$('.message');
            this.$startButton = this.$('.startButton');
            this.$updateButton = this.$('.updateButton');
            this.$removeButton = this.$('.removeButton');
            this.$board = this.$('.board');
        },
        
        render: function(model) {
            this.model = model;
            this.$message.text('');
            this.$board.empty();

            this.model
                .on('request', _.bind(this.controlButton, this, 'lock'))
                .on('error', this.onServerError.bind(this))
                .fetch()
                .done(this.onLoadModel.bind(this));
            
            return this;
        },
        
        onLoadModel: function() {
            this.controlButton('fetch-success');
            
            this.board = new LifeGameBoard({size: this.model.get('size')});
            
            this.$board.append(this.board.el);
            
            this.board.on('click-cell', this.onClickCell.bind(this));
            
            this.board.render(this.model.get('cells'));
        },
        
        onClickCell: function(position) {
            this.controlButton('changed');
            var size = this.model.get('size');
            
            if (position.h < size && position.v < size) {
                var cells = this.model.get('cells');
                cells[position.h][position.v] = !cells[position.h][position.v];
                this.board.render(cells);
            }
        },
        
        start: function() {
            var game = new Game();
            
            game.register(this.model.id)
                .done(this.trigger.bind(this, 'start-game', game));
        },
        
        update: function() {
            var self = this;
            
            this.model
                .update()
                .done(function() {
                    self.controlButton('update-success');
                    self.$message.text('update successful.');
                });
        },
        
        remove: function() {
            var self = this;
            
            this.model
                .destroy()
                .done(function() {
                    self.controlButton('remove-success');
                    self.$message.text('Game definition is removed.');
                    self.$board.empty();
                });
        },
        
        onServerError: function(model, xhr, options) {
            if (xhr.status === 404) {
                this.$message.text('Game definition is not found.');
            }
            
            this.controlButton('error');
        },
        
        controlButton: function(status) {
            if (status === 'error' || status === 'lock' || status === 'remove-success') {
                this.$startButton.attr('disabled', true);
                this.$updateButton.attr('disabled', true);
                this.$removeButton.attr('disabled', true);
            } else if (status === 'update-success' || status === 'fetch-success') {
                this.$startButton.attr('disabled', false);
                this.$updateButton.attr('disabled', true);
                this.$removeButton.attr('disabled', false);
            } else if (status === 'changed') {
                this.$startButton.attr('disabled', true);
                this.$updateButton.attr('disabled', false);
                this.$removeButton.attr('disabled', false);
            }
        }
    });
    
    return EditGameDefinitionForm;
});