const { createSuccessResponse } = require('../../../responseMessage');
const getItem = (req, res) => {
    const code = 200;
    const data = {item: {name: 'keyboard', price: '200'}}
    const success = createSuccessResponse(data);

    return res.status(code).json(success);
}

module.exports = getItem;