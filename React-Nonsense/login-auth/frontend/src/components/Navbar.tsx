import {Box, AppBar, Toolbar, Typography, Button} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import type { IRootState } from "../app/store";
import { toggleLoggedIn } from "../features/loggedInSlice";

const Navbar = () => {
    const loggedIn = useSelector<IRootState, boolean>(state => state.loggedIn.loggedIn)
    const dispatch = useDispatch()

    const toggleAuthentication = () => {
        dispatch(toggleLoggedIn())
    }

    return (
        <Box
            flexGrow={1}
        >
            <AppBar
                position="sticky"
            >
                <Toolbar>
                    <Typography 
                        variant="h6"
                        flexGrow={1}
                    >
                        SignerUper
                    </Typography>
                    {loggedIn == true ? 
                        <Button 
                            color="inherit"
                            onClick={toggleAuthentication}
                        >
                            Authenticated
                        </Button> 
                            : 
                        <Button
                            onClick={toggleAuthentication} 
                            color="inherit"
                        >
                            Unauthenticated
                        </Button>
                    }
                </Toolbar>
            </AppBar>
        </Box>
    ); 
}
export default Navbar;