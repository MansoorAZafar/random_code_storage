const auth = require('http-auth');
const authPassport = require('http-auth-passport');
const authorize  = require('./auth-middleware');

module.exports.strategy = () => {
    return authPassport(
        auth.basic({
            file: process.env.HTPASSWD_FILE,
        })
    );
}  


module.exports.authenticate = () => authorize('http');