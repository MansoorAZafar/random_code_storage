import { Box, Button, Card, CardActions, CardContent, Typography } from "@mui/material";

export type CardShape = {
    name: string;
    text: string;
    _id: string;
    date: string;
    onClickAction?: () => void;
};

const TodoCard = ({name, text, _id, date, onClickAction}: CardShape) => {
    return (
       <Box>
        <Card 
            variant="outlined" 
            sx={{maxWidth: "250px", minHeight: "250px", overflow: 'auto', marginTop: 2, marginBottom: 2}}
        >
            <CardContent>
                <Typography variant="h4" component="div">
                    {name}
                </Typography>
                <Typography sx={{ color: 'text.secondary', mb: 1.5 }}>
                    <strong>ID:</strong> {_id}
                </Typography>
                <Typography variant="h6" mb={0.5}>
                    {text}
                </Typography>
                <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                    {date}
                </Typography>
            </CardContent>
            <CardActions>
            <Button 
                size="small" 
                color="error" 
                variant="outlined"
                onClick={onClickAction}
            >
                Delete
            </Button>
            </CardActions>

        </Card>
       </Box>
    )
}
export default TodoCard;