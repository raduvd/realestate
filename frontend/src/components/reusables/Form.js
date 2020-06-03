import React from "react";
import Container from "./Container";

class Form extends React.Component {
  render() {
    return (
      <Container>
        <form onSubmit={this.props.onFormSubmit} className="ui form">
          <h4 className="ui dividing header">{this.props.instructions}</h4>
          {this.props.children}
          <div
            className="three wide field"
            data-position="top left"
            data-tooltip={this.props.submitButtonTooltipMesage}
          >
            <button className="ui button">{this.props.buttonText}</button>
          </div>
        </form>
      </Container>
    );
  }
}

export default Form;
