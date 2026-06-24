const { createSuccessResponse, createErrorResponse } = require('../../../response');
const Todo = require('../../../models');

const removeTodo = async (req, res) => {
    const id = req.params.id;
    
    try { 
        const todo = await Todo.findByIdAndDelete(id);
    } catch (err) {
        return res.status(404).createErrorResponse({id: "doesn't exist"})
    }
    
    const success = createSuccessResponse({"success": true})
    return res.status(200).json(success);
}
module.exports = removeTodo;