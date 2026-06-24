import { Card, CardContent, Typography } from "@mui/material";
import type { TodoShape } from "../types";

const TodoCard = ({userId, id, title, completed}: TodoShape) => {
    return (
        <Card sx={{height: 150, border: '10px red solid'}}>
            <CardContent>
                <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 14 }}>
                    {userId} - {id}
                </Typography>

                <Typography variant="h6" component="div">
                    {title}
                </Typography>

                <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 14 }}>
                    {completed ? 'true' : 'false'}
                </Typography>  
            </CardContent>
        </Card>
    )
}
export default TodoCard;