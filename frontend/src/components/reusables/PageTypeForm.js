import React from "react";
import { getPageType } from "../../action/FormActionCreators";
import { connect } from "react-redux";
import Form from "./Form";

class PageTypeForm extends React.Component {
  onDropdownChange = (event) => {
    this.props.getPageType(event.target.value);
  };

  render() {
    return (
      <Form
        onFormSubmit={this.props.onFormSubmit}
        instructions={this.props.instructions}
        submitButtonTooltipMesage={this.props.submitButtonTooltipMesage}
        buttonText={this.props.buttonText}
      >
        <div className="fields">
          <div className="three wide field">
            <label>Page Type</label>
            <div className="field">
              <select
                onChange={this.onDropdownChange}
                className="ui fluid search dropdown"
              >
                <option value="APARTAMENT">Apartment</option>
                <option value="CASE">House</option>
                <option value="TEREN">Field</option>
              </select>
            </div>
          </div>
        </div>
        {this.props.children}
      </Form>
    );
  }
}

const mapStateToProps = (state) => {
  return {};
};
export default connect(mapStateToProps, { getPageType })(PageTypeForm);
