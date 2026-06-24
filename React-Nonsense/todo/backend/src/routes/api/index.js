const express = require('express');
const router = express.Router();

router.delete('/todo/:id', require('./delete/removeTodo'));
router.get('/todo/:id', require('./get/getTodo'));
router.get('/todos', require('./get/getTodos'));
router.post('/todo', require('./post/addTodo'));

module.exports = router;