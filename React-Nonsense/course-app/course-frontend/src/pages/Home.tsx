import { Box, Container } from "@mui/material";
import MediaComponent from "../components/MediaComponent";
import CardList from "../components/CardList";

const itemData = [
  {
    src: 'https://images.unsplash.com/photo-1551963831-b3b1ca40c98e',
    title: 'Breakfast',
    text: '@bkristastucchio',
  },
  {
    src: 'https://images.unsplash.com/photo-1551782450-a2132b4ba21d',
    title: 'Burger',
    text: '@rollelflex_graphy726',
  },
  {
    src: 'https://images.unsplash.com/photo-1522770179533-24471fcdba45',
    title: 'Camera',
    text: '@helloimnik',
  },
  {
    src: 'https://images.unsplash.com/photo-1444418776041-9c7e33cc5a9c',
    title: 'Coffee',
    text: '@nolanissac',
  },
  {
    src: 'https://images.unsplash.com/photo-1533827432537-70133748f5c8',
    title: 'Hats',
    text: '@hjrc33',
  },
  {
    src: 'https://images.unsplash.com/photo-1558642452-9d2a7deb7f62',
    title: 'Honey',
    text: '@arwinneil',
  },
  {
    src: 'https://images.unsplash.com/photo-1516802273409-68526ee1bdd6',
    title: 'Basketball',
    text: '@tjdragotta',
  },
  {
    src: 'https://images.unsplash.com/photo-1518756131217-31eb79b20e8f',
    title: 'Fern',
    text: '@katie_wasserman',
  },
  {
    src: 'https://images.unsplash.com/photo-1597645587822-e99fa5d45d25',
    title: 'Mushrooms',
    text: '@silverdalex',
  },
  {
    src: 'https://images.unsplash.com/photo-1567306301408-9b74779a11af',
    title: 'Tomato basil',
    text: '@shelleypauls',
  },
  {
    src: 'https://images.unsplash.com/photo-1471357674240-e1a485acb3e1',
    title: 'Sea star',
    text: '@peterlaster',
  },
  {
    src: 'https://images.unsplash.com/photo-1589118949245-7d38baf380d6',
    title: 'Bike',
    text: '@southside_customs',
  },
];

const Home = () => {
    return (
        <Box>
            <Container maxWidth="lg" sx={{ py: { xs: 6, md: 10 } }}>
               <MediaComponent
                    text="Lorem ipsum dolor sit amet consectetur adipisicing elit. Vero velit accusantium iure ullam eos, fuga obcaecati aliquam eligendi inventore quod?"
                    onClick={() => {}}
                    imgPath="https://t4.ftcdn.net/jpg/06/57/37/01/360_F_657370150_pdNeG5pjI976ZasVbKN9VqH1rfoykdYU.jpg"
                /> 
                <Box
                    mb={10}
                />
                <CardList
                    itemData={itemData}
                />
            </Container>
        </Box>
    );
}

export default Home;