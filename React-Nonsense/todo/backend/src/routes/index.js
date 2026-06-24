const { authenticate } = require('../auth');
const { createSuccessResponse } = require('../response');
const express = require('express');
const router = express.Router();

const {hostname} = require('os');

/**
 * @description: Returns a health check of the sevrer
 * @returns {JSON}: {status: ok, hostname: [DEVICE-HOST]}
 */
router.get('/', (req, res) => {
    const data = {
        hostname
    };
    const success = createSuccessResponse(data);
    const code = 200;

    return res.status(code).json(success);
})

router.use('/v1', require('./api'));
module.exports = router;