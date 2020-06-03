import React from "react";
import { ChartElement } from "./ChartElement";
import { connect } from "react-redux";
import { getNumberOfAdsFluctuation } from "../../action/ChartActionCreators";
import { getNumberOfAdsFluctuationFromBackEnd } from "../../api/adcontroller";
import PageContainer from "../reusables/PageContainer";
import { parseStringToDate } from "./ChartUtils";

class NumberOfAdsFluctuation extends React.Component {
  componentDidMount() {
    this.loadNumberOfAdsFluctuation();
  }

  async loadNumberOfAdsFluctuation() {
    const averagesFromBE = await getNumberOfAdsFluctuationFromBackEnd();
    parseStringToDate(averagesFromBE.data.apartments);
    parseStringToDate(averagesFromBE.data.houses);
    parseStringToDate(averagesFromBE.data.fields);

    this.props.getNumberOfAdsFluctuation(averagesFromBE.data);
  }

  render() {
    return (
      <PageContainer
        icon="chart line icon"
        pageName="Number Of Ads Fluctuation"
      >
        <ChartElement
          title="Appartment Number Of Ads"
          averageList={this.props.apartments}
        />
        <ChartElement
          title="Field  Number Of Ads"
          averageList={this.props.fields}
        />
        <ChartElement
          title="House  Number Of Ads"
          averageList={this.props.houses}
        />
      </PageContainer>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    apartments: state.numberOfAdsFluctuation.apartments,
    fields: state.numberOfAdsFluctuation.fields,
    houses: state.numberOfAdsFluctuation.houses,
  };
};

export default connect(mapStateToProps, {
  getNumberOfAdsFluctuation,
})(NumberOfAdsFluctuation);
