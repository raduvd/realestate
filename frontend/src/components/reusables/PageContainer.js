import React from "react";

const PageContainer = (props) => {
  return (
    <div>
      <div className="ui grid">
        <div className="right floated ten wide column">
          <h4 className="ui icon header">
            <i className={props.icon}></i>
            <div className="content">
              <div className="sub header">{props.pageName}</div>
            </div>
          </h4>
        </div>
      </div>
      {props.children}
    </div>
  );
};
export default PageContainer;
