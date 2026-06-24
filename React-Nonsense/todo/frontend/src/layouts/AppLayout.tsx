import { Box } from "@mui/material"
import { Outlet } from "react-router-dom"
import Navbar from "../components/Navbar";

const AppLayout = () => {
    return (
        <Box
            minHeight="100dvh"
            display="flex"
            flexDirection="column"
        >
            <Navbar/>
            <Box flexGrow={1} display={"flex"}>
                <Outlet/>
            </Box>
        </Box>
    )
}

export default AppLayout;