"use strict";

import fs from 'fs';
import path from 'path';
import Sequelize from 'sequelize';
import all from '../config/environment/index.js';

const sequelize = new Sequelize(all.sql.database, all.sql.username, all.sql.password, all.sql);
let db = {};

fs
  .readdirSync(__dirname)
  .filter(function(file) {
    return (file.indexOf(".") !== 0) && (file !== "index.js");
  })
  .forEach(function(file) {
    var model = sequelize.import(path.join(__dirname, file));
    db[model.name] = model;
  });

Object.keys(db).forEach(modelName => {
  if ("associate" in db[modelName]) {
    db[modelName].associate(db);
  }
});

db.sequelize = sequelize;
db.Sequelize = Sequelize;

export default db;
