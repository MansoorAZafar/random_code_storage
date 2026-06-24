const stoppable = require('stoppable');
const app = require('./app');

const server = stoppable(
    app.listen(8080, () => {
        console.log(`Listening on port: ${8080}`)
    })
)
module.exports = server;