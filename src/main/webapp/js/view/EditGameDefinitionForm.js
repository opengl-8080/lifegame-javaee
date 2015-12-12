define(function(require) {
    var Backbone = require('Backbone');
    var LifeGameBoard = require('LifeGameBoard');
    
    var EditGameDefinitionForm = Backbone.View.extend({
        el: '#editGameDefinitionForm',
        
        events: {
            'click .startButton': 'start',
            'click .saveButton': 'save',
            'click .removeButton': 'remove'
        },
        
        initialize: function() {
            this.model
                .fetch()
                .done(this.onLoadModel.bind(this));
        },
        
        onLoadModel: function() {
            this.board = new LifeGameBoard({size: this.model.get('size')});
            
            this.$('.board').empty().append(this.board.el);
            
            this.board.on('click-cell', this.onClickCell.bind(this));
            
            this.board.render(this.model.get('cells'));
        },
        
        onClickCell: function(position) {
            var size = this.model.get('size');
            
            if (position.h < size && position.v < size) {
                var cells = this.model.get('cells');
                cells[position.h][position.v] = !cells[position.h][position.v];
                this.board.render(cells);
            }
        },
        
        start: function() {
            console.log('start');
        },
        
        save: function() {
            this.model.update();
        },
        
        remove: function() {
            this.model.destroy();
        }
    });
    
    return EditGameDefinitionForm;
});