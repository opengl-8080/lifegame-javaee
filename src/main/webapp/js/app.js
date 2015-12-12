requirejs.config({
    paths: {
        'LifeGameRouter': 'controller/LifeGameRouter',
        
        'GameDefinition': 'model/GameDefinition',
        
        'RegisterGameDefinitionForm': 'view/RegisterGameDefinitionForm',
        
        'jquery': 'lib/jquery-2.1.4.min',
        'underscore': 'lib/underscore.1.8.3-min',
        'Backbone': 'lib/backbone.1.2.3'
    }
});

define('main', function(require) {
    var LifeGameRouter = require('LifeGameRouter');

    var router = new LifeGameRouter();
    Backbone.history.start();
    
    router.navigate('', {trigger: true});
});

require(['main']);
