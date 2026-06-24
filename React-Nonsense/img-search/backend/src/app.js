const { createErrorResponse } = require('./response');
const compression = require('compression');
const express     = require('express');
const cors        = require('cors');
const app         = express();

app.use(cors());
app.use(compression());

app.use('/', require('./routes'));

// Handle any route not handled
app.use((req, res) => {
    const code = 404;
    const message = "Resource not found";
    const error = createErrorResponse(code, message);

    return res.status(code).json(error);
});

// handle any errors thrown
app.use((err, req, res, next) => {
    const code = err.code || 500
    const message = err.message || "Unable to process reqest";
    const error = createErrorResponse(code, message);

    if(code > 409) {
        console.log({err}, `Error processing Request`);
    }

    return res.status(code).json(error);
});

module.exports = app;