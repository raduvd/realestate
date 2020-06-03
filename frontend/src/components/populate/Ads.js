import React from "react";
import PageTypeForm from "../reusables/PageTypeForm";
import {
  getNumberOfPages,
  getStartingPage,
} from "../../action/FormActionCreators";
import { connect } from "react-redux";
import { populateDB } from "../../api/webDriverController";

class Ads extends React.Component {
  onNumberOfPagesInputChange = (event) => {
    this.props.getNumberOfPages(event.target.value);
  };

  onStartingPageInputChange = (event) => {
    this.props.getStartingPage(event.target.value);
  };

  onFormSubmit = (event) => {
    event.preventDefault();
    populateDB(
      this.props.startingPage,
      this.props.numberOfPages,
      this.props.pageType
    );
  };

  render() {
    return (
      <PageTypeForm
        instructions="Database Ad Population Form"
        submitButtonTooltipMesage="Start Web Driver and populate ad and adPrice DB tables"
        onFormSubmit={this.onFormSubmit}
        buttonText="Submit"
      >
        <div className="seven wide fields">
          <div className="six wide field">
            <label>Number of pages</label>
            <div
              data-position="top left"
              data-tooltip="Do 1, and after checking the result do maximum according to Latest Number Of Pages."
            >
              <input
                className="ui input"
                type="number"
                min="0"
                max="5000"
                step="1"
                onChange={this.onNumberOfPagesInputChange}
                value={this.props.numberOfPages}
                placeholder="Nr. of pages"
              />
            </div>
          </div>
        </div>

        <div className="ten wide field">
          <label>Override the default starting page</label>
          <div
            data-position="top left"
            data-tooltip="We already have a default page witch starts from page 1. But we sometimes need to start from other page."
          >
            <input
              className="ui input"
              type="text"
              onChange={this.onStartingPageInputChange}
              value={this.props.startingPage}
              placeholder="Starting Page"
            />
          </div>
        </div>
      </PageTypeForm>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    numberOfPages: state.numberOfPages,
    pageType: state.pageType,
    startingPage: state.startingPage,
  };
};
export default connect(mapStateToProps, {
  getNumberOfPages,
  getStartingPage,
})(Ads);
