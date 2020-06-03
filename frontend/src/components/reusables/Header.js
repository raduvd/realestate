import React from "react";
import "react-dropdown/style.css";
import { withRouter } from "react-router-dom";
import { Link } from "react-router-dom";

class Header extends React.Component {
  render() {
    return (
      <div className="ui stackable menu">
        <div className="ui container">
          <Link to="/" className="item">
            <i className="home icon"></i> Home
          </Link>
          <Link to="/populate" className="item">
            <i className="grid layout icon"></i> Populate DB
          </Link>
          <div className="ui simple dropdown item ">
            Charts
            <i className="dropdown icon"></i>
            <div className="menu">
              <Link to="/" className="item">
                <i className="chart line icon"></i> Average Sqare Meter Prices
              </Link>
              <Link to="/fluctuation" className="item">
                <i className="chart line icon"></i> Fluctuations Average Since
                Last Date
              </Link>
              <Link to="/fluctuation/custom" className="item">
                <i className="chart line icon"></i> Custom Fluctuations
              </Link>
              <Link to="/fluctuation/numberOfAds" className="item">
                <i className="chart line icon"></i> Number of ads fluctuation
              </Link>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default withRouter(Header);
