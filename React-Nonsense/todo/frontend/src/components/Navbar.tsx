import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";

const Navbar = () => {    
    const names = [
        "Aidenrost",
        "Luna Silver",
        "Marcus Row",
        "Ivy Moon",
        "Theo Ashen",
        "Nova",
        "Callum tone"
    ];

    const titles = [
        "Keeper of Secrets",
        "Warden of Dawn",
        "Bearer of Flames",
        "Architect of Fate",
        "Watcher of Stars",
        "Herald of Change",
        "Master of Whispers"
    ];

    function getRandomWord(words: Array<string>) {
        const randomIndex = Math.floor(Math.random() * words.length);
        const randomWord = words[randomIndex];
        
        return randomWord;
    }
    
    const addRandomCard = async () => {
        const token = localStorage.getItem('IdToken');
        const name = getRandomWord(names)
        const text = getRandomWord(titles)

        await fetch('http://localhost:8080/v1/todo', {            
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name,
                text
            })
        });
    }

    return (
        <Box>
           <AppBar
            position="sticky"
           >
                <Toolbar>
                    <Typography variant="h6" flexGrow={1}>
                        Todo Or NOT toDo
                    </Typography>
                    <Button
                        color="inherit"
                        onClick={addRandomCard}
                    >
                        Add Random Card
                    </Button>
                </Toolbar>
            </AppBar> 
        </Box>
    );
}
export default Navbar;