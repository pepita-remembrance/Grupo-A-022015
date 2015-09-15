'use strict';

import _ from 'lodash';
import { Import } from '../../models';

const send = (res, status) => (result) => res.status(status).send(result);

export function index(req, res) {
  Import.findAll().then(send(res, 200)).catch(send(res, 500));
}

export function create(req, res) {
  Import.create(req.body).then(send(res, 201)).catch(send(res, 500));
}
