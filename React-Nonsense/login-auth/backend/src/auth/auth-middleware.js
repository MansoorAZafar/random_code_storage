const passport = require('passport');
const { createErrorMessage } = require('../response');
const hash = require('../hash');
/**
 * 
 * @param {'http'} strategyName: the strategy we will use 
 * @returns {Function} - middleware for authorization
 */
module.exports =  (strategyName) => {
    return function (req, res, next) {
        /**
         * 
         * @param {Error} err - an error object 
         * @param {string} email - the email of the user
         */
        function callback(err, email) {
            if(err) {
                console.log({err}, `Error authenticating User`)
                return next(createErrorMessage(500, 'Unable to authenticate user'));
            }
            if(!email) {
                console.log(`${email}: User is UNAUTHENTICATED`)
                return res.status(401).json(createErrorMessage(401, 'Unauthorized'));
            }

            req.user = hash(email);
            next();
        }

        passport.authenticate(strategyName, {session: false}, callback)(req, res, next);
    }
};