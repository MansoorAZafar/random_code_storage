import { Box, Button } from "@mui/material";
import { useState } from "react";
import Chat from "../components/Chat";
import { useQuery } from "@tanstack/react-query";


const Home = () => {
    const [prompt, setPrompt] = useState('');
    const [previousPrompt, setPreviousPrompt] = useState('')
    const [disabled, setDisabled] = useState(false)
    const [isWaiting, setIsWaiting] = useState(true)

    const { isPending, data, refetch } = useQuery({
        queryKey: ['response'],
        enabled: false,
        queryFn: () => 
            fetch(`http://localhost:8000/ask/${prompt}`)
                .then(
                    (res) => res.json()
                ),
    })

    const handleSubmission = async () => {
        setIsWaiting(true)
        setPreviousPrompt(prompt)
        setPrompt('')
        setDisabled(true); // prevent clicking until api is done
        
        await refetch();
        setIsWaiting(false)

        // Call API
        setDisabled(false);
    }

    return (
        <Box
            display="flex"
            flexGrow={1}
            flexDirection="column"
            mr={10}
            ml={10}
            mb={5}
            mt={5}
        >
            <Box flexGrow={1} >
                <Chat
                    personText={previousPrompt}
                    botText={isWaiting ? '' : data}
                />
            </Box>

            <Box
                display="flex"
                flexDirection="row"
            >                
                < input value={prompt} style={{flexGrow: 1}} onChange={(e) => setPrompt(e.target.value)}/>
                <Button 
                    disabled={disabled} 
                    color="info" 
                    type="submit"
                    onClick={handleSubmission}
                >
                    Submit
                </Button>
            </Box>
        </Box>
    );
}
export default Home;