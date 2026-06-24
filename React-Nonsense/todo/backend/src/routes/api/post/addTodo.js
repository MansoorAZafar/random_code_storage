const { createSuccessResponse } = require('../../../response');
const Todo = require('../../../models');

const addTodo = async (req,res) => {
    const { name, text } = req.body;
    const todo = new Todo({
        name,
        text
    });

    let id;
    try {
        const newTodo = await todo.save();
        id = newTodo.id;     
    } catch (err) {
        return res.status(400).json({"error": "couldn't save todo"})
    }

    const success = createSuccessResponse({id});
    return res.status(201).json(success);
} 

module.exports = addTodo;