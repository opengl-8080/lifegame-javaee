define(function(require) {
    var Backbone = require('Backbone');
    
    var RegisterGameDefinitionForm = Backbone.View.extend({
        el: '#registerGameDefinitionForm',
        
        events: {
            'click #registerGameDefinitionButton': 'register'
        },
        
        initialize: function() {
            this.$size = this.$('.size');
            this.$message = this.$('.message');
            this.$registerButton = this.$('#registerGameDefinitionButton');
        },
        
        render: function(model) {
            this.model = model;
            
            this.$message.text('');
            this.$size.val(model.get('size'));
            this.$registerButton.attr('disabled', false);

            this.model.on('invalid', this.onInvalidRegister.bind(this));
        },
        
        onInvalidRegister: function() {
            this.$message.text(this.model.validationError);
        },
        
        register: function() {
            var self = this;
            
            self.$message.text('');
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
                    self.$message.text('failed to register game definition.');
                })
                .always(function() {
                    self.$registerButton.attr('disabled', false);
                });
        }
    });
    
    return RegisterGameDefinitionForm;
});