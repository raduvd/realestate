import React from "react";
import Elements from "./Elements";
import PageContainer from "../reusables/PageContainer";
import Result from "./Result";
import Validate from "./Validate";

class Populate extends React.Component {
  render() {
    return (
      <PageContainer
        icon="grid layout icon"
        pageName="Populate The Database Page"
      >
        <Elements />
        <Result />
        <Validate />
      </PageContainer>
    );
  }
}
export default Populate;
