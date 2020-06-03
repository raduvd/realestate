import { validateTotayAds } from "./../../api/adcontroller";
import React from "react";
import Form from "../reusables/Form";

class Validate extends React.Component {
  onFormSubmit = (event) => {
    event.preventDefault();
    validateTotayAds();
  };

  render() {
    return (
      <Form
        instructions="Database Validation Of Today Ads Form"
        onFormSubmit={this.onFormSubmit}
        submitButtonTooltipMesage="Before validating please check all the errors in log"
        buttonText="Validate"
      ></Form>
    );
  }
}
export default Validate;
