import React from 'react';
import '../styles/Dashboard.css';

const Dashboard = () => {
  return (
    <div className="dashboard">
      <div className="main-content">
        <div className="content">
          <div className="card">
            <h2>Portfolio Performance</h2>
            <div className="card-content">
              <div className="card-item">
                <h3>Cash Deposit</h3>
                <p>1.7M</p>
              </div>
              <div className="card-item">
                <h3>Invested Dividends</h3>
                <p>9M</p>
              </div>
              <div className="card-item">
                <h3>Capital Gains</h3>
                <p>$563</p>
              </div>
            </div>
            <button>View Complete Report</button>
          </div>
          <div className="card">
            <h2>Company Agents Status</h2>
            <table>
              <thead>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Company</th>
                  <th>Status</th>
                  <th>Due Date</th>
                  <th>Target Achievement</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>Juan C. Cargill</td>
                  <td>Micro Electronics</td>
                  <td>Completed</td>
                  <td>12 Dec</td>
                  <td>71%</td>
                  <td>
                    <button>Hire</button>
                    <button>Fire</button>
                  </td>
                </tr>
              </tbody>
            </table>
            <button>View Complete Report</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
