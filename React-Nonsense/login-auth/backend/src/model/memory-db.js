let db = {
    1: {
        name: 'bob',
        title: 'joe banks v1',
        img: 'https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png'
    },
    2: {
        name: 'geogre',
        title: 'the ducks v2',
        img: 'https://static.vecteezy.com/system/resources/thumbnails/008/695/917/small/no-image-available-icon-simple-two-colors-template-for-no-image-or-picture-coming-soon-and-placeholder-illustration-isolated-on-white-background-vector.jpg'
    },
    3: {
        name: 'stimpay',
        title: 'the end of the tomorrow',
        img: 'https://static.vecteezy.com/system/resources/thumbnails/004/511/281/small/default-avatar-photo-placeholder-profile-picture-vector.jpg'
    }
};


const addItemToDB = (name, title, img) => {
    const length = Object.keys(db).length;
    const key = length + 1;

    db[key] = {name, title, img};
    return Promise.resolve(key);
}

/**
 * 
 * @returns {Object} returns all the items 
 */
const getItemsFromDB = () => {
    let _data = []
    for(const key in db) {
        _data.push(db[key])
    }

    return Promise.resolve(_data);
}

module.exports = { addItemToDB, getItemsFromDB }