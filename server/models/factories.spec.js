import _ from 'lodash';

import { sequelize, RealWorldTeam, Player } from './index.js';

function createRealWorldTeam(attributes) {
  return RealWorldTeam.create(_.extend({ name: 'Argentinos Juniors' }, attributes));
}

function createPlayer(attributes) {
  return createRealWorldTeam()
    .then(team => Player.create(_.extend({ name: 'Sarasa', RealWorldTeamId: team.id, position: 'goalkeeper' }, attributes)));
}

export { createRealWorldTeam, createPlayer }
