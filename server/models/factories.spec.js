import _ from 'lodash';

import { sequelize, RealWorldTeam, Player, Team } from './index.js';

function createRealWorldTeam(attributes) {
  return RealWorldTeam.create(_.extend({ name: 'Argentinos Juniors' }, attributes));
}

function createPlayer(attributes) {
  return createRealWorldTeam()
    .then(team => Player.create(_.extend({ name: 'Sarasa', RealWorldTeamId: team.id, position: 'goalkeeper' }, attributes)));
}

function createTeam(attributes) {
  return Team.create(_.extend({ name: 'Sarasa' }, attributes));
}

export { createRealWorldTeam, createPlayer, createTeam }
