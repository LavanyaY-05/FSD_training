import { configureStore } from "@reduxjs/toolkit";
import { characterReducer } from "./store/Reducer/CharacterReducer";

export const store = configureStore({
    reducer:{
        characters: characterReducer,
    }
})