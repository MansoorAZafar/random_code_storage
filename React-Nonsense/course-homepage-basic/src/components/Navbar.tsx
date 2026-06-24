import { AppBar, Toolbar, Typography } from "@mui/material";
import { useLocation, type Location } from "react-router-dom";

export default function Navbar() {
    const location: Location<any> = useLocation();
    const currentURL: string = location.pathname;
    
    return (
        currentURL !== '/' &&  ( 
            <AppBar position="sticky" color="primary" >
                <Toolbar>
                    <Typography 
                        variant="h6" 
                        sx={{ mr: 2, fontWeight: 750 }}
                    >
                        Dashboard
                    </Typography>
                </Toolbar>
            </AppBar>
        )
    )
}