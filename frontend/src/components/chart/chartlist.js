import React from "react";
import { ChartElement } from "./chartelement";
import { connect } from "react-redux";
import {
  getApartmentAverageList,
  getFieldAverageList,
  getHouseAverageList,
} from "./../../action";
import { getAverage } from "./../../api/adcontroller";
import PageContainer from "./../reusables/PageContainer";

class ChartList extends React.Component {
  componentDidMount() {
    this.loadApartmentAverages();
    this.loadFieldAverages();
    this.loadHouseAverages();
  }

  async loadApartmentAverages() {
    const averagesFromBE = await getAverage("APARTAMENT");
    this.createDateAndFormat(averagesFromBE);
    this.props.getApartmentAverageList(averagesFromBE.data);
  }

  async loadFieldAverages() {
    const averagesFromBE = await getAverage("TEREN");
    this.createDateAndFormat(averagesFromBE);
    this.props.getFieldAverageList(averagesFromBE.data);
  }

  async loadHouseAverages() {
    const averagesFromBE = await getAverage("CASE");
    this.createDateAndFormat(averagesFromBE);
    this.props.getHouseAverageList(averagesFromBE.data);
  }

  createDateAndFormat(averagesFromBE) {
    averagesFromBE.data.forEach((el) => {
      var parts = el.x.split("-");
      var mydate = new Date(parts[0], parts[1] - 1, parts[2]);
      el.x = mydate;
    });
  }

  render() {
    return (
      <PageContainer
        icon="chart line icon"
        pageName="Average Square Meter Prices"
      >
        <ChartElement
          title="Appartment Average"
          averageList={this.props.apartmentAverageList}
        />
        <ChartElement
          title="Field Average"
          averageList={this.props.fieldAverageList}
        />
        <ChartElement
          title="House Average"
          averageList={this.props.houseAverageList}
        />
      </PageContainer>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    apartmentAverageList: state.apartmentAverageList,
    houseAverageList: state.houseAverageList,
    fieldAverageList: state.fieldAverageList,
  };
};

export default connect(mapStateToProps, {
  getApartmentAverageList,
  getFieldAverageList,
  getHouseAverageList,
})(ChartList);
