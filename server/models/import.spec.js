'use strict';

import 'should-promised';

import { sequelize, Import } from './index.js';

describe('Import', () => {
  describe('create()', () => {
    it('adds the object to the DB', () => {
      return Import.create({ code: 1234 }).then(it => it.get({ plain: true }))
        .should.eventually
        .have.properties(['code', 'createdAt'])
        .and.have.property('code', 1234);
    });

    it('requires a code', () => {
      return Import.create({}).should.be.rejectedWith(/code cannot be null/);
    });

    it('requires a unique code', () => {
      return Import.create({ code: 1234 })
        .then(Import.create({ code: 1234 }))
        .should.be.rejectedWith(sequelize.UniqueConstraintError);
    });
  });
});
