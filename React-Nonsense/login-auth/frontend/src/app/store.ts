import { configureStore } from '@reduxjs/toolkit';
import loggedInReducer  from '../features/loggedInSlice';

const store = configureStore({
  reducer: { loggedIn: loggedInReducer }
})

export type IRootState = ReturnType<typeof store.getState>
export default store;