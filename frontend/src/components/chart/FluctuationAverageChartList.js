import React from "react";
import { ChartElement } from "./ChartElement";
import { connect } from "react-redux";
import {
  getApartmentFluctuations,
  getFieldFluctuations,
  getHouseFluctuations,
} from "../../action/ChartActionCreators";
import { getFluctuationAverage } from "../../api/adcontroller";
import PageContainer from "../reusables/PageContainer";
import { parseStringToDate } from "./ChartUtils";

class FluctuationAverageChartList extends React.Component {
  componentDidMount() {
    this.loadAverages("APARTAMENT");
    this.loadAverages("TEREN");
    this.loadAverages("CASE");
  }

  async loadAverages(pagetype) {
    const averagesFromBE = await getFluctuationAverage(pagetype);
    parseStringToDate(averagesFromBE.data);
    switch (pagetype) {
      case "APARTAMENT":
        this.props.getApartmentFluctuations(averagesFromBE.data);
        break;
      case "TEREN":
        this.props.getFieldFluctuations(averagesFromBE.data);
        break;
      case "CASE":
        this.props.getHouseFluctuations(averagesFromBE.data);
        break;
      default:
    }
  }

  render() {
    return (
      <PageContainer
        icon="chart line icon"
        pageName="Fluctuation Average Since Last Date"
      >
        <ChartElement
          title="Appartment Average"
          averageList={this.props.apartmentFluctuations}
        />
        <ChartElement
          title="Field Average"
          averageList={this.props.fieldFluctuations}
        />
        <ChartElement
          title="House Average"
          averageList={this.props.houseFluctuations}
        />
      </PageContainer>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    apartmentFluctuations: state.apartmentFluctuations,
    fieldFluctuations: state.fieldFluctuations,
    houseFluctuations: state.houseFluctuations,
  };
};

export default connect(mapStateToProps, {
  getApartmentFluctuations,
  getFieldFluctuations,
  getHouseFluctuations,
})(FluctuationAverageChartList);
