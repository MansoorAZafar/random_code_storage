import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";
import { useSelector, useDispatch } from "react-redux";
import { toggleLoggedIn } from "../states/loggedInSlice";
import type { IRootState } from "../app/store";

const Navbar = () => {
   const loggedIn = useSelector<IRootState, boolean>(state => state.loggedIn.loggedIn); 
   const dispatch = useDispatch()

   const handleLoginClick = () => {
    dispatch(toggleLoggedIn());
   }

   return (
        <Box 
        sx={{flexGrow: 1}}>
            <AppBar position="sticky">
                <Toolbar>
                    <Typography 
                        variant="h5"
                        fontWeight={600}
                        sx={{flexGrow: 1}}
                    >
                        Course-Olio
                    </Typography>
                    {loggedIn ? 
                        <Button onClick={handleLoginClick} color="inherit">Logout</Button>
                            : 
                        <Button onClick={handleLoginClick} color="inherit">Login</Button>
                    }
                </Toolbar>
            </AppBar>
        </Box>    
    );
}
export default Navbar;