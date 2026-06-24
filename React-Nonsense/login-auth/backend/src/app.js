const { createErrorMessage } = require('./response');
const authenticate = require('./auth')
const express = require('express');
const passport = require('passport');
const helmet = require('helmet');
const compression = require('compression');
const cors = require('cors');

const app = express();
passport.use(authenticate.strategy())
app.use(passport.initialize());

app.use(express.json());
app.use(helmet());
app.use(cors());
app.use(compression());

/**
 * @description: Handle the routing of all routes other than 404
 */
app.use('/', require('./routes'));


/**
* @description: 404 middleware, to handle any reqeuests to a non found resource
* @returns {[res.status(404)]} 404 response with JSON invalid message
*/
app.use((req, res) => {
    const errorCode = 404;
    const errorMessage = "resource not found";
    const error = createErrorMessage(errorCode, errorMessage);

    return res.status(errorCode).json(error);
})

/**
* @description: Error handling middleware for anything else 
*/
app.use((err, req, res, next) => {
    const status = err.status || 500;
    const message = err.message || "Unable to process request";
    const error = createErrorMessage(status, message);

    if(status > 409) {
        console.log({err}, `Error Processing Request`);
    }

    res.status(status).json(error);
})

module.exports = app;