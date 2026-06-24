require('dotenv').config();
process.on('uncaughtException', (err, origin) => {
    console.log({err, origin}, `Uncaught error`);
    throw err;
});
process.on('unhandledRejection', (reason, promise) => {
    console.log({reason, promise}, `Unhandled Rejection`);
    throw reason;
});

require('./server');