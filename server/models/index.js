"use strict";

import fs from 'fs';
import path from 'path';
import Sequelize from 'sequelize';
import _ from 'lodash';

import { sql } from '../config/environment';

const sequelize = new Sequelize(sql.database, sql.username, sql.password, sql);
let db = {};

_(fs.readdirSync(__dirname))
  .reject((it) => it === 'index.js')
  .reject((it) => _.contains(it, '.spec.'))
  .value()
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
