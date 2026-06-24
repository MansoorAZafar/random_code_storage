const { createSuccessMessage } = require('../response');
const express = require('express');
const router = express.Router();

const { hostname } = require('os');
const { authenticate } = require('../auth');
/**
 * @description: Health check of the server
 * @returns: {[res.status]}: 200 if server is ok, else anything else
 */
router.get('/', (req, res) => {
    console.log(`User entered / route`);
    const data = {
        hostname: hostname()
    };

    const success = createSuccessMessage(data);
    const successCode = 200
    res.status(successCode).json(success);
})

router.use('/v1', authenticate(), require('./api'));
module.exports = router;