import { Box, Grid, Typography } from "@mui/material";

type Item = {
    name: string;
    title: string;
    img: string;
}; 

type CardListShape = {
    items: Array<Item>;
};

const CardList = ({items}: CardListShape) => {
    return (
        <Grid
            container
            justifyContent="center"
            spacing={{xs: 2, md: 2}}
            columns={{xs: 4, sm: 8, md: 12}}
        >
            {items.map((item: Item) => (
                <Grid key={item.name} size={{xs: 2, sm: 4, md: 4}} border={"1px solid red"}>
                    <Box
                        component="img"
                        src={item.img}
                        height={"250px"}
                        sx={{
                            objectFit: "cover"
                        }}
                    />
                    <Typography variant="h6">{item.name}</Typography>
                    <Typography>{item.title}</Typography>                    
                </Grid>
            ))}
        </Grid>
    );
}
export default CardList;