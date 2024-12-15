import './App.css'
import Listbugcomponent from './components/Listbugcomponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Routes, Route} from 'react-router-dom'
import Bugcomponent from './components/Bugcomponent'

function App() {

  return (
    <>
    <BrowserRouter> 
        <HeaderComponent />
          <Routes>
              {/* http://localhost:8082 */}
              <Route path='/' element = { <Listbugcomponent /> }></Route>
               {/* http://localhost:8082/bugs */}
              <Route path='/bugs' element = { <Listbugcomponent /> }></Route>
              {/* http://localhost:8082/add-bug */}
              <Route path='/add-bug' element = { <Bugcomponent /> }></Route>
              {/* http://localhost:8082/update-bug/1 */}
              <Route path='/update-bug/:id' element = { <Bugcomponent /> }></Route>

          </Routes>
        <FooterComponent />
        </BrowserRouter>
    </>
  )
}

export default App
