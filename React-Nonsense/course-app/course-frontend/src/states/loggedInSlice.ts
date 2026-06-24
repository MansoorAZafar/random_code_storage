import { createSlice } from "@reduxjs/toolkit";
export const loggedInSlice = createSlice({
    name: 'loggedIn',
    initialState: {
        loggedIn: false
    },
    reducers: {
        toggleLoggedIn: state => {
            state.loggedIn = !state.loggedIn
        }
    }
})

export const { toggleLoggedIn } = loggedInSlice.actions;
export default loggedInSlice.reducer