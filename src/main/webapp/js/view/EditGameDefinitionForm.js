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
            this.$errorMessage = this.$('.message .error');
            this.$successMessage = this.$('.message .success');
            this.$startButton = this.$('.startButton');
            this.$updateButton = this.$('.updateButton');
            this.$removeButton = this.$('.removeButton');
            this.$board = this.$('.board');
        },
        
        render: function(model) {
            this.model = model;
            this.$errorMessage.text('');
            this.$successMessage.text('');
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
            this.controlButton('lock');
            game.register(this.model.id)
                .done(this.trigger.bind(this, 'start-game', game));
        },
        
        update: function() {
            var self = this;
            
            this.model
                .update()
                .done(function(response) {
                    self.controlButton('update-success');
                    self.$successMessage.text('更新が完了しました。');
                    self.model.set('version', response.version);
                })
                .fail(function(xhr) {
                    self.onServerError(self, xhr);
                });
        },
        
        remove: function() {
            var self = this;
            
            this.model
                .destroy()
                .done(function() {
                    self.controlButton('remove-success');
                    self.$successMessage.text('ゲーム定義を削除しました。');
                    self.$board.empty();
                });
        },
        
        onServerError: function(model, xhr, options) {
            if (xhr.status === 404) {
                this.$errorMessage.text('ゲーム定義が存在しません。');
            } else if (xhr.status === 409) {
                this.$errorMessage.text('同時更新されています。再検索してください。');
            } else {
                this.$errorMessage.text('サーバーエラーが発生しました。');
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