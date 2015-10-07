'use strict';

import 'should-promised';

import { sequelize, RealWorldTeam } from './index.js';

describe('RealWorldTeam', () => {

  describe('create()', () => {
    it('adds the object to the DB', () => {
      return RealWorldTeam.create({ name: 'San Lorenzo de Almagro' }).then(it => it.get({ plain: true }))
        .should.eventually
        .have.properties({ name: 'San Lorenzo de Almagro' })
    });

    it('requires a name', () => {
      return RealWorldTeam.create({}).should.be.rejectedWith(/name cannot be null/);
    });
  });

});
