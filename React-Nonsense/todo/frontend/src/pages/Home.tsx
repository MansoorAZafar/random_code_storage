import { popupSignIn } from "../../firebase/firebase";
import { Box, Button } from "@mui/material";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();

    useEffect(() => {
        if(localStorage.getItem('IdToken')) {
            navigate('/dashboard'); 
        } 
    }, [])

    const logGoogleUser = async () => {
        try {
            const response = await popupSignIn();
            localStorage.setItem('Idtoken', await response.user.getIdToken());
            
            navigate('/dashboard');
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <Box
            display={"flex"}
            justifyContent={"center"}
            alignItems={"center"}
            flexGrow={1}
        >
           <Button
            size="large"
            variant="outlined"
            onClick={logGoogleUser}
           >
                Login
            </Button> 
        </Box>
    );
}
export default Home;