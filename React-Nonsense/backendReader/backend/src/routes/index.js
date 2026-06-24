const { createSuccessResponse }  = require('../responseMessage');
const express = require('express');
const router = express.Router();

const { hostname } = require('os')
router.get('/', (req, res) => {
    const code = 200
    const data = {'host': hostname()}
    const success = createSuccessResponse(data)
    
    return res.status(code).json(success)   
})

router.use('/api', require('./api'));
module.exports = router;