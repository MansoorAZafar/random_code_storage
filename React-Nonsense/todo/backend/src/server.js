const stoppable = require('stoppable');
const app = require('./app');

const port = parseInt(process.env.PORT || 8080);
const server = stoppable(
    app.listen(port, () => {
        console.log(`App listening on port: ${port}`);
    })
);

module.exports = server;