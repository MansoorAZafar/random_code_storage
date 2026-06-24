const { createSuccessResponse } = require('../../../response');
const Todo = require('../../../models');

const getTodo = async (req, res) => {
    let response;
    try {
        const id = req.params.id;
        const data = await Todo.findById(id);
        response = data;
    } catch (err) {
        return res.status(400).json({'error': 'something went wrong when getting SINGLE item'});
    }

    return res.status(200).json(createSuccessResponse({"data": response}));
}

module.exports = getTodo;