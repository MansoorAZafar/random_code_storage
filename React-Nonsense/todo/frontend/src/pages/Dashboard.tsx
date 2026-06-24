import { Box, Grid } from "@mui/material";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import TodoCard from "../components/TodoCard";
import type { CardShape } from "../components/TodoCard";

const Dashboard = () => {
    const queryClient = useQueryClient();

    const deleteItemMutation = useMutation({
        mutationFn: (item: CardShape) => {
            const token = localStorage.getItem('IdToken');
            return fetch(`http://localhost:8080/v1/todo/${item._id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['todos'] });
        }
    })
    const { isPending, error, data } = useQuery({
        queryKey: ['todos'],
        queryFn: () => {
            const token = localStorage.getItem('IdToken');
            return fetch('http://localhost:8080/v1/todos', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            }).then((res) => res.json());
        }
    })
    
    if(isPending) return 'Loading...';
    if (error) return 'An error has occurred: ' + error.message;
    
    return (
        <Box
            display={"flex"}
        >
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                flexGrow={1}
            >
                <Grid 
                    container
                    spacing={{xs: 2, md: 2}}
                    columns={{xs: 4, sm: 8, md: 12}}
                    display={"flex"}
                    justifyContent={"center"}
                    alignItems={"center"}
                >
                    {data.data.map((item: CardShape) => (
                        <Grid size={{}} key={item._id}>
                            <TodoCard
                                _id={item._id}
                                name={item.name}
                                text={item.text}
                                date={item.date}
                                onClickAction={() => deleteItemMutation.mutate(item)}
                            />
                        </Grid>
                    ))}
                </Grid>
            </Box>
        </Box>
    );
}
export default Dashboard;

/*
<TodoCard
                            key={item._id}
                            _id={item._id}
                            name={item.name}
                            text={item.text}
                            date={item.date}
                            onClickAction={() => 
                                deleteItemMutation.mutate(item)
                            }
                        /> 
*/