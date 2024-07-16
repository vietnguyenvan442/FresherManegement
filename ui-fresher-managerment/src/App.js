import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Sidebar from './components/Sidebar';
import Login from './components/Login';
import Header from './components/Header';
import { fetchUserInfo } from './services/authService';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('isLoggedIn'));
  const [user, setUser] = useState(null);

  useEffect(() => {
    if (isLoggedIn) {
      fetchUserInfo().then(setUser).catch(console.error);
    }
  }, [isLoggedIn]);
  
  return (
    <Router>
      <div className="App" style={{ display: 'flex', minHeight: '100vh' }}>
        {isLoggedIn ? (
          <>
            <div style={{ width: '250px', flexShrink: 0 }}>
              <Sidebar user={user} />
            </div>
            <div style={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
              <Header user={user} />
              <div style={{ flex: 1, overflowY: 'auto'}}>
                <Routes>
                  <Route path="/dashboard" element={<Dashboard />} />
                  <Route path="*" element={<Navigate to="/dashboard" />} />
                </Routes>
              </div>
            </div>
          </>
        ) : (
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="*" element={<Navigate to="/login" />} />
          </Routes>
        )}
      </div>
    </Router>
  );
};

export default App;
