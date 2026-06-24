const BearerStrategy = require('passport-http-bearer');
const authorize = require('./auth-middleware');
const admin = require('firebase-admin');

module.exports.strategy = () => {
    return new BearerStrategy(async (token, done) => {
        try {
            const user = await admin.auth().verifyIdToken(token);
            done(null, user.email);
        } catch (err) {
            done(null, false);
        }
    })
}
module.exports.authenticate = () => authorize('bearer');