const createErrorResponse = (code, message) => {
    return {
        status: 'error',
        error: {
            code,
            message
        }
    };
};

/**
 * 
 * @param {Object} data - the data to jsonified 
 */
const createSuccessResponse = (data) => {
    return {
        status: 'ok',
        ...data
    }
}

module.exports = { createErrorResponse, createSuccessResponse };