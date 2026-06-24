const { createSuccessResponse } = require('../../../response');
const getTest = (req, res) => {
    return res.status(200).json(
        createSuccessResponse({'test': true})
    );
}
module.exports = getTest;