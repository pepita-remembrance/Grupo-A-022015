/**
 * Main application file
 */

'use strict';

require("babel/register");

// Set default node environment to development
process.env.NODE_ENV = process.env.NODE_ENV || 'development';

var express = require('express');
var config = require('./config/environment');
// Setup server
var app = express();
var server = require('http').createServer(app);
var models = require('./models');

require('./config/express')(app);
require('./routes')(app);

models.sequelize.sync().then(function () {
  server.listen(config.port, config.ip, function () {
    console.log('Express server listening on %d, in %s mode', config.port, app.get('env'));
  });
});

// Expose app
exports = module.exports = app;