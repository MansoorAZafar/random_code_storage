import { Checkbox, Container, FormControlLabel, FormGroup } from "@mui/material";
import type { TodoData } from "../types/TodoData";

interface TodoTableProps {
    data: TodoData[];
};

export default function TodoTable({data}: TodoTableProps) {
    return (
        <Container>
            <FormGroup>
                {data.map((item) => (
                    <div key={item.id}>
                        <FormControlLabel control={<Checkbox />} label={item.name}/>
                    </div>
                ))}                
            </FormGroup>
        </Container>
    );
}