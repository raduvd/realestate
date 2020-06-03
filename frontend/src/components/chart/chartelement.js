import React from "react";
import { LineChart } from "./LineChart";
import Container from "../reusables/Container";

export class ChartElement extends React.Component {
  render() {
    return (
      <Container>
        <h2 className="ui header">{this.props.title}</h2>
        <LineChart averageList={this.props.averageList} />
      </Container>
    );
  }
}
