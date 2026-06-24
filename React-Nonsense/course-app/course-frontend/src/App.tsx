import {Route, Routes} from "react-router-dom";
import {ROUTES} from "./constants";
import Home from "./pages/Home";
import AppLayout from "./layouts/AppLayout";

const App = () => {
  return (
    <Routes>
      <Route element={<AppLayout/>}>
        <Route index element={<Home/>}/>
        <Route path={ROUTES.login}/>
        <Route path={ROUTES.dashboard}/>
      </Route>
    </Routes>
  );
}

export default App; 