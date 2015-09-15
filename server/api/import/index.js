'use strict';

import express from 'express';
import { index, create } from './import.controller';

const router = express.Router();

router.get('/', index);
router.post('/', create);

export default router;
