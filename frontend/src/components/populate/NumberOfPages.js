import React from "react";
import Form from "../reusables/Form";
import { populateNumberOfAds } from "./../../api/webDriverController";
import { getLatestNumberOfPagesFromBE } from "../../api/adcontroller";
import { getLatestNumberOfPages } from "../../action/FormActionCreators";
import { connect } from "react-redux";

class NumberOfPages extends React.Component {
  onFormSubmit = (event) => {
    event.preventDefault();
    populateNumberOfAds();
  };

  async componentDidMount() {
    this.props.getLatestNumberOfPages(
      await (await getLatestNumberOfPagesFromBE()).data
    );
  }

  //RENDER THIS:  { this.props.latestNumberOfPages }
  render() {
    return (
      <Form
        instructions="Latest Number Of Pages"
        onFormSubmit={this.onFormSubmit}
        submitButtonTooltipMesage="This will start WebDriver and insert in DB the total number of pages from imobiliare.ro."
        buttonText="Update"
      >
        <table className="ui celled table">
          <thead>
            <tr>
              <th>Nr Pages</th>
              <th>Date</th>
              <th>Page Type</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td data-label="Nr Pages">
                {this.props.latestNumberOfPages[0] == null
                  ? 0
                  : this.props.latestNumberOfPages[0].numberOfPages}
              </td>
              <td data-label="Date">
                {" "}
                {this.props.latestNumberOfPages[0] == null
                  ? 0
                  : this.props.latestNumberOfPages[0].numberOfAdsId.addedAtDate}
              </td>
              <td data-label="Page Type">
                {" "}
                {this.props.latestNumberOfPages[0] == null
                  ? 0
                  : this.props.latestNumberOfPages[0].numberOfAdsId.pageType}
              </td>
            </tr>
            <tr>
              <td data-label="Nr Pages">
                {this.props.latestNumberOfPages[1] == null
                  ? 0
                  : this.props.latestNumberOfPages[1].numberOfPages}
              </td>
              <td data-label="Date">
                {" "}
                {this.props.latestNumberOfPages[1] == null
                  ? 0
                  : this.props.latestNumberOfPages[1].numberOfAdsId.addedAtDate}
              </td>
              <td data-label="Page Type">
                {" "}
                {this.props.latestNumberOfPages[1] == null
                  ? 0
                  : this.props.latestNumberOfPages[1].numberOfAdsId.pageType}
              </td>
            </tr>
            <tr>
              <td data-label="Nr Pages">
                {this.props.latestNumberOfPages[2] == null
                  ? 0
                  : this.props.latestNumberOfPages[2].numberOfPages}
              </td>
              <td data-label="Date">
                {" "}
                {this.props.latestNumberOfPages[2] == null
                  ? 0
                  : this.props.latestNumberOfPages[2].numberOfAdsId.addedAtDate}
              </td>
              <td data-label="Page Type">
                {" "}
                {this.props.latestNumberOfPages[2] == null
                  ? 0
                  : this.props.latestNumberOfPages[2].numberOfAdsId.pageType}
              </td>
            </tr>
          </tbody>
        </table>
      </Form>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    latestNumberOfPages: state.latestNumberOfPages,
  };
};
export default connect(mapStateToProps, {
  getLatestNumberOfPages,
})(NumberOfPages);
