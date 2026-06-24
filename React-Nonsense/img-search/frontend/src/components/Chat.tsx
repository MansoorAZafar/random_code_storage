import { Box, Typography } from "@mui/material";

export type BotText = {
    response: string;
    sources: Array<string>;
}

export type ChatShape = {
    personText: string;
    botText: BotText;
}

const Chat = ({personText, botText}: ChatShape) => {
    return (
        <>
            <Box
                display="flex"
                justifyContent="flex-end"
                mb={5}
                border="1px solid green"
                p={3}
            >
                { personText ? <strong>You:</strong> : '' } 
                {personText}
            </Box>
            <Box
                border="1px solid blue"
                p={3}
            >
                { botText ? <strong>Bot:</strong> : '' } {botText.response}
                <br/><br/>
                {botText ? <p>Sources: {botText.sources}</p> : '' } 
            </Box>
        </>
    );
}
export default Chat;