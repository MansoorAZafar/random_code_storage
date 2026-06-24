const {createErrorResponse} = require('./response');
const authenticate = require('./auth');
const compression = require('compression');
const passport = require('passport');
const helm = require('helmet');
const path = require('path');
const mongoose = require('mongoose');
const express = require('express');
const cors = require('cors');
const app = express();
const admin = require('firebase-admin');

app.use(helm());
app.use(compression());
app.use(cors());

const serviceAccountPath = path.resolve(process.env.FIREBASE_SERVICE_ACCOUNT);
const serviceAccount = require(serviceAccountPath);
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
})

passport.use(authenticate.strategy());
app.use(passport.initialize());


mongoose.connect(process.env.CONNECTION_STRING);
const db = mongoose.connection;

db.on('error', (error) => console.log(error));
db.once('open', () => console.log('Connected to Database'));

app.use(express.json());
app.use('/', require('./routes'));

/**
 * @description: Handles any route that doesn't exist, 404 middleware
 */
app.use((req, res) => {
    const status = 404;
    const message = 'Resource not found';
    const error = createErrorResponse(status, message);

    return res.status(status).json(error);
});

/**
 * @description: Handles any error thrown and logs it
 */
app.use((err, req, res, next) => {
    const status = err.status || 500;
    const message = err.message || "Unable to process request";
    const error = createErrorResponse(status, message);    

    if(status < 409) {
        console.log({err}, `Error Processing Request`);
    }

    res.status(status).json(error);
})

module.exports = app;