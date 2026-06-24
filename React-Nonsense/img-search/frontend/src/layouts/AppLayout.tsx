import { Box } from "@mui/material";
import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";

const AppLayout = () => {
    return (
        <Box
            display={"flex"}
            flexGrow={1}
            flexDirection={"column"}
            minHeight="100dvh"
        >
            <Navbar/>
            <Box display="flex" flexGrow={1}>
                <Outlet/>
            </Box>
        </Box>
    );
}
export default AppLayout;