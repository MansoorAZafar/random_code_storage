import { AppBar, Box, Toolbar, Typography } from "@mui/material";

const Navbar = () => {
    return (
        <Box>
            <AppBar
                position="sticky"
            >
                <Toolbar>
                    <Typography variant="h3" fontWeight={700} flexGrow={1}>
                        Backend Reader
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
    );
}
export default Navbar;