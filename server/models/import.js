"use strict";

export default function(sequelize, DataTypes) {
  return sequelize.define('Import', {
    code: { type: DataTypes.BIGINT, allowNull: false, unique: true }
  }, {});
}
