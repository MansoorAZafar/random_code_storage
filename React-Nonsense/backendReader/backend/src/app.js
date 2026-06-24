const { createErrorResponse } = require('./responseMessage');
const express = require('express');
const compression = require('compression');
const cors = require('cors');
const helmet = require('helmet')
const app = express();

app.use(express.json());
app.use(compression())
app.use(helmet())
app.use(cors())

app.use('/', require('./routes'));
// 404 routing
app.use((req, res, next) => {
    const message = 'Resource not found'
    const code = 404
    const error = createErrorResponse(code, message);

    res.status(code).json(error);
});

app.use((err, req, res, next) => {
    const code = err.code || 500
    const message = err.message || 'Unable to process request'
    const error = createErrorResponse(code, message)
    
    if(code > 409) {
        console.log({err}, `Error processing request`);
    }

    return res.status(code).json(error)
})
module.exports = app;