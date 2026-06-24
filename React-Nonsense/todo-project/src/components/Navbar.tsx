import {AppBar, Button, Typography, Toolbar, IconButton, Box } from "@mui/material";
import EventNoteIcon from '@mui/icons-material/EventNote';

export default function Navbar() {
    const currentURL = window.location.pathname;

    return (
        <Box sx={{flexGrow: 1}}>
            
            <AppBar position="sticky" color="primary">
                <Toolbar>
                    <IconButton edge="start" 
                        sx={{ mr: 2 }}
                        color="inherit"
                    >
                        <EventNoteIcon/>
                    </IconButton>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        Todo App
                    </Typography>
                    <Button color="inherit" href="/" variant={currentURL == '/' ? "outlined" : "text"}>
                        All 
                    </Button>
                    <Button color="inherit" href="/saved" variant={currentURL == '/saved' ? "outlined" : "text"}>
                        Favourite
                    </Button>
                </Toolbar>
            </AppBar>
        </Box>
    )
}