const { getItemsFromDB } = require('../../../model');

const getItems = async (req, res) => {
    const data = await getItemsFromDB();
    return res.status(200).json(data);
}

module.exports = getItems;