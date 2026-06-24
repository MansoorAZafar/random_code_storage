require('dotenv').config();
process.on('uncaughtException', (error, origin) => {
    console.log({error, origin}, `Uncaught Error`);
    throw error;
})
process.on('unhandledRejection', (reason, promise) => {
    console.log({reason, promise}, `Unandled Promise`);
    throw reason;
});

require('./server');