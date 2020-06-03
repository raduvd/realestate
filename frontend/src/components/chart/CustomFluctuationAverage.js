import React from "react";
import { connect } from "react-redux";

import PageContainer from "../reusables/PageContainer";
import { ChartElement } from "./ChartElement";
import PageTypeForm from "../reusables/PageTypeForm";
import {
  getMaxSquareMeters,
  getMinSquareMeters,
  getMaxSquareMeterPrice,
  getMinSquareMeterPrice,
} from "../../action/FormActionCreators";
import { getCustomFluctuationAverage } from "../../api/adcontroller";
import { getCustomFluctuationAverages } from "../../action/ChartActionCreators";
import { parseStringToDate } from "./ChartUtils";

class CustomFluctuationAverage extends React.Component {
  async onFormSubmit(event) {
    event.preventDefault();
    const customFluctuationAverage = await getCustomFluctuationAverage(
      this.props.pageType,
      this.props.maxSquareMeters,
      this.props.minSquareMeters,
      this.props.maxSquareMeterPrice,
      this.props.minSquareMeterPrice
    );
    parseStringToDate(customFluctuationAverage.data);
    this.props.getCustomFluctuationAverages(customFluctuationAverage.data);
  }

  onMaxSquareMetersInputChange = (event) => {
    this.props.getMaxSquareMeters(event.target.value);
  };

  onMinSquareMetersInputChange = (event) => {
    this.props.getMinSquareMeters(event.target.value);
  };
  onMaxSquareMeterPriceInputChange = (event) => {
    this.props.getMaxSquareMeterPrice(event.target.value);
  };
  onMinSquareMeterPriceInputChange = (event) => {
    this.props.getMinSquareMeterPrice(event.target.value);
  };

  render() {
    return (
      <PageContainer
        icon="chart line icon"
        pageName="Custom Fluctuation Average"
      >
        <PageTypeForm
          instructions="Select what type of Ad would you like to see fluctuations for: "
          submitButtonTooltipMesage="Note that for the 'Field' Page Type, droptdown option, there is no square meters."
          onFormSubmit={this.onFormSubmit.bind(this)}
          buttonText="Submit"
        >
          <div className="twelve wide fields">
            <div className="three wide field">
              <label>Maximum Square Meters</label>
              <input
                className="ui input"
                type="number"
                min="0"
                max="200000"
                step="10"
                onChange={this.onMaxSquareMetersInputChange}
                value={this.props.maxSquareMeters}
                placeholder="Max meters"
              />
            </div>
            <div className="three wide field">
              <label>Minimum Square Meters</label>
              <input
                className="ui input"
                type="number"
                min="0"
                max="200000"
                step="10"
                onChange={this.onMinSquareMetersInputChange}
                value={this.props.minSquareMeters}
                placeholder="Min meters"
              />
            </div>
            <div className="three wide field">
              <label>Maximum Square Meter Price</label>
              <input
                className="ui input"
                type="number"
                min="0"
                max="1000000"
                step="10"
                onChange={this.onMaxSquareMeterPriceInputChange}
                value={this.props.maxSquareMeterPrice}
                placeholder="Max price"
              />
            </div>
            <div className="three wide field">
              <label>Minimum Square Meter Price</label>
              <input
                className="ui input"
                type="number"
                min="0"
                max="1000000"
                step="10"
                onChange={this.onMinSquareMeterPriceInputChange}
                value={this.props.minSquareMeterPrice}
                placeholder="Min price"
              />
            </div>
          </div>
        </PageTypeForm>
        <ChartElement
          title="Custom Average"
          averageList={this.props.customFluctuationAverages}
        />
      </PageContainer>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    pageType: state.pageType,
    maxSquareMeters: state.maxSquareMeters,
    minSquareMeters: state.minSquareMeters,
    maxSquareMeterPrice: state.maxSquareMeterPrice,
    minSquareMeterPrice: state.minSquareMeterPrice,
    customFluctuationAverages: state.customFluctuationAverages,
  };
};
export default connect(mapStateToProps, {
  getMaxSquareMeters,
  getMinSquareMeters,
  getMaxSquareMeterPrice,
  getMinSquareMeterPrice,
  getCustomFluctuationAverages,
})(CustomFluctuationAverage);
