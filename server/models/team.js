"use strict";

export default function(sequelize, DataTypes) {
  const Team = sequelize.define('Team', {
    name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    imageUrl: {
      type: DataTypes.STRING,
      validate: { isUrl: true }
    }
  }, {
    classMethods: {
      associate: models => {
        Team.belongsToMany(models.Player, { through: 'PlayerTeam' });
        Team.belongsTo(models.Player, { as: 'Captain', foreignKey: { allowNull: false } });
      }
    }
  });

  return Team;
}
