import React from "react";

const Container = (props) => {
  return (
    <div className="ui raised very padded container segment">
      {props.children}
    </div>
  );
};
export default Container;
