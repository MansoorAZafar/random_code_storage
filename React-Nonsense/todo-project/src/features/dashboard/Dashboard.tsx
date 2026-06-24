import type { TodoData } from "../../types/TodoData.tsx";
import SearchBar from "../../components/SearchBar.tsx";
import TodoTable from "../../components/TodoTable.tsx"
import { Container, Stack } from "@mui/material";
import { useState } from "react";

export default function Dashboard() {
    const [todos, setTodos] = useState<Array<TodoData>>([]);        
    const addTodoAction = (text: string) => {
        const newTodo: TodoData = {
            id: crypto.randomUUID().slice(0, 4),
            name: text
        }

        setTodos((prev) => [...prev, newTodo])
    }

    return (
        <Container
            sx={{mt: 5}}
        >
            <Stack spacing={3}>
                <TodoTable data={todos} />
                <SearchBar onSubmit={addTodoAction} />
            </Stack>
        </Container>
    )
}