"use strict";

export default function(sequelize, DataTypes) {
  return sequelize.define('RealWorldTeam', {
    name: { type: DataTypes.STRING, allowNull: false }
  });
}
