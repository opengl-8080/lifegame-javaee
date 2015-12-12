define(function(require) {
    var Backbone = require('Backbone');
    
    var RegisterGameDefinitionForm = Backbone.View.extend({
        el: '#registerGameDefinitionForm',
        
        events: {
            'click #registerGameDefinitionButton': 'register'
        },
        
        initialize: function() {
            var self = this;
            
            self.$size = self.$('.size').val(self.model.get('size'));
            self.$message = self.$('.message');
            
            self.model.on('invalid', function() {
                self.$message.text(self.model.validationError);
            });
        },
        
        register: function() {
            console.log('register');
            var self = this;
            
            self.$message.text('');
            self.model.set('size', self.$size.val());
            
            var request = self.model.register();
            
            if (!request) {
                return;
            }
            
            request
                .done(function(response) {
                    self.trigger('register-game-definition', {id: response.id});
                })
                .fail(function(xhr) {
                    self.$message.text('登録失敗');
                });
        },
        
        onInvalidRegister: function() {
            this.$message.text(this.model.validationError);
        }
    });
    
    return RegisterGameDefinitionForm;
});