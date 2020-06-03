import React from "react";
import PageContainer from "../reusables/PageContainer";
import Result from "./Result";
import Validate from "./Validate";
import NumberOfPages from "./NumberOfPages";
import Ads from "./Ads";

class PopulatePage extends React.Component {
  render() {
    return (
      <PageContainer icon="grid layout icon" pageName="Populate The Database">
        <NumberOfPages />
        <Ads />
        <Validate />
        <Result />
      </PageContainer>
    );
  }
}

export default PopulatePage;
