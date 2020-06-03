import React from "react";
import { BrowserRouter, Route } from "react-router-dom";
import Header from "./reusables/Header";
import SimpleAverageChartList from "./chart/SimpleAverageChartList";
import PopulatePage from "./populate/PopulatePage";
import FluctuationAverageChartList from "./chart/FluctuationAverageChartList";
import CustomFluctuationAverage from "./chart/CustomFluctuationAverage";
import NumberOfAdsFluctuation from "./chart/NumberOfAdsFluctuation"

export const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <Route path="/" exact component={SimpleAverageChartList} />
      <Route path="/populate" exact component={PopulatePage} />
      <Route
        path="/fluctuation"
        exact
        component={FluctuationAverageChartList}
      />
      <Route
        path="/fluctuation/custom"
        exact
        component={CustomFluctuationAverage}
      />
      <Route path="/fluctuation/numberOfAds" exact component={NumberOfAdsFluctuation} />
    </BrowserRouter>
  );
};
