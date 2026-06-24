import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";

const Navbar = () => {
    return (
        <Box>
            <AppBar position="sticky">
                <Toolbar>
                    <Typography
                        flexGrow={1}
                        variant="h6"
                    >
                        Chat Bot
                    </Typography>
                    <Button
                        color="inherit"
                    >
                        Click Me for nothing...
                    </Button>
                </Toolbar>    
            </AppBar>           
        </Box>
    );
}
export default Navbar;