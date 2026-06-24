import { Box, Grid } from "@mui/material";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import type { TodoShape } from "../types";
import TodoCard from "../components/TodoCard";

const Home = () => {
    const queryClient = useQueryClient()
    const query = useQuery<TodoShape[]>({
        queryKey: ['todos'],
        queryFn: () => 
            fetch('https://jsonplaceholder.typicode.com/todos')
            .then((res) => res.json())
    })    



    return (
        <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
            m={3}
        >
            <Grid container rowSpacing={5} columnSpacing={{xs: 1, sm: 2, md: 2}}>
                {query.data?.map((item) => (
                    <Grid 
                        size={{sm: 6, md: 6, lg: 3}} 
                        key={item.id}
                        border="10px solid green"
                    >
                        <TodoCard
                            userId={item.userId}
                            id={item.id}
                            title={item.title}
                            completed={item.completed}
                        />
                    </Grid>
                ))}
            </Grid>
        </Box>    
    );
}
export default Home;