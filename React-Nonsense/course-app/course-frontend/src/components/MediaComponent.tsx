import { Box, Button, Typography } from "@mui/material";

type MediaComponentShape = {
    text: string;
    onClick: () => void;
    imgPath: string; 
};

const MediaComponent = ({text, onClick, imgPath}: MediaComponentShape) => {
    return (
        <Box
            display={"flex"}
            alignItems={"center"}
        >
            <Box
                flex={1}
                display={"flex"}
                flexDirection={"column"}
                justifyContent={"center"}
                alignContent={"center"}
                px={4}
                mr={10}
            >
                <Typography
                    sx={{maxWidth: '60ch'}}
                    variant="h6"
                    mb={"30px"}
                >
                    {text}
                </Typography>
                <Button 
                    onClick={onClick} 
                    color="primary" 
                    sx={{
                        borderBottom: '1px blue solid',
                    }}
                >
                    Learn More
                </Button>
            </Box>
            <Box
                flex={1}
                display={{ xs: 'none', md: 'flex' }}
            >
                <Box
                    component="img"
                    src={imgPath}
                    alt="Image"
                    loading="lazy"
                    sx={{
                        width: '100%',
                        height: '350px',
                        objectFit: 'cover',
                    }}
                />
            </Box>
        </Box>
    );
}

export default MediaComponent;