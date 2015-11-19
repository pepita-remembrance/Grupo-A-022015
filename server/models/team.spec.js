'use strict';

import 'should-promised';
import _ from 'lodash';
import Promise from 'bluebird';

import { Team, Player } from './index.js';
import { createTeam, createPlayer} from './factories.spec.js';

describe('Team', () => {
  describe('create()', () => {
    it('adds the object to the DB', () => {
      return Promise.all([ createPlayer({ name: 'Leo Gassman' }), createPlayer({ name: 'Nico Passerini' }) ])
        .spread((leo, nico) => [Team.create({ name: 'UNQ', imageUrl: 'logo.jpeg', PlayersId: [leo.id, nico.id], CaptainId: leo.id }), leo, nico])
        .spread((team, ...players) => { team.addPlayers(players); return team })
        .then(team => Team.findOne({ where: { id: team.id }, include: [ { model: Player, as: 'Players' } ] }))
        .then(it => it.get({ plain: true }))
        .should.eventually
        .have.properties({ name: 'UNQ', imageUrl: 'logo.jpeg' })
        .and.have.propertyByPath('Captain', 'name').eql('Leo Gassman');
    });

    it('requires a name', () => {
      return createTeam({ name: null }).should.be.rejectedWith(/name cannot be null/);
    });

    it('requires a valid image url', () => {
      return createTeam({ imageUrl: 'not a valid image' }).should.be.rejectedWith(/Validation isUrl failed/);
    });

    it('has a captain', () => {
      return createTeam({ CaptainId: null }).should.be.rejectedWith(/CaptainId cannot be null/);
    });

    it('has at least one player', () => {

    });
  });
});
