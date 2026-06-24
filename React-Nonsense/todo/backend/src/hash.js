const crypto = require('crypto');
module.exports = (email) => crypto.hash('256').update(email).digest('hex')