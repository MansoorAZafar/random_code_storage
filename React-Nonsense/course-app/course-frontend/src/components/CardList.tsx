import { Box, Button, Container, Typography } from "@mui/material";
import Grid from "@mui/material/Grid";

type CardData = {
    src: string;
    title: string;
    text: string;
    alt?: string;
};

type CardListShape = {
    itemData: Array<CardData>;
};

const CardList = ({itemData}: CardListShape) => {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                {itemData.map((_item, index) => (
                    <Grid key={index} size={{ xs: 2, sm: 4, md: 4 }}>
                        <Container>
                            <Box
                                flex={1}
                                display={{ xs: 'flex' }}
                            >
                                <Box
                                    component="img"
                                    src={_item.src}
                                    alt="Image"
                                    loading="lazy"
                                    sx={{
                                        width: '100%',
                                        height: '250px',
                                        objectFit: 'cover',
                                    }}
                                />
                            </Box>
                            <Typography>
                                {_item.text}
                            </Typography>
                            <Button color='info'>Click Me</Button>
                        </Container>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
}
export default CardList;