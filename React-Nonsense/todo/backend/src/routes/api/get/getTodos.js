const { createSuccessResponse } = require("../../../response");
const Todo = require('../../../models');

const getTodos = async (req, res) => {
    const data = await Todo.find();    

    const success = createSuccessResponse({'data': data})
    return res.status(200).json(success);
}

module.exports = getTodos;