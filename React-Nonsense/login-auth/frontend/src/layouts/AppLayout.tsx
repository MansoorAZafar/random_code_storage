import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar"
import { Box } from "@mui/material";
const AppLayout = () => {
    return (
        <Box
            sx={{
                minHeight: '100dvh',
                display: 'flex',
                flexDirection: 'column',
            }}
        >
            <Navbar/>
            <Box sx={{flex: 1}}>
                <Outlet/>
            </Box>
        </Box>
    );
}
export default AppLayout;