const express = require('express');
const router = express.Router();

router.get('/item', require('./get/item'));
module.exports = router;