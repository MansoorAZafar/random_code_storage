const express = require('express');
const router = express.Router();

router.get('/items', require('./get/getItems'));
router.post('/item', require('./post/addItem'));
module.exports = router;