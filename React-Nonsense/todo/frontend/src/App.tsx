import {Routes, Route} from "react-router-dom";
import { Home, Dashboard } from "./pages";
import { AppLayout } from "./layouts";

const App = () => {
  return (
    <Routes>
      <Route element={<AppLayout/>}>
        <Route index element={<Home/>}/>
        <Route element={<Dashboard/>} path={"/dashboard"} />
      </Route>
    </Routes>
  );
}

export default App;