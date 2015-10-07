"use strict";

export default function(sequelize, DataTypes) {
  const Player = sequelize.define('Player', {
    name: { type: DataTypes.STRING , allowNull: false }
  }, {
    classMethods: {
      associate: models => Player.belongsTo(
        models.RealWorldTeam,
        { foreignKey: { allowNull: false } }
      )
    }
  });

  return Player;
}
