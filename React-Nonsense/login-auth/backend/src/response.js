/**
 * 
 * @param {number} code: the status code of the error (404, 409, 500 ...) 
 * @param {string} message: the message to be responded in the JSON 
 * @returns {object} Returns an error object that's 
 *  formatted as such: {status: 'error', error: {code, message}} 
*/
const createErrorMessage = (code, message) => {
    return {
        status: 'erorr',
        error: {
            code,
            message
        }
    };
}

/**
 * 
 * @param {object} data: The data that is being returned 
 * @returns {object} a success object that's formatted as: {status: 'ok', ...data}
 */
const createSuccessMessage = (data) => {
    return {
        status: 'ok',
        ...data
    }
}

module.exports = { createErrorMessage, createSuccessMessage };