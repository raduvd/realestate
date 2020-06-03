import React from "react";
import CanvasJSReact from "./external/canvasjs.react";
//var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

export class LineChart extends React.Component {
  render() {
    const options = {
      animationEnabled: true,
      exportEnabled: true,
      theme: "light2", // "light1", "dark1", "dark2"
      axisY: {
        includeZero: false,
        suffix: " Eur",
      },
      axisX: {
        valueFormatString: "DD.MMMM.YYYY",
        interval: 1,
      },
      data: [
        {
          type: "line",
          toolTipContent:
            "{y} - Average <br/>{z} - Number of ads <br/>Date: {x} ",
          dataPoints: this.props.averageList,
        },
      ],
    };

    return (
      <div>
        <CanvasJSChart
          options={options}
          /* onRef={ref => this.chart = ref} */
        />
        {/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
      </div>
    );
  }
}
