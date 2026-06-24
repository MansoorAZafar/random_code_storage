process.on('uncaughtException', (error, origin) => {
    console.log({error, origin}, 'Unhandled Exception');
    throw error;
})

process.on('unhandledRejection', (reason, promise) => {
    console.log({reason, promise}, 'Unhandled Promise');
    throw reason;
})

require('./server');