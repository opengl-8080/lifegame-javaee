requirejs.config({
    paths: {
        'LifeGameRouter': 'controller/LifeGameRouter',
        
        'GameDefinition': 'model/GameDefinition',
        
        'RegisterGameDefinitionForm': 'view/RegisterGameDefinitionForm',
        'EditGameDefinitionForm': 'view/EditGameDefinitionForm',
        'LifeGameBoard': 'view/LifeGameBoard',
        
        'jquery': 'lib/jquery-2.1.4.min',
        'underscore': 'lib/underscore.1.8.3-min',
        'Backbone': 'lib/backbone.1.2.3'
    }
});

define('main', function(require) {
    var LifeGameRouter = require('LifeGameRouter');
    
    new LifeGameRouter();
    Backbone.history.start();
});

require(['main']);
