import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";
import { Box } from "@mui/material";

export default function AppLayout() {
  return (
    <>
      <Navbar/>
      <Box sx={{width: "100%"}}>
        <Outlet/>
      </Box>
    </>
  );
}
