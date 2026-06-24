import { Route, Routes } from "react-router-dom"; 
import { Dashboard, Login } from "./features" 
import { ROUTES } from "./constants"
import AppLayout from "./layouts/AppLayout";

function App() {
  return (
    <Routes>
      <Route element={<AppLayout/>}>
        <Route index element={<Login/>}/>
        <Route path={ROUTES.home} element={<Dashboard/>}/>
      </Route>
    </Routes>
  )
}

export default App;