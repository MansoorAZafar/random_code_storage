import { Box, Button, TextField } from "@mui/material";
import React, { useState } from "react";

interface SearchBarProps {
    onSubmit: (text: string) => void;
};

export default function SearchBar({onSubmit}: SearchBarProps) {
    const [text, setText] = useState("");

    const HandleTextChange 
        = (event: React.ChangeEvent<HTMLInputElement>) => {
        setText(event.target.value);
    }

    const handleButtonSubmission = () => {
        onSubmit(text)
        setText("")
    }

    const handleEnterPress 
        = (event: React.KeyboardEvent<HTMLDivElement>) => {
        if(event.key === 'Enter') {
            handleButtonSubmission();
        }
    }

    return (
        <Box 
            component="section"
            display={"flex"}
            alignContent={"center"}
            gap={1}
        >
            <TextField
                id="outlined-controlled"
                fullWidth
                value={text}
                onChange={HandleTextChange}
                onKeyDown={handleEnterPress}
            /> 
            <Button
                color="primary"
                onClick={handleButtonSubmission}
                variant="outlined"
            >
                Submit
            </Button>
        </Box>
    
    );
}