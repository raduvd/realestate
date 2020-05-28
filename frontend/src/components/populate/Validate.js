import { validateTotayAds } from "./../../api/adcontroller";

import React from "react";
import Container from "../reusables/Container";

class Validate extends React.Component {
  render() {
    return (
      <Container>
        <button className="ui button" onClick={() => validateTotayAds()}>
          Validate Today Ads
        </button>
        <h6>Before validating please check all the errors in log.</h6>
      </Container>
    );
  }
}
export default Validate;
