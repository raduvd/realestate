import React from "react";
import { ChartElement } from "./ChartElement";
import { connect } from "react-redux";
import {
  getApartments,
  getFields,
  getHouses,
} from "../../action/ChartActionCreators";
import { getAverage } from "../../api/adcontroller";
import PageContainer from "../reusables/PageContainer";
import { parseStringToDate } from "./ChartUtils";

class SimpleAverageChartList extends React.Component {
  componentDidMount() {
    this.loadAverages("APARTAMENT");
    this.loadAverages("TEREN");
    this.loadAverages("CASE");
  }

  async loadAverages(pagetype) {
    const averagesFromBE = await getAverage(pagetype);
    parseStringToDate(averagesFromBE.data);
    switch (pagetype) {
      case "APARTAMENT":
        this.props.getApartments(averagesFromBE.data);
        break;
      case "TEREN":
        this.props.getFields(averagesFromBE.data);
        break;
      case "CASE":
        this.props.getHouses(averagesFromBE.data);
        break;
      default:
    }
  }

  render() {
    return (
      <PageContainer
        icon="chart line icon"
        pageName="Simple Average Square Meter Prices"
      >
        <ChartElement
          title="Appartment Average"
          averageList={this.props.apartments}
        />
        <ChartElement title="Field Average" averageList={this.props.fields} />
        <ChartElement title="House Average" averageList={this.props.houses} />
      </PageContainer>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    apartments: state.apartments,
    fields: state.fields,
    houses: state.houses,
  };
};

export default connect(mapStateToProps, {
  getApartments,
  getFields,
  getHouses,
})(SimpleAverageChartList);
