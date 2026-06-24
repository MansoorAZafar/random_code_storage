import { useQuery } from "@tanstack/react-query";
import { useSelector } from "react-redux";
import type { IRootState } from "../app/store";
import { Box, Container, Typography } from "@mui/material";
import CardList from "../components/CardList";

const Home = () => {

    const loggedIn = useSelector<IRootState, boolean>(state => state.loggedIn.loggedIn)
    const { isPending, error, data } = useQuery({
        queryKey: ['items'],
        queryFn: () => {
           return fetch('http://localhost:8080/v1/items', {
            headers: {
                'Authorization': 'Basic ' + btoa(`admin:admin`)
            }
           }).then((res) => res.json());
        }
    })
    if (error) return 'An error has occurred: ' + error.message
    
    const renderCards = () => {
        return (
            <Container
                disableGutters
            >
                { !isPending && <CardList items={data}/> }
            </Container>
        );
    }

    const renderUnauthenticatedScreen = () => {
        return (
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                alignContent="center"
            >
                <Typography 
                    variant="h2"
                >
                    Authenticate to show items
                </Typography>
            </Box>
        )
    }

    return ( 
        loggedIn ? renderCards() : renderUnauthenticatedScreen() 
    );
}
export default Home;