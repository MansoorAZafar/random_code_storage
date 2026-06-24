const {createErrorResponse} = require('../response');
const passport = require('passport');
const hash = require('../hash');

module.exports = (strategyName) => {
    return function(req,res,next) {
        function callback(err, email) {
            if(err) {
                console.log({err}, `Failed to authenticate`)
                return next(createErrorResponse(500, 'Unable to authenticate user'));
            }

            if(!email) {
                return res.status(401).json(createErrorResponse(401, 'Unauthorized'));
            }

            req.user = hash(email);
            next();
        }
        passport.authenticate(strategyName, {session: false}, callback)(req,res,next);
    }
};