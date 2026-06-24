import { Box, Button, Stack, TextField, Typography } from "@mui/material";
import { useNavigate, type NavigateFunction } from "react-router-dom";
import {ROUTES} from "../constants"

export default function LoginForm() {
    const navigate: NavigateFunction = useNavigate();
    const onLoginClicked: () => void = () => {
        navigate(ROUTES.home, { replace: true })
    }
    
    return (
        <Box
            sx={{
                border: 1, 
                borderColor: 'primary.main',
                borderRadius: 1,
                p: 3,
                width: 300,
                height: 250,
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
            }}
        >
            <Typography variant="h6" mb={2}>
                Login Form
            </Typography>

            <Stack gap={3} >
                <TextField
                    required
                    id='outlined-required'
                    label='Username'
                />
                <TextField
                    required
                    id='outlined-required-password'
                    label='Password'
                    type='password'
                />

                <Button variant="outlined" onClick={onLoginClicked} >Login</Button>
            </Stack>
        
        </Box>
    )
}