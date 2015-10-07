'use strict';

import 'should-promised';

import { sequelize, RealWorldTeam, Player } from './index.js';

describe('Player', () => {
  describe('create()', () => {
    it('adds the object to the DB', () => {
      return RealWorldTeam.create({ name: 'Argentinos Juniors' })
        .then(team => Player.create({ name: 'Diego Armando Maradona', RealWorldTeamId: team.id }))
        .then(player => Player.findOne({ where: { id: player.id }, include: [ RealWorldTeam ] }))
        .then(it => it.get({ plain: true }))
        .should.eventually
        .have.properties({ name: 'Diego Armando Maradona' })
        .and.have.propertyByPath('RealWorldTeam', 'name').eql('Argentinos Juniors');
    });

    it('requires a name', () => {
      return Player.create({}).should.be.rejectedWith(/name cannot be null/);
    });

    it('belongs to a real world team', () => {
      return Player.create({ name: 'Sarasa' }).should.be.rejectedWith(/RealWorldTeamId cannot be null/);
    });
  });
});
