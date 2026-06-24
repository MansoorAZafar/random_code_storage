import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";
import { Box } from "@mui/material";

export default function AppLayout() {
  return (
    <Box
      sx={{
        height: "100vh",
        display: 'flex',
        flexDirection: 'column',
        overflow: "hidden"
      }}
    >
      <Navbar />
      <Box
        component="main"
        sx={{
          flex: 1,
          display: 'flex'
        }}
      >
        <Outlet />
      </Box>
    </Box>
  );
}
