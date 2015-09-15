'use strict';

// Development specific configuration
// ==================================
module.exports = {
  sql: {
    dialect: 'sqlite',
    storage: './db.development.sqlite'
  },

  seedDB: true
};
