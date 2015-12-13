define(function(require) {
    var Backbone = require('Backbone');
    
    var RegisterGameDefinitionForm = Backbone.View.extend({
        el: '#registerGameDefinitionForm',
        
        events: {
            'click #registerGameDefinitionButton': 'register'
        },
        
        initialize: function() {
            this.$size = this.$('.size');
            this.$errorMessage = this.$('.message .error');
            this.$registerButton = this.$('#registerGameDefinitionButton');
        },
        
        render: function(model) {
            this.model = model;
            
            this.$errorMessage.text('');
            this.$size.val(model.get('size'));
            this.$registerButton.attr('disabled', false);

            this.model.on('invalid', this.onInvalidRegister.bind(this));
        },
        
        onInvalidRegister: function() {
            this.$errorMessage.text(this.model.validationError);
        },
        
        register: function() {
            var self = this;
            
            self.$errorMessage.text('');
            self.model.set('size', self.$size.val());
            
            var request = self.model.register();
            
            if (!request) {
                return;
            }
            
            self.$registerButton.attr('disabled', true);
            
            request
                .done(function(response) {
                    self.trigger('register-game-definition', {id: response.id});
                })
                .fail(function(xhr) {
                    self.$errorMessage.text('ゲーム定義の登録に失敗しました。');
                })
                .always(function() {
                    self.$registerButton.attr('disabled', false);
                });
        }
    });
    
    return RegisterGameDefinitionForm;
});