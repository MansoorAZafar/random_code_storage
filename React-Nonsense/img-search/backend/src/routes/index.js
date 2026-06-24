const { createSuccessResponse } = require('../response');
const express = require('express');
const router = express.Router();

const { hostname } = require('os');
router.get('/', (req,res) => {
    return res.status(200).json(
        createSuccessResponse({'host': hostname})
    );
});

router.use('/v1', require('./api'));
module.exports = router;