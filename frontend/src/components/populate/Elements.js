import React from "react";
import Container from "../reusables/Container";
import { getPageType, getNumberOfPages } from "./../../action";
import { connect } from "react-redux";
import { populateDB } from "./../../api/webDriverController";

class Elements extends React.Component {
  onFormSubmit = (event) => {
    event.preventDefault();
    populateDB(this.props.numberOfPages, this.props.pageType);
  };

  onCheckboxChange = (event) => {
    this.props.getPageType(event.target.name);
  };

  onNumberOfPagesInputChange = (event) => {
    this.props.getNumberOfPages(event.target.value);
  };

  render() {
    return (
      <Container>
        <p>Select Page Type and Number of pages to iterate over:</p>
        <form onSubmit={this.onFormSubmit} className="ui form">
          <div className="inline fields">
            <div className="field">
              <div className="ui radio checkbox">
                <input
                  type="radio"
                  onChange={this.onCheckboxChange}
                  name="APARTAMENT"
                />
                <label>Apartment</label>
              </div>
            </div>
            <div className="field">
              <div className="ui radio checkbox">
                <input
                  type="radio"
                  onChange={this.onCheckboxChange}
                  name="TEREN"
                />
                <label>Field</label>
              </div>
            </div>
            <div className="field">
              <div className="ui radio checkbox">
                <input
                  type="radio"
                  onChange={this.onCheckboxChange}
                  name="CASE"
                />
                <label>House</label>
              </div>
            </div>
            <div className="field">
              <input
                className="ui input"
                type="number"
                min="0"
                max="5000"
                step="1"
                onChange={this.onNumberOfPagesInputChange}
                value={this.props.numberOfPages}
              />
            </div>
            <div className="field">
              <div data-tooltip="Do 1 page (see results) and then 500 (process all ads).">
                <button className="ui button">Submit</button>
              </div>
            </div>
          </div>
        </form>
      </Container>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    pageType: state.pageType,
    numberOfPages: state.numberOfPages,
  };
};
export default connect(mapStateToProps, { getPageType, getNumberOfPages })(
  Elements
);
