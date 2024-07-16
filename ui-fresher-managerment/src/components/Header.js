import React from 'react';
import '../styles/Header.css';

function Header(props){
  const user = props.user;

  console.log(user)
  return (
    <div className="header">
      <div className="header-left">
      <img src={process.env.PUBLIC_URL + '/avatar.jpg'} alt="Avatar" className="avatar" />
        <span className="admin-name">Nguyễn Văn Việt</span>
      </div>
      <div className="header-right">
        <i className="fas fa-question-circle help-icon"></i>
      </div>
    </div>  
  );
};

export default Header;
