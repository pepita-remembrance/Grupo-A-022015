'use strict';

import 'should-promised';
import _ from 'lodash';

import { sequelize, RealWorldTeam, Player } from './index.js';
import { createPlayer, createRealWorldTeam } from './factories.spec.js';

describe('Player', () => {
  describe('create()', () => {
    it('adds the object to the DB', () => {
      return createRealWorldTeam()
        .then(team => Player.create({ name: 'Diego Armando Maradona', position: 'goalkeeper', RealWorldTeamId: team.id }))
        .then(player => Player.findOne({ where: { id: player.id }, include: [ RealWorldTeam ] }))
        .then(it => it.get({ plain: true }))
        .should.eventually
        .have.properties({ name: 'Diego Armando Maradona', position: 'goalkeeper' })
        .and.have.propertyByPath('RealWorldTeam', 'name').eql('Argentinos Juniors');
    });

    it('requires a name', () => {
      return createPlayer({ name: null }).should.be.rejectedWith(/name cannot be null/);
    });

    it('requires a valid position', () => {
      return createPlayer({ position: 'developer' }).should.be.rejectedWith(/"developer" is not a valid choice/);
    });

    it('belongs to a real world team', () => {
      return createPlayer({ RealWorldTeamId: null }).should.be.rejectedWith(/RealWorldTeamId cannot be null/);
    });
  });
});
