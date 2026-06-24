import {Routes, Route} from "react-router-dom";
import AppLayout from "./layouts/AppLayout";
import Home from "./pages/Home";

const App = () => {
  return (
    <Routes>
      <Route element={<AppLayout/>}>
        <Route index element={<Home/>}/>
      </Route>
    </Routes>
  );
}
export default App;