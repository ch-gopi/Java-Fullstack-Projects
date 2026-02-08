import './App.css'
import Helloworld from './Helloworld'
import Listemployeecomponent from './components/Listemployeecomponent'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Headercomponent from './components/Headercomponent';
import Footercomponent from './components/Footercomponent';
import { BrowserRouter , Routes, Route } from 'react-router-dom'
import Employeecomponent from './components/Employeecomponent';

function App() {
  return (
   <>
   <BrowserRouter>
      <Headercomponent />
      <Routes>
      {/* //  http://localhost:3000 */}
      <Route path='/' element={<Listemployeecomponent />}></Route>

      {/* // http://localhost:3000/Users */}
      <Route path='/Users' element={<Listemployeecomponent />}></Route>

       {/* //  http://localhost:3000/Add-User */}
      <Route path='/Add-User' element={<Employeecomponent />}></Route>
          {/* //  http://localhost:3000/Edit-User/1 */}
          <Route path='/Edit-User/:id' element={<Employeecomponent />}></Route>
    </Routes>
    
      <Footercomponent /></BrowserRouter>
   </>          
       )
}
export default App
