import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router,Routes,Route  } from 'react-router-dom'
import Home from './pages/Home';
import CreatePost from './pages/CreatePost';
import PostDetail from './pages/PostDetail';
import PostEdit from './pages/PostEdit';
function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route  path='/'  element={<Home/>}/>
          <Route  path='/create'  element={<CreatePost/>}/>
          <Route  path='/post/:id'  element={<PostDetail/>}/>
          <Route  path='/post/edit/:id'  element={<PostEdit/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
