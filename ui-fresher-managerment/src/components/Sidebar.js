import React from 'react';
import '../styles/Sidebar.css';

const Sidebar = () => {
  return (
    <div className="sidebar">
      <div className="logo">
        <h2>Fresher Management</h2>
      </div>
      <ul>
        <li>
          <a href="/freshers"><i className="icon"></i> Freshers</a>
          <ul className="sub-menu">
            <li><a href="/freshers/list">List Fresher</a></li>
            <li><a href="/freshers/add">Add Fresher</a></li>
          </ul>
        </li>
        <li>
          <a href="/centers"><i className="icon"></i> Centers</a>
          <ul className="sub-menu">
            <li><a href="/centers/list">List Center</a></li>
            <li><a href="/centers/add">Add Center</a></li>
          </ul>
        </li>
        <li>
          <a href="/statistics"><i className="icon"></i> Statistic</a>
          <ul className="sub-menu">
            <li><a href="/statistics/center">Statistic Center</a></li>
            <li><a href="/statistics/fresher">Statistic Fresher</a></li>
          </ul>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
