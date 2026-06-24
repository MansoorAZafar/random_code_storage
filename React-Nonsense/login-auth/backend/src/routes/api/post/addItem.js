const { addItemToDB } = require('../../../model');
const { createSuccessMessage } = require('../../../response');
const addItem = async (req, res) => {
    const {name, title, img} = req.body;
    const key = await addItemToDB(name, title, img);
    // console.log(key);

    const successMessage = createSuccessMessage({key});
    return res.status(200).json(successMessage);
}

module.exports = addItem;