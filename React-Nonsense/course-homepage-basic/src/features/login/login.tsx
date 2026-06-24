import { Box } from "@mui/material";
import LoginForm from "../../components/LoginForm";
import reactLogo from "../../assets/react.svg";

export default function Login() {
    return (
        <Box
            display={"flex"}
            width={"100%"}
            height={"100%"}
        >
            <Box sx={{
                flex: 1,
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
            }}>
                <Box
                    component="img"
                    src={reactLogo}
                    sx={{
                        display: { xs: 'none', md: 'block' },
                        width: "100%",
                        height: "100%",
                        objectFit: "contain",
                    }}
                />
            </Box>
            <Box sx={{
                flex: 1,
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center'
            }}>
                <LoginForm/>
            </Box>
        </Box>
    )
}